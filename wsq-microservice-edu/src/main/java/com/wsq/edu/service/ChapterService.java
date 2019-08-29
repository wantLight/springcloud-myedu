package com.wsq.edu.service;

import com.wsq.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsq.edu.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xyzzg
 * @since 2019-08-15
 */
public interface ChapterService extends IService<Chapter> {


    void removeChapterById(String id);

    List<ChapterVo> nestedList(String courseId);

}
