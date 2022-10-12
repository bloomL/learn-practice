package com.learn.chapter_three.old;

import lombok.Getter;

/**
 * Create by liguo on 2022/8/16
 **/
@Getter
public enum EmployeeTypeEnum {
    COMMISSIONED(1, "commissioned"),
    HOURLY(2, "hourly"),
    SALARIED(3, "salaried");

    private int value;
    private String name;

    EmployeeTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
