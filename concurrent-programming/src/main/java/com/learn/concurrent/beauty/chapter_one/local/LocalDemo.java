package com.learn.concurrent.beauty.chapter_one.local;

/**
 * Create by liguo on 2021/9/26
 **/
public class LocalDemo {
    static ThreadLocal<String> local = new ThreadLocal<>();

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            local.set("threadA");
            print("threadA");
            System.out.println("threadA remove after : "+ local.get());
        });

        Thread threadB = new Thread(() -> {
            local.set("threadB");
            print("threadB");
            System.out.println("threadB remove after : "+ local.get());
        });

        threadA.start();
        threadB.start();
    }

    private static void print(String str) {
        // 打印变量值
        System.out.println(str + " : " + local.get());
        // 清除本地内存中的值
        local.remove();
    }
}
