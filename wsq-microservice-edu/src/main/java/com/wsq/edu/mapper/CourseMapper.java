package com.wsq.edu.mapper;

import com.wsq.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsq.edu.vo.CoursePublishVo;
import com.wsq.edu.vo.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author xyzzg
 * @since 2019-08-15
 */
public interface CourseMapper extends BaseMapper<Course> {

    CoursePublishVo selectCoursePublishVoById(String id);

    CourseWebVo selectCourseWebVoById(String courseId);
}
