package com.learn.algorithm.chapter_two.section_two;

import com.learn.algorithm.chapter_two.ExampleSort;

/**
 * 自顶向下的归并排序
 * Create by liguo on 2022/10/14
 **/
public class Merge extends ExampleSort {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length -1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = (lo + hi) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        // 归并结果
        merge(a, lo, mid, hi);
    }

    /**
     * 左半边用尽（取右半边的元素）、右半边用尽（取左半边的元素）、
     * 右半边的当前元素小于左半边的当前元素（取右半边的元素）以及右半边的当前元素大于等于左半边的当前元素（取左半边的元素）
     */
    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{3,5,4,7,6,8,9,12,50,30};
        sort(a);
        show(a);
    }
}
