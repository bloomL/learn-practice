package com.learn.algorithm.chapter_two.section_three;

import com.learn.algorithm.chapter_two.ExampleSort;
import com.learn.algorithm.chapter_two.section_one.InsertionSort;

/**
 * 快速排序，分治的排序算法
 * Create by liguo on 2022/10/17
 **/
public class QuickSort extends ExampleSort {
    // 适合插入排序的数组大小 5-15任意值
    private static final Integer M = 6;
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + M) {
            InsertionSort.sort(a);
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    /**
     * 切分
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        // 左扫描指针
        int i = lo;
        // 右扫描指针
        int j = hi + 1;
        // 切分元素
        Comparable v = a[i];
        while(true) {
            while(less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
}
