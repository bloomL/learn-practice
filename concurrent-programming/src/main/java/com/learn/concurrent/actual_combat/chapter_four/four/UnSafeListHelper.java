package com.learn.concurrent.actual_combat.chapter_four.four;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 线程不安全，使用的锁不是同一把锁
 * 使用客户端加锁或外部加锁使用同一把锁
 * Create by liguo on 2021/11/1
 **/
public class UnSafeListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());


    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent) {
            list.add(x);
        }
        return absent;
    }
}
