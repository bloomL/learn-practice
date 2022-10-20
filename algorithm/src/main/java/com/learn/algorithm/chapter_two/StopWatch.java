package com.learn.algorithm.chapter_two;

/**
 * Create by liguo on 2022/10/14
 **/
public class StopWatch {
    private final long start;

    public StopWatch() {
        start = System.currentTimeMillis();
    }

    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }
}
