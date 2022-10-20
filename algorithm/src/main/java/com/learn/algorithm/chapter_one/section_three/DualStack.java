package com.learn.algorithm.chapter_one.section_three;

import cn.hutool.core.util.StrUtil;

import java.util.Stack;

/**
 * 双栈实现算术算法
 * Create by liguo on 2022/10/12
 **/
public class DualStack {
    public static void main(String[] args) {
        Stack<String> operators = new Stack<>();
        Stack<Double> vals = new Stack<>();
        String input = "(1 + ((2 + 3) * (4 * 5)))";
        for (int i = 0; i < input.length(); i++) {
            String val = StrUtil.sub(input, i,  i + 1);
            if (StrUtil.isNotBlank(val)) {
                if (StrUtil.equals("(", val)) {

                } else if (isOperator(val)) {
                    operators.push(val);
                } else if (StrUtil.equals(")", val)) {
                    Double pop = vals.pop();
                    String operator = operators.pop();
                    Double result = calculate(vals, pop, operator);
                    vals.push(result);
                } else {
                    vals.push(Double.valueOf(val));
                }
            }
        }
        System.out.println(vals.pop());
    }

    private static Boolean isOperator(String param) {
        return StrUtil.equalsAny(param, "+", "-", "*", "/", "sqrt");
    }

    private static Double calculate(Stack<Double> vals, Double value, String operator) {
        if (StrUtil.equals("+", operator)) {
            return value + vals.pop();
        }
        if (StrUtil.equals("-", operator)) {
            return value - vals.pop();
        }
        if (StrUtil.equals("*", operator)) {
            return value * vals.pop();
        }
        if (StrUtil.equals("/", operator)) {
            return value / vals.pop();
        }
        if (StrUtil.equals("sqrt", operator)) {
            return Math.sqrt(value);
        }
        return null;
    }
}
