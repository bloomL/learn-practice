package com.learn.concurrent.actual_combat.chapter_four;

/**
 * Create by liguo on 2021/11/1
 **/
public class MutablePoint {
    public int x;
    public int y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
