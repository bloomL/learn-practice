package com.learn.concurrent.actual_combat.chapter_four.four;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 使用客户端加锁
 * Create by liguo on 2021/11/1
 **/
public class SafeListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());


    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent) {
                list.add(x);
            }
            return absent;
        }
    }
}
