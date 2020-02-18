package com.zjj.aisearch.service.impl;

import com.zjj.aisearch.mapper.DocumentMapper;
import com.zjj.aisearch.mapper.UploadFileMapper;
import com.zjj.aisearch.model.ResponseResult;
import com.zjj.aisearch.pojo.dto.DocumentDTO;
import com.zjj.aisearch.pojo.dto.FullTextDTO;
import com.zjj.aisearch.repository.impl.DocumentESRepository;
import com.zjj.aisearch.service.UploadFileService;
import com.zjj.aisearch.utils.MultipartFileToFile;
import com.zjj.aisearch.utils.UploadFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @program: AISearch
 * @description: 上传文件
 * @author: zjj
 * @create: 2020-01-06 14:06:14
 **/
@Service
@Slf4j
public class UploadFileFastServiceImpl implements UploadFileService {
    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private UploadFileMapper uploadFileMapper;

    @Autowired
    private DocumentESRepository documentESRepository;

    @Override
    public int save(FullTextDTO fullTextDTO) {
        return uploadFileMapper.save(fullTextDTO);
    }

    @Override
    public int deleteFile(Integer id) {
        return uploadFileMapper.deleteFile(id);
    }

    @Override
    public ResponseResult uploadlocal(MultipartFile file) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        try {
            //提取文件信息进入数据库
            DocumentDTO documentDTO = new DocumentDTO();
            long size = file.getSize();
            Tika tika = new Tika();
            //考虑这儿会不会文件没传上去或者数据库没写入进去,事务就体现作用了
            //然后这儿开拓展多线程上传和下载,考虑用缓冲流什么的,下载的话需要nginx支持
            //然后多线程上传下载可以用不同的工具类,其实这些操作应该放在service中进行的,然后有无多线程,有无缓冲,用四个不同的service类来实现,体现多态的优势
            /*1、首先文件保存的SAVE方法放在数据库保存后面执行
            2、建立一个事务，首先进行数据库的保存，但是不要commit；然后save文件，当成功save后就commit，否则就会滚*/
            log.info("有缓冲start:" + System.currentTimeMillis());
            String s = UploadFileUtil.uploadFileFast("I:/document", file);
            log.info("有缓冲end:" + System.currentTimeMillis());
            File file1 = MultipartFileToFile.multipartFileToFile(file);
            String fileName = file1.getName();
            String filecontent = tika.parseToString(file1);
            MultipartFileToFile.delteTempFile(file1);
            documentDTO.setDocumentcontent(filecontent);
            documentDTO.setDocumentname(s);
            //保存到数据库
            documentMapper.insert(documentDTO);
            documentESRepository.save(documentDTO);
            responseResult.setStatus(0);
            responseResult.setMsg("上传成功,这是第" + documentDTO.getId() + "个文件");
        } catch (IOException e) {
            responseResult.setStatus(-1);
            responseResult.setMsg("上传失败");
            e.printStackTrace();
        }
        return responseResult;
    }

}
