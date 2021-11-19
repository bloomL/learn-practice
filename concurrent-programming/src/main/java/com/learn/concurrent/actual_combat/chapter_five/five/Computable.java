package com.learn.concurrent.actual_combat.chapter_five.five;

/**
 * @Description
 * @Author liguo
 * @Date 2021/11/3 16:19
 **/
interface Computable <A, V> {
    V compute(A arg) throws InterruptedException;
}
