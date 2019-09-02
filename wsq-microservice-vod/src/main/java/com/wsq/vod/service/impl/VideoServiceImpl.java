package com.wsq.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.wsq.common.constants.ResultCodeEnum;
import com.wsq.common.exception.MyException;
import com.wsq.vod.service.VideoService;
import com.wsq.vod.util.ConstantPropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-29 11:38
 */
@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    @Override
    public String uploadVideo(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        String title = fileName.substring(0, fileName.lastIndexOf("."));
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            //e.printStackTrace();
            throw new MyException(ResultCodeEnum.VIDEO_UPLOAD_TOMCAT_ERROR);
        }
        UploadStreamRequest request = new UploadStreamRequest(
                ConstantPropertiesUtil.ACCESS_KEY_ID,
                ConstantPropertiesUtil.ACCESS_KEY_SECRET,
                title,
                fileName,
                inputStream);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);

        String videoId = response.getVideoId();
        if (!response.isSuccess()) {
            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            if(StringUtils.isEmpty(videoId)){
                throw new MyException(ResultCodeEnum.VIDEO_UPLOAD_TOMCAT_ERROR);
            }
        }
        return videoId;
    }
}
