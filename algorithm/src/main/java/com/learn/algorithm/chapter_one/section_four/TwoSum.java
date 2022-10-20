package com.learn.algorithm.chapter_one.section_four;

import com.learn.algorithm.chapter_one.section_one.BinarySearch;

import java.util.Arrays;

/**
 * Create by liguo on 2022/10/13
 **/
public class TwoSum {

    /**
     * N的2次方
     */
    public int count(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (a[i] + a[j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    /**
     * N*logN
     * @param a
     * @return
     */
    public int countImprove(int[] a) {
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (BinarySearch.rank(-a[i], a) > i) {
                cnt++;
            }
        }
        return cnt;
    }
}
