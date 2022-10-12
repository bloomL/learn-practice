package com.learn.chapter_one;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 剧目
 * Create by liguo on 2022/7/8
 **/
@Data
@AllArgsConstructor
public class Plays implements Serializable {
    private String name;
    private String type;
}
