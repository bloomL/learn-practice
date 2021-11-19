package com.learn.concurrent.actual_combat.chapter_five.five;

import java.math.BigInteger;

/**
 * Create by liguo on 2021/11/3
 **/
public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) {
        // after deep thought...
        return new BigInteger(arg);
    }
}
