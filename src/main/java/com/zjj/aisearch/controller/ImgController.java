package com.zjj.aisearch.controller;

import com.zjj.aisearch.config.ConfigBean;
import com.zjj.aisearch.model.Img;
import com.zjj.aisearch.model.ResponseResult;
import com.zjj.aisearch.pojo.dto.DocumentDTO;
import com.zjj.aisearch.pojo.dto.FullTextDTO;
import com.zjj.aisearch.pojo.entity.Page;
import com.zjj.aisearch.repository.FullTextRepository;
import com.zjj.aisearch.repository.impl.DocumentESRepository;
import com.zjj.aisearch.service.ImgService;
import com.zjj.aisearch.service.UploadFileService;
import com.zjj.aisearch.utils.DateTimeUtil;
import com.zjj.aisearch.utils.MultipartFileToFile;
import io.searchbox.client.JestResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
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

import static com.zjj.aisearch.utils.UploadFileUtil.uploadFileCommon;

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
    private DocumentESRepository documentESRepository;

    @Autowired
    private ConfigBean configBean;
    //获取登录信息 不知道怎么获取不到
  /*  Subject subject = SecurityUtils.getSubject();
    User user = (User) subject.getPrincipal();*/

    @ApiOperation("上传图片")
    @PostMapping("/uploadImg")
    public ResponseResult uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        long size = file.getSize();
        //只支持文本文件
        /*InputStream inputStream = file.getInputStream();
        long size = file.getSize();
        StringBuffer sb = new StringBuffer();
        byte[] tempbytes = new byte[1024];
        int byteread = 0;
        while ((byteread = inputStream.read(tempbytes)) != -1) {
            //必须加GBK字符编码,不然数据库乱码
            String s = new String(tempbytes, 0, byteread, "GBK");
            sb.append(s);
        }*/

        //tika支持各种类型的文件,doc,java,js,html,md,excel,pdf等

        Tika tika = new Tika();
        File file1 = MultipartFileToFile.multipartFileToFile(file);
        String filecontent = tika.parseToString(file1);
        MultipartFileToFile.delteTempFile(file1);
        fullTextDTO.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        //应该存用户id,暂时写死
        fullTextDTO.setCreateuser("zjj");
        /*fullTextDTO.setFileContent(sb.toString());*/

        fullTextDTO.setFileContent(filecontent);
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

    @ApiOperation("跳转详情")
    @PostMapping("/toqueryDocument")
    public ResponseResult toqueryDocument(@RequestBody Map<String, String> map, HttpServletResponse response, HttpServletRequest request) throws IOException {
        ResponseResult responseResult = new ResponseResult();
        if (!map.get("keyword").isEmpty()) {
            request.getSession().setAttribute("keyword", map.get("keyword"));
            //不做任何事,避免生成两次记录
            responseResult.setUrl("detail");
            return responseResult;
        }
        return null;
    }

    @ApiOperation("获取关键字keyword")
    @PostMapping("/getKeyword")
    public ResponseResult getKeyword(HttpServletRequest request) {
        String keyword = (String) request.getSession().getAttribute("keyword");
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg(keyword);

        return responseResult;
    }

    @ApiOperation("查询文档")
    @PostMapping("/queryDocument")
    public ResponseResult queryDocument(HttpServletResponse response, HttpServletRequest request) throws IOException {
        /*String keyword = (String) request.getSession().getAttribute("keyword");
        Page<FullTextDTO> jestResult = fullTextESRepository.query(keyword, 1, 20);
        System.out.println(jestResult);
        System.out.println(jestResult.getList().toString());
        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(jestResult.getList());
        return responseResult;*/
        String keyword = (String) request.getSession().getAttribute("keyword");
        Page<DocumentDTO> jestResult = documentESRepository.query(keyword, 1, 20);
        System.out.println(jestResult);
        System.out.println(jestResult.getList().toString());
        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(jestResult.getList());
        return responseResult;
    }


    private String uploadFile(String uploadPath, MultipartFile file) {
        return uploadFileCommon(uploadPath, file, true);
    }
}
