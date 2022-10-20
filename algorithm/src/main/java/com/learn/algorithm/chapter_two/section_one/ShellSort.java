package com.learn.algorithm.chapter_two.section_one;

import com.learn.algorithm.chapter_two.ExampleSort;

/**
 * 希尔排序，基于插入排序的快速的排序算法
 * 交换不相邻的元素以对数组的局部进行排序，并最终用插入排序将局部有序的数组排序
 * 思想：使数组中任意间隔为h的元素都是有序的
 * Create by liguo on 2022/10/14
 **/
public class ShellSort extends ExampleSort {
    public static void sort(Comparable[] a) {
        int length = a.length;
        int h = 1;
        while(h < length / 3) {
            h = 3*h + 1;
        }
        while(h >= 1) {
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{3,5,4,7,6,8,9,12,50,30};
        sort(a);
        show(a);
    }
}
