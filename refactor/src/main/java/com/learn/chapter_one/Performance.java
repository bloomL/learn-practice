package com.learn.chapter_one;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * Create by liguo on 2022/7/8
 **/
@Data
@AllArgsConstructor
public class Performance implements Serializable {
    private String palyId;
    private Integer audience;
    private Integer amount;
    private Integer credits;

    public Performance(String palyId, Integer audience) {
        this.palyId = palyId;
        this.audience = audience;
    }
}
