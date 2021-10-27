package com.learn.concurrent.beauty.chapter_eleven;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create by liguo on 2021/10/26
 **/
public class ThreadLocalTest {

    static class LocalVariable {
        private Long[] a = new Long[1024 * 1024];
    }

    private final static ThreadPoolExecutor pool = new ThreadPoolExecutor(5,
            5,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>());

    private final static ThreadLocal<LocalVariable> LOCAL_VARIABLE = new ThreadLocal<>();

    private final static int SIZE = 50;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < SIZE; i++) {
            pool.execute(() -> {
                LOCAL_VARIABLE.set(new LocalVariable());
                System.out.println("use local variable");
                //没有清理，导致5个核心线程的threadLocals变量中的new LocalVariable()没被释放；会造成内泄漏，
                LOCAL_VARIABLE.remove();
            });
            Thread.sleep(1000);
        }
        System.out.println("pool execute over");
    }
}
