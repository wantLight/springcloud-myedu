package com.wsq.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsq.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsq.edu.form.CourseInfoForm;
import com.wsq.edu.query.CourseQuery;
import com.wsq.edu.vo.CoursePublishVo;
import com.wsq.edu.vo.CourseWebVo;

import java.util.List;
import java.util.Map;

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

    CourseInfoForm getCourseInfoFormById(String id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    void pageQuery(Page<Course> pageParam, CourseQuery courseQuery);

    boolean removeCourseById(String id);

    CoursePublishVo getCoursePublishVoById(String id);

    void publishCourseById(String id);

    List<Course> selectByTeacherId(String teacherId);

    Map<String, Object> pageListWeb(Page<Course> pageParam);

    /**
     * 获取课程信息
     * @param id
     * @return
     */
    CourseWebVo selectCourseWebVoById(String id);

}
