package com.wsq.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsq.common.exception.MyException;
import com.wsq.edu.entity.Chapter;
import com.wsq.edu.entity.Course;
import com.wsq.edu.entity.CourseDescription;
import com.wsq.edu.entity.Video;
import com.wsq.edu.form.CourseInfoForm;
import com.wsq.edu.mapper.ChapterMapper;
import com.wsq.edu.mapper.CourseDescriptionMapper;
import com.wsq.edu.mapper.CourseMapper;
import com.wsq.edu.mapper.VideoMapper;
import com.wsq.edu.query.CourseQuery;
import com.wsq.edu.service.CourseDescriptionService;
import com.wsq.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsq.edu.vo.CoursePublishVo;
import com.wsq.edu.vo.CourseWebVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xyzzg
 * @since 2019-08-15
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private ChapterMapper chapterMapper;

    @Transactional
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {

        //保存课程基本信息
        Course course = new Course();
        course.setStatus(Course.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm, course);
        baseMapper.insert(course);

        //保存课程详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionMapper.insert(courseDescription);

        return course.getId();
    }

    @Override
    public CourseInfoForm getCourseInfoFormById(String id) {

        //从course表中取数据
        Course course = baseMapper.selectById(id);
        if(course == null){
            throw new MyException(20001, "数据不存在");
        }

        //从course_description表中取数据
        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);
        if(courseDescription == null){
            throw new MyException(20001, "数据不完整");
        }

        //创建courseInfoForm对象
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course, courseInfoForm);
        BeanUtils.copyProperties(courseDescription, courseInfoForm);

        return courseInfoForm;
    }

    @Transactional
    @Override
    public void updateCourseInfoById(CourseInfoForm courseInfoForm) {
        //保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm, course);
        baseMapper.updateById(course);

        //保存课程详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescriptionMapper.updateById(courseDescription);
    }

    @Override
    public void pageQuery(Page<Course> pageParam, CourseQuery courseQuery) {

        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");

        if (courseQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(teacherId) ) {
            queryWrapper.eq("teacher_id", teacherId);
        }

        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.ge("subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.ge("subject_id", subjectId);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }


    @Transactional
    @Override
    public boolean removeCourseById(String id) {

        //根据id删除所有视频
        QueryWrapper<Video> queryWrapperVideo = new QueryWrapper<>();
        queryWrapperVideo.eq("course_id", id);
        videoMapper.delete(queryWrapperVideo);

        //根据id删除所有章节
        QueryWrapper<Chapter> queryWrapperChapter = new QueryWrapper<>();
        queryWrapperChapter.eq("course_id", id);
        chapterMapper.delete(queryWrapperChapter);

        //删除课程详情
        courseDescriptionMapper.deleteById(id);

        //根据id删除课程
        baseMapper.deleteById(id);

        return true;
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        return baseMapper.selectCoursePublishVoById(id);
    }

    @Override
    public void publishCourseById(String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(Course.COURSE_NORMAL);
        baseMapper.updateById(course);
    }

    /**
     * 根据讲师id查询当前讲师的课程列表
     * @param teacherId
     * @return
     */
    @Override
    public List<Course> selectByTeacherId(String teacherId) {

        QueryWrapper<Course> queryWrapper = new QueryWrapper<Course>();

        queryWrapper.eq("teacher_id", teacherId);
        //按照最后更新时间倒序排列
        queryWrapper.orderByDesc("gmt_modified");

        List<Course> courses = baseMapper.selectList(queryWrapper);
        return courses;
    }

    @Override
    public Map<String, Object> pageListWeb(Page<Course> pageParam) {

        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_modified");

        baseMapper.selectPage(pageParam, queryWrapper);

        List<Course> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Transactional
    @Override
    public CourseWebVo selectCourseWebVoById(String id) {
        //更新课程浏览数
        Course course = baseMapper.selectById(id);
        course.setViewCount(course.getViewCount() + 1);
        baseMapper.updateById(course);
        //获取课程信息
        return baseMapper.selectCourseWebVoById(id);
    }

}
