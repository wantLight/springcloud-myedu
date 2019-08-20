package com.wsq.aliyunoss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-20 18:36
 */
public interface FileService {

    /**
     * 文件上传至阿里云
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}