package com.learn.chapter_one;

/**
 * Create by liguo on 2022/7/12
 **/
public class CalComedy extends Calculate{
    @Override
    int calAmount(Performance performance) {
        int result = 30000;
        if (performance.getAudience() > 20) {
            result += 10000 + 500 * (performance.getAudience() - 20);
        }
        result += 300 * performance.getAudience();
        return result;
    }

    @Override
    int calCredits(Performance performance) {
        return  (super.calCredits(performance) + (int) Math.floor(performance.getAudience() / 5));
    }
}
