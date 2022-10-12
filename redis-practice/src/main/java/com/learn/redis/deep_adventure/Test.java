package com.learn.redis.deep_adventure;

/**
 * Create by liguo on 2021/12/20
 **/
public class Test {
    public static void main(String[] args) {
        // hello
        String msg = "hello";
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            System.out.println(c + " : " + Integer.toBinaryString(c));
        }
        //h : 1101000
        //e : 1100101
        //l : 1101100
        //l : 1101100
        //o : 1101111
    }
}
