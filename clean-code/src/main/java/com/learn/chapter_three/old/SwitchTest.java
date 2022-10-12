package com.learn.chapter_three.old;

import cn.hutool.core.math.Money;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by liguo on 2022/8/16
 **/
public class SwitchTest {

    /**
     * 违反单一权责原则，违反开放闭合原则（对扩展开放，对修改关闭）
     * @param e
     * @return
     */
    public Money calculatePay(Employee e) {
        switch (e.getType()) {
            case COMMISSIONED:
                return calculateCommissionedPay(e);
            case HOURLY:
                return calculateHourlyPay(e);
            case SALARIED:
                return calculateSalariedPay(e);
            default:
                throw new RuntimeException(e.getType().getName());
        }
    }

    public Money calculateCommissionedPay(Employee employee) {
        return null;
    }

    public Money calculateHourlyPay(Employee employee) {
        return null;
    }

    public Money calculateSalariedPay(Employee employee) {
        return null;
    }
}
