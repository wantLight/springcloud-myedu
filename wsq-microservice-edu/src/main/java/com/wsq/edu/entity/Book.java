package com.wsq.edu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-10-10 9:16
 */
@Data
@AllArgsConstructor
public class Book {
    private Integer id;
    private Integer userId;
    private String name;
}