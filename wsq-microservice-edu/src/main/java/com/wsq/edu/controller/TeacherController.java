package com.wsq.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsq.common.vo.R;
import com.wsq.edu.entity.Course;
import com.wsq.edu.entity.Teacher;
import com.wsq.edu.service.CourseService;
import com.wsq.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author xyzzg
 * @since 2019-08-15
 */
/*
 * 在swagger-annotations jar包中 1.5.X版本以上, 注解 io.swagger.annotations.API
 * 中的description被废弃了。新的swagger组件中使用了新的方法来对Web api 进行分组。原来使用 description ，
 * 默认一个Controller类中包含的方法构成一 个api分组。现在使用tag，可以更加方便的分组。
 * 比如把两个Controller类里的方法划分成同一个分组。tag的key用来区分不同的分组。tag的value用做分组的描述。
 * @ApiOperation 中value是api的简要说明，在界面api 链接的右侧，少于120个字符。
 * @ApiOperation 中notes是api的详细说明，需要点开api 链接才能看到。
 * @ApiOperation 中 produces 用来标记api返回值的具体类型。这里是json格式，utf8编码。
 */
@Api(tags={"讲师"})
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "分页讲师列表")
    @GetMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<Teacher> pageParam = new Page<Teacher>(page, limit);

        Map<String, Object> map = teacherService.pageListWeb(pageParam);

        return  R.ok().data(map);
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping(value = "{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        //查询讲师信息
        Teacher teacher = teacherService.getById(id);

        //根据讲师id查询这个讲师的课程列表
        List<Course> courseList = courseService.selectByTeacherId(id);

        return R.ok().data("teacher", teacher).data("courseList", courseList);
    }

}

