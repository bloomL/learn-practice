package com.learn.concurrent.beauty.chapter_eleven;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Create by liguo on 2021/10/25
 **/
public class ConcurrentHashMapTest2 {
    public static ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ThreadFactory nameFactory = new ThreadFactoryBuilder().setNameFormat("concurrentHashMap-pool-%d").build();

        ExecutorService pool = new ThreadPoolExecutor(2,
                2,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                nameFactory,
                new ThreadPoolExecutor.AbortPolicy());

        pool.submit(() -> {
            List<String> list = new ArrayList<>();
            list.add("device1");
            list.add("device2");
            List<String> old = map.putIfAbsent("topic1", list);
            if (CollUtil.isNotEmpty(old)) {
                old.addAll(list);
            }
            System.out.println(JSON.toJSONString(map));
        });

        pool.submit(() -> {
            List<String> list = new ArrayList<>();
            list.add("device11");
            list.add("device22");
            List<String> old = map.putIfAbsent("topic1", list);
            if (CollUtil.isNotEmpty(old)) {
                old.addAll(list);
            }
            System.out.println(JSON.toJSONString(map));
        });

        pool.submit(() -> {
            List<String> list = new ArrayList<>();
            list.add("device111");
            list.add("device222");
            List<String> old = map.putIfAbsent("topic2", list);
            if (CollUtil.isNotEmpty(old)) {
                old.addAll(list);
            }
            System.out.println(JSON.toJSONString(map));
        });

        pool.shutdown();
    }
}
