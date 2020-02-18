package com.zjj.aisearch.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

/**
 * @program: AISearch
 * @description: 文件上传工具类
 * @author: zjj
 * @create: 2020-02-18 00:45:18
 **/
public class UploadFileUtil {

    public static String uploadFile(String uploadPath, MultipartFile file) {
        return uploadFileCommon(uploadPath, file, false);
    }

    public static String uploadFileCommon(String uploadPath, MultipartFile file, boolean isFast) {
        InputStream inputStream = null;
        OutputStream os = null;
        String path = null;
        String fileName = new Date().getTime() + "_" + file.getOriginalFilename();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
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

            if (isFast) {
                bis = new BufferedInputStream(inputStream);
                bos = new BufferedOutputStream(os);
                // 开始读取
                while ((len = bis.read(bs)) != -1) {
                    bos.write(bs, 0, len);
                }
            } else {
                // 开始读取
                while ((len = inputStream.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                if (isFast) {
                    bos.close();
                    bis.close();
                    os.close();
                    inputStream.close();
                } else {
                    os.close();
                    inputStream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    //缓冲版
    public static String uploadFileFast(String uploadPath, MultipartFile file) {
        return uploadFileCommon(uploadPath, file, true);
    }
}
