package com.learn.algorithm.chapter_two.section_one;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.learn.algorithm.chapter_two.StopWatch;


/**
 * Create by liguo on 2022/10/14
 **/
public class SortCompare {
    public static Double time(String alg, Comparable[] a) {
        StopWatch timer = new StopWatch();
        if (StrUtil.equals(alg, "Insertion")) {
            InsertionSort.sort(a);
        } else if (StrUtil.equals(alg, "Selection")) {
            SelectionSort.sort(a);
        } else if (StrUtil.equals(alg, "Shell")) {
            ShellSort.sort(a);
        }
        return timer.elapsedTime();
    }

    /**
     *
     * @param alt 算法
     * @param n 数组长度
     * @param t 数组数量
     * @return
     */
    public static double timeRandomInput(String alt, int n, int t) {
        double total = 0.0;
        Double[] a = new Double[n];
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < n; j++) {
                a[j] = RandomUtil.randomDouble();
            }
            total += time(alt, a);
        }
        return total;
    }

    public static void main(String[] args) {
        /*String alg1 = "Insertion";
        String alg2 = "Selection";*/
        String alg1 = "shell";
        String alg2 = "Insertion";
        int arrLength = 100;
        int arrAmount = 1;
        // 算法1的总时间
        double t1 = timeRandomInput(alg1, arrLength, arrAmount);
        // 算法2的总时间
        double t2 = timeRandomInput(alg2, arrLength, arrAmount);
        System.out.println(arrLength + " random Doubles, " + alg1 + " is " + t2/t1 + " times faster than " + alg2);
    }
}
