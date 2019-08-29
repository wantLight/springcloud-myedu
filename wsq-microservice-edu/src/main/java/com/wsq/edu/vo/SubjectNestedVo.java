package com.wsq.edu.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 在项目中很多时候需要把model转换成dto用于网站信息的展示，按前端的需要传递对象的数据，
 * 保证model对外是隐私的，例如密码之类的属性能很好地避免暴露在外，同时也会减小数据传输的体积。
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-21 21:13
 */
@Data
public class SubjectNestedVo {

    private String id;
    private String title;
    private List<SubjectVo> children = new ArrayList<>();
}