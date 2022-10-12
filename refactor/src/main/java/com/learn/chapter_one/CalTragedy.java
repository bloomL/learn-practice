package com.learn.chapter_one;

/**
 * Create by liguo on 2022/7/12
 **/
public class CalTragedy extends Calculate{
    @Override
    int calAmount(Performance performance) {
        int result = 40000;
        if (performance.getAudience() > 30) {
            result += 1000 * (performance.getAudience() - 30);
        }
        return result;
    }
}
