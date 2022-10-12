package com.learn.chapter_one;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 账单
 * Create by liguo on 2022/7/8
 **/
@Data
public class Invoices implements Serializable {
    private String customer;
    private List<Performance> performances;
}
