package com.learn.concurrent.beauty.chapter_one.local;

/**
 * Create by liguo on 2021/9/27
 **/
public class LocalDemo2 {
    static ThreadLocal<String> local = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        local.set("hello");
        Thread thread = new Thread(() -> {
            System.out.println("child: " + local.get());
        });

        thread.start();
        System.out.println("main: " + local.get());
    }
}
