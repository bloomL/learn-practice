package com.learn.concurrent.beauty.chapter_eleven;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create by liguo on 2021/10/25
 **/
public class ConcurrentHashMapTest {
    public static ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Thread one = new Thread(() -> {
            List<String> list = new ArrayList<>();
            list.add("device1");
            list.add("device2");
            List<String> old = map.putIfAbsent("topic1", list);
            if (CollUtil.isNotEmpty(old)) {
                old.addAll(list);
            }
            System.out.println(JSON.toJSONString(map));
        });

        Thread two = new Thread(() -> {
            List<String> list = new ArrayList<>();
            list.add("device11");
            list.add("device22");
            List<String> old = map.putIfAbsent("topic1", list);
            if (CollUtil.isNotEmpty(old)) {
                old.addAll(list);
            }
            System.out.println(JSON.toJSONString(map));
        });

        Thread three = new Thread(() -> {
            List<String> list = new ArrayList<>();
            list.add("device111");
            list.add("device222");
            List<String> old = map.putIfAbsent("topic2", list);
            if (CollUtil.isNotEmpty(old)) {
                old.addAll(list);
            }
            System.out.println(JSON.toJSONString(map));
        });

        one.start();
        two.start();
        three.start();
    }
}
