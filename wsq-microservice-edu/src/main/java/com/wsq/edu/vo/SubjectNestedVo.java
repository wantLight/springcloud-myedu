package com.wsq.edu.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
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