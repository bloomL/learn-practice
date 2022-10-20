package com.learn.algorithm.chapter_two;

import com.learn.algorithm.chapter_two.section_one.InsertionSort;
import com.learn.algorithm.chapter_two.section_one.SelectionSort;
import lombok.Getter;

/**
 * @Description 算法类型
 * @Author liguo
 * @Date 2022/10/14 9:38
 **/
@Getter
public enum AlgorithmType {
    Selection("选择排序", InsertionSort.class),
    Insertion("插入排序", SelectionSort.class),
    ;
    private final String name;
    private final Class<?> aClass;

    AlgorithmType(String name, Class<?> aClass) {
        this.name = name;
        this.aClass = aClass;
    }
}
