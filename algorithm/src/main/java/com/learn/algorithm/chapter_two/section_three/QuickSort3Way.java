package com.learn.algorithm.chapter_two.section_three;

import com.learn.algorithm.chapter_two.ExampleSort;
import com.learn.algorithm.chapter_two.section_one.InsertionSort;

/**
 * 三向切分的快速排序
 * Create by liguo on 2022/10/17
 **/
public class QuickSort3Way extends ExampleSort {
    // 适合插入排序的数组大小 5-15任意值
    private static final Integer M = 6;
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + M) {
            InsertionSort.sort(a);
        }
        int lt = lo;
        int i = lo + 1;
        int gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0 ) {
                exch(a, lt++, i++);
            } else if (cmp > 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
}
