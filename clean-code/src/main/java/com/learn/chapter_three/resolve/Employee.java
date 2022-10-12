package com.learn.chapter_three.resolve;

import cn.hutool.core.math.Money;

/**
 * Create by liguo on 2022/8/16
 **/
public abstract class Employee {
    abstract boolean isPayday();
    abstract Money calculatePay();
    abstract void deliverPay(Money pay);
}
