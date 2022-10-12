package com.learn.chapter_six.extractMethod;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Create by liguo on 2022/7/19
 **/
@Data
public class Invoice implements Serializable {
    private String customer;
    private List<Order> orders;
}
