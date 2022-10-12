package com.learn.chapter_three.resolve;

import cn.hutool.core.math.Money;

/**
 * Create by liguo on 2022/8/16
 **/
public class CommissionedEmployee extends Employee{
    public CommissionedEmployee(EmployeeRecord r) {
        super();
    }

    @Override
    boolean isPayday() {
        return false;
    }

    @Override
    Money calculatePay() {
        return null;
    }

    @Override
    void deliverPay(Money pay) {

    }
}
