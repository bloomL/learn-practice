package com.learn.algorithm.chapter_one.section_one;

/**
 * Create by liguo on 2022/10/13
 **/
public class BinarySearch {

    /**
     *
     * @param k
     * @param a 有序数组
     * @return
     */
    public static int rank(int k, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            if (k < a[mid]) {
                hi = mid -1;
            } else if (k > a[mid]) {
                lo = mid +1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
