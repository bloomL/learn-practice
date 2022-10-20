package com.learn.algorithm.chapter_two.section_two;

import com.learn.algorithm.chapter_two.ExampleSort;

/**
 * 自底向上的归并排序
 * Create by liguo on 2022/10/17
 **/
public class MergeUp extends ExampleSort {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        int length = a.length;
        aux = new Comparable[length];
        for (int sz = 1; sz < length; sz = sz + sz) {
            for (int lo = 0; lo < length - sz; lo += sz + sz) {
                meger(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, length - 1));
            }
        }
    }

    private static void meger(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi ; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(a[j], a[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }
}
