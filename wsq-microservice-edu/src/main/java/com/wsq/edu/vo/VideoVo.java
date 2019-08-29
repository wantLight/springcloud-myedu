package com.wsq.edu.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-29 10:11
 */
@ApiModel(value = "章节信息")
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
}