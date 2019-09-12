package com.wsq.edu.controller;


import com.wsq.edu.rabbitmq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author xyzzg
 * @since 2019-08-15
 */
@RestController
@RequestMapping("/edu/video")
public class VideoController {

    @Autowired
    private Sender sender;

    @RequestMapping("/send/{name}")
    public String helloworld(@PathVariable String name) {
        return sender.send(name);
    }
}

