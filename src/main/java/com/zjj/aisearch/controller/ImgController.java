package com.zjj.aisearch.controller;

import com.zjj.aisearch.config.ConfigBean;
import com.zjj.aisearch.model.Img;
import com.zjj.aisearch.model.ResponseResult;
import com.zjj.aisearch.pojo.dto.FullTextDTO;
import com.zjj.aisearch.pojo.entity.Page;
import com.zjj.aisearch.repository.FullTextRepository;
import com.zjj.aisearch.service.ImgService;
import com.zjj.aisearch.service.UploadFileService;
import com.zjj.aisearch.utils.DateTimeUtil;
import io.searchbox.client.JestResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: AISearch
 * @description: 图片上传
 * @author: zjj
 * @create: 2019-12-15 12:17:02
 **/
@RestController
@Slf4j
public class ImgController {
    @Autowired
    private ImgService imgService;

    @Autowired
    private UploadFileService uploadFileServiceImpl;
    @Autowired
    private FullTextRepository fullTextESRepository;

    @Autowired
    private ConfigBean configBean;
    //获取登录信息 不知道怎么获取不到
  /*  Subject subject = SecurityUtils.getSubject();
    User user = (User) subject.getPrincipal();*/

    @ApiOperation("上传图片")
    @PostMapping("/uploadImg")
    public ResponseResult uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map resultMap = new HashMap();
        String uploadPath = configBean.getImgDir();
        String fileName = uploadFile(uploadPath, file);
        Img img = new Img();

        img.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        img.setImgOriginName(file.getOriginalFilename());
        img.setImgNewName(fileName);
        img.setLocation(uploadPath);
        img.setSize(file.getSize());
        img.setType(file.getContentType());
        int result = imgService.save(img);


        //提取文件信息进入数据库
        FullTextDTO fullTextDTO = new FullTextDTO();

        InputStream inputStream = file.getInputStream();
        long size = file.getSize();
        StringBuffer sb = new StringBuffer();
        byte[] tempbytes = new byte[1024];
        int byteread = 0;
        while ((byteread = inputStream.read(tempbytes)) != -1) {
            //必须加GBK字符编码,不然数据库乱码
            String s = new String(tempbytes, 0, byteread, "GBK");
            sb.append(s);
        }
        fullTextDTO.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        //应该存用户id,暂时写死
        fullTextDTO.setCreateuser("zjj");
        fullTextDTO.setFileContent(sb.toString());
        fullTextDTO.setFileName(fileName);
        fullTextDTO.setFilePath(uploadPath);
        fullTextDTO.setFileSize(size);
        fullTextDTO.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));


        uploadFileServiceImpl.save(fullTextDTO);
        fullTextESRepository.save(fullTextDTO);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatus(0);
        responseResult.setMsg("上传成功,这是第" + img.getId() + "张图片");
        responseResult.setUrl(fileName);
        responseResult.setData(fileName);
        return responseResult;
    }


    @ApiOperation("删除文档")
    @PostMapping("/deleteDocumentById")
    public ResponseResult uploadImg(@RequestBody Map<String, String> param) throws IOException {

        JestResult jestResult = fullTextESRepository.deleteDocument("fulltextfile", "file", param.get("deleteid"));
        System.out.println(jestResult.getJsonString());
        return null;
    }
    @ApiOperation("查询文档")
    @PostMapping("/queryDocument")
    public ResponseResult queryDocument(@RequestBody Map<String, String> param) throws IOException {

        Page<FullTextDTO> jestResult = fullTextESRepository.query(param.get("keyword"), 1, 20);
        System.out.println(jestResult);
        return null;
    }



    private String uploadFile(String uploadPath, MultipartFile file) {
        InputStream inputStream = null;
        OutputStream os = null;
        String path = null;
        String fileName = new Date().getTime() + "_" + file.getOriginalFilename();
        try {
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(uploadPath);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            inputStream = file.getInputStream();
            path = tempFile.getPath() + File.separator + fileName;
            os = new FileOutputStream(path);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }
}
