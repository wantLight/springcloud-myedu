package com.wsq.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsq.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsq.edu.query.TeacherQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author xyzzg
 * @since 2019-08-15
 */
public interface TeacherService extends IService<Teacher> {

    void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery);

    public Map<String, Object> pageListWeb(Page<Teacher> pageParam);
}
