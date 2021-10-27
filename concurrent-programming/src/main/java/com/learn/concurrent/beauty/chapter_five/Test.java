package com.learn.concurrent.beauty.chapter_five;

import java.util.Arrays;

/**
 * Create by liguo on 2021/10/8
 **/
public class Test {
    public static void main(String[] args) {
        int[] arr = {1,4,6,8,9,12};
        int length = arr.length;
        int index = 1;
        int numMoved = length - index - 1;
        int[] newArr = new int[length - 1];
        /**
         * src      the source array.
         * srcPos   starting position in the source array.
         * dest     the destination array.
         * destPos  starting position in the destination data.
         * length   the number of array elements to be copied.
         */
        System.arraycopy(arr, 0, newArr, 0, index);
        System.arraycopy(arr, index + 1, newArr, index, numMoved);
        Arrays.stream(newArr).forEach(System.out::println);
    }
}
