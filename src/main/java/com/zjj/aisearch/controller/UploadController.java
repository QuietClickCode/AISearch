package com.zjj.aisearch.controller;

import com.zjj.aisearch.mapper.DocumentMapper;
import com.zjj.aisearch.mapper.UploadFileMapper;
import com.zjj.aisearch.model.ResponseResult;
import com.zjj.aisearch.pojo.dto.DocumentDTO;
import com.zjj.aisearch.pojo.dto.FullTextDTO;
import com.zjj.aisearch.repository.impl.DocumentESRepository;
import com.zjj.aisearch.service.UploadFileService;
import com.zjj.aisearch.utils.DateTimeUtil;
import com.zjj.aisearch.utils.FastDFSClientUtil;
import com.zjj.aisearch.utils.MultipartFileToFile;
import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @Description: FastDFS做网盘和图床
 * @Param:
 * @return:
 * @Author: zjj
 * @Date: 2020/2/15
 */
@RestController
public class UploadController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private UploadFileMapper uploadFileMapper;

    @Autowired
    private DocumentESRepository documentESRepository;
    @Autowired
    private FastDFSClientUtil dfsClient;
    @Autowired
    private UploadFileService uploadFileServiceImpl;

    @Autowired
    private UploadFileService uploadFileFastServiceImpl;



    //上传到本地文件系统中
    @PostMapping("/uploadlocal")
    public ResponseResult uploadlocal(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
       return uploadFileServiceImpl.uploadlocal(file);
    }

    //上传到本地文件系统中,使用缓冲流,快一些
    @PostMapping("/uploadlocalfast")
    public ResponseResult uploadlocalfast(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        return uploadFileFastServiceImpl.uploadlocal(file);
    }

    //上传到服务器上
    @PostMapping("/upload")
    public ResponseResult fdfsUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        try {
            String fileUrl = dfsClient.uploadFile(file);
            //提取文件信息进入数据库
            FullTextDTO fullTextDTO = new FullTextDTO();
            long size = file.getSize();
            Tika tika = new Tika();
            File file1 = MultipartFileToFile.multipartFileToFile(file);
            String fileName = file1.getName();
            String filecontent = tika.parseToString(file1);
            MultipartFileToFile.delteTempFile(file1);
            fullTextDTO.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
            //应该存用户id,暂时写死
            fullTextDTO.setCreateuser("zjj");
            fullTextDTO.setFileContent(filecontent);
            fullTextDTO.setFileName(fileName);
            fullTextDTO.setFilePath(fileUrl);
            fullTextDTO.setFileSize(size);
            fullTextDTO.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
            //保存到数据库
            uploadFileServiceImpl.save(fullTextDTO);
            //保存到索引库
            DocumentDTO documentDTO = new DocumentDTO();
            documentDTO.setDocumentcontent(filecontent);
            documentDTO.setDocumentname(fileName);
            documentDTO.setUrl(fileUrl);
            documentMapper.insert(documentDTO);
            documentESRepository.save(documentDTO);
            responseResult.setStatus(0);
            responseResult.setMsg("上传成功,这是第" + fullTextDTO.getId() + "个文件");
            responseResult.setUrl(fileUrl);
            responseResult.setData(fileUrl);
        } catch (IOException e) {
            responseResult.setStatus(-1);
            responseResult.setMsg("上传失败");
            e.printStackTrace();
        }
        return responseResult;
    }


    /*
     * http://localhost/download?filePath=group1/M00/00/00/wKgIZVzZEF2ATP08ABC9j8AnNSs744.jpg
     */
    @RequestMapping("/download")
    public void download(String filePath, HttpServletRequest request, HttpServletResponse response) throws IOException {

        //    group1/M00/00/00/wKgIZVzZEF2ATP08ABC9j8AnNSs744.jpg
        String[] paths = filePath.split("/");
        String groupName = null;
        for (String item : paths) {
            if (item.indexOf("group") != -1) {
                groupName = item;
                break;
            }
        }
        String path = filePath.substring(filePath.indexOf(groupName) + groupName.length() + 1, filePath.length());
        InputStream input = dfsClient.download(groupName, path);

        //根据文件名获取 MIME 类型
        String fileName = paths[paths.length - 1];
        System.out.println("fileName :" + fileName); // wKgIZVzZEF2ATP08ABC9j8AnNSs744.jpg
        String contentType = request.getServletContext().getMimeType(fileName);
        String contentDisposition = "attachment;filename=" + fileName;

        // 设置头
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Disposition", contentDisposition);

        // 获取绑定了客户端的流
        ServletOutputStream output = response.getOutputStream();

        // 把输入流中的数据写入到输出流中
        IOUtils.copy(input, output);
        input.close();

    }

    /**
     * http://localhost/deleteFile?filePath=group1/M00/00/00/wKgIZVzZaRiAZemtAARpYjHP9j4930.jpg
     *
     * @param filePath group1/M00/00/00/wKgIZVzZaRiAZemtAARpYjHP9j4930.jpg
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deleteFile")
    public ResponseResult delFile(Integer id, String filePath, HttpServletRequest request, HttpServletResponse response) {
        ResponseResult responseResult = new ResponseResult();
        try {
            dfsClient.delFile(filePath);
            uploadFileServiceImpl.deleteFile(id);
        } catch (Exception e) {
            // 文件不存在报异常 ： com.github.tobato.fastdfs.exception.FdfsServerException: 错误码：2，错误信息：找不到节点或文件
            responseResult.setStatus(-1);
            responseResult.setMsg("删除失败");
            e.printStackTrace();
            return responseResult;
        }
        responseResult.setStatus(0);
        responseResult.setMsg("成功删除");
        return responseResult;
    }
}
