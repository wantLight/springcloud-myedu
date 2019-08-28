package com.wsq.edu.service;

import com.wsq.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsq.edu.form.CourseInfoForm;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xyzzg
 * @since 2019-08-15
 */
public interface CourseService extends IService<Course> {

    /**
     * 保存课程和课程详情信息
     * @param courseInfoForm
     * @return 新生成的课程id
     */
    String saveCourseInfo(CourseInfoForm courseInfoForm);
}
