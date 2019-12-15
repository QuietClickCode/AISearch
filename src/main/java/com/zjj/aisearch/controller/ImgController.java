package com.zjj.aisearch.controller;

import com.zjj.aisearch.config.ConfigBean;
import com.zjj.aisearch.model.Img;
import com.zjj.aisearch.model.ResponseResult;
import com.zjj.aisearch.service.ImgService;
import com.zjj.aisearch.utils.DateTimeUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    private ConfigBean configBean;

    @ApiOperation("上传图片")
    @PostMapping("/uploadImg")
    public ResponseResult uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
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
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatus(0);
        responseResult.setMsg("上传成功,这是第" + img.getId() + "张图片");
        responseResult.setUrl(fileName);
        responseResult.setData(fileName);
        return responseResult;
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
