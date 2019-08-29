package com.wsq.edu.service;

import com.wsq.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsq.edu.form.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author xyzzg
 * @since 2019-08-15
 */
public interface VideoService extends IService<Video> {

    void saveVideoInfo(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoInfoFormById(String id);

    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    void removeVideoById(String id);
}
