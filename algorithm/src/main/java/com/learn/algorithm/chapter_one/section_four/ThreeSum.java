package com.learn.algorithm.chapter_one.section_four;

import com.learn.algorithm.chapter_one.section_one.BinarySearch;

/**
 * 第一章
 * 第一节
 * N个数中找出三个和为0的整数数组的个数
 * Create by liguo on 2022/10/13
 **/
public class ThreeSum {

    /**
     * N*N*N
     */
    public int count(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    /**
     * N*N*logN
     */
    public int countImprove(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (BinarySearch.rank(-(a[i] + a[j]), a) > j) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
