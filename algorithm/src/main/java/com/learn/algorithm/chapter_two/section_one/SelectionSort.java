package com.learn.algorithm.chapter_two.section_one;

import com.learn.algorithm.chapter_two.ExampleSort;

/**
 * 选择排序。找到数组最小元素，然后与数组第一个元素交换位置。再次，在剩下数组找到最小元素，然后与数组第二个元素交换位置，直到整个数组排序
 * Create by liguo on 2022/10/13
 **/
public class SelectionSort extends ExampleSort {
    public static void sort(Comparable[] a) {
        int length = a.length;
        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i , min);
        }
    }

    public static void main(String[] args) {
        System.out.println(SelectionSort.class.isInstance(ExampleSort.class));
        /*Integer[] a = new Integer[]{3,5,4,7,6,8,9,12,34,50};
        sort(a);
        show(a);*/
    }


}
