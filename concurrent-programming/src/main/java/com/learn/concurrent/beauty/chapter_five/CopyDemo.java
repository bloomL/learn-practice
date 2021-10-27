package com.learn.concurrent.beauty.chapter_five;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Create by liguo on 2021/10/8
 * 弱一致性的迭代器
 **/
public class CopyDemo {
    private static volatile CopyOnWriteArrayList<String> copy = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        copy.add("hello");
        copy.add("world");
        copy.add("I");
        copy.add("am");
        copy.add("fine");

        Thread threadA = new Thread(() -> {
            copy.set(1,"java");
            copy.remove(2);
            copy.remove(3);
        });

        // 修改线程前启动前获取迭代器
        Iterator<String> iterator = copy.iterator();

        threadA.start();
        threadA.join();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
