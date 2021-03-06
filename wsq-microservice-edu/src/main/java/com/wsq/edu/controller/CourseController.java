package com.wsq.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsq.common.vo.R;
import com.wsq.edu.entity.Course;
import com.wsq.edu.service.ChapterService;
import com.wsq.edu.service.CourseService;
import com.wsq.edu.vo.ChapterVo;
import com.wsq.edu.vo.CourseWebVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xyzzg
 * @since 2019-08-15
 */
@RestController
@RequestMapping("/edu/course")
@CrossOrigin //跨域
@Api(tags={"课程模块"})
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @ApiOperation(value = "分页课程列表")
    @GetMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<Course> pageParam = new Page<Course>(page, limit);

        Map<String, Object> map = courseService.pageListWeb(pageParam);

        return  R.ok().data(map);
    }



    @ApiOperation(value = "根据ID查询课程")
    @GetMapping(value = "{courseId}")
    public R getById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){

        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = courseService.selectCourseWebVoById(courseId);

        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);

        return R.ok().data("course", courseWebVo).data("chapterVoList", chapterVoList);
    }

}

