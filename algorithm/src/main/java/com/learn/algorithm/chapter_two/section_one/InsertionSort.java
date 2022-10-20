package com.learn.algorithm.chapter_two.section_one;

import com.learn.algorithm.chapter_two.ExampleSort;

/**
 * 插入排序。为了给要插入的元素腾空间，需将其余所有元素在插入前都右移一位
 * 有效的典型数组：
 * 1.每个元素离它的最终位置都不远
 * 2.一个有序大数组接一个小数组
 * 3.数组中只有几个元素的位置不确定
 * Create by liguo on 2022/10/13
 **/
public class InsertionSort extends ExampleSort {

    public static void sort(Comparable[] a) {
        int length = a.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j -1]) ; j--) {
                exch(a, j, j-1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{3,5,4,7,6,8,9,12,34,50};
        sort(a);
        show(a);
    }
}
