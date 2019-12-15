package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.ResponseResult;
import com.zjj.aisearch.service.ImgService;
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

    @ApiOperation("上传图片")
    @PostMapping("/uploadImg")
    public ResponseResult uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        log.error(file.getContentType());
        Map resultMap = new HashMap();
        String uploadPath = "I:/img";
        String fileName = uploadFile(uploadPath, file);
        log.error(fileName);
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
