package com.learn.chapter_six.extractMethod;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Create by liguo on 2022/7/19
 **/
@Data
public class Order implements Serializable {
    private String name;
    private BigDecimal price;
    private Integer amount;
}
