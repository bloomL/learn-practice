package com.learn.concurrent.concurrency_principle.chapter_one.one;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 *
 * 构造函数溢出问题
 * 1.volatile
 * 2.synchronized
 * 3.final
 *
 * Create by liguo on 2021/11/24
 **/
public class TestFinal {
    private int i;
    private int j;
    private static TestFinal obj;

    public TestFinal() {
        i = 6;
        j = 8;
    }

    public static void write() {
        // 三步骤 1.分配内存；2.内存上初始化i,j；3.obj指向这块内存
        obj = new TestFinal();
    }

    public static void read() {
        if (obj != null) {
            int a = obj.i;
            int b = obj.j;
            System.out.println("a: " + a + ",b: " + b );
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread writeThread = new Thread(TestFinal::write);
        Thread readThread = new Thread(TestFinal::read);
        writeThread.start();
        readThread.start();
    }
}
