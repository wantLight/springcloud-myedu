package com.wsq.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-29 11:38
 */
public interface VideoService {
    String uploadVideo(MultipartFile file);
}
