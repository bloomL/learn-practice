package com.learn.chapter_one;

/**
 * Create by liguo on 2022/7/12
 **/
public abstract class Calculate {

    abstract int calAmount(Performance performance);

    int calCredits(Performance performance) {
        return Math.max(performance.getAudience() - 30, 0);
    }

}
