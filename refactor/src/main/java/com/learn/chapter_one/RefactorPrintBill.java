package com.learn.chapter_one;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 重构过程的性能问题，建议是：大多数情况下可以忽略它。如果重构引入了性能损耗，先完成重构，再做性能优化
 * Create by liguo on 2022/7/12
 **/
public class RefactorPrintBill {
    private static final Map<String, Plays> playsMap = new HashMap<>();
    private static final Invoices invoices = new Invoices();
    static {
        playsMap.put("hamlet", new Plays("Hamlet", "tragedy"));
        playsMap.put("as-like", new Plays("As You Like It", "comedy"));
        playsMap.put("othello", new Plays("Othello", "tragedy"));

        invoices.setCustomer("BigCo");
        List<Performance> performances = Arrays.asList(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)
        );
        invoices.setPerformances(performances);
    }

    public static String statement() {
        StringBuilder result = new StringBuilder("Statement for " + invoices.getCustomer() + "\n");
        for (Performance performance : invoices.getPerformances()) {
            result.append("  ").append(playFor(performance).getName()).append(": ").append(amountFor(performance) / 100).append("￥(").append(performance.getAudience()).append(" seats)\n");
        }
        result.append("Amount owed is ").append(totalAmount() / 100).append("￥ \n");
        result.append("You earned ").append(totalVolumeCredits()).append(" credits\n");
        return result.toString();
    }

    /**
     * Step1:分解statement，提取费用方法
     */
    private static int amountFor(Performance performance) {
        int result;
        switch (playFor(performance).getType()) {
            case "tragedy":
                result = 40000;
                if (performance.getAudience() > 30) {
                    result += 1000 * (performance.getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (performance.getAudience() > 20) {
                    result += 10000 + 500 * (performance.getAudience() - 20);
                }
                result += 300 * performance.getAudience();
                break;
            default:
                throw new Error("unknown type: " + playFor(performance).getType());
        }
        return result;
    }

    /**
     * Step2:移除局部变量,让积分计算逻辑更简单
     */
    private static Plays playFor(Performance performance) {
        return playsMap.get(performance.getPalyId());
    }

    /**
     * Step3:分解statement，提取积分方法
     */
    private static int volumeCreditsFor(Performance performance) {
        // 积分
        int volumeCredits = 0;
        volumeCredits += Math.max(performance.getAudience() - 30, 0);
        if ("comedy".equals(playFor(performance).getType())) {
            volumeCredits += Math.floor(performance.getAudience() / 5);
        }
        return volumeCredits;
    }

    /**
     * Step4: 拆分循环，计算积分，内联变量
     */
    private static int totalVolumeCredits() {
        int volumeCredits = 0;
        for (Performance performance : invoices.getPerformances()) {
            volumeCredits += volumeCreditsFor(performance);
        }
        return volumeCredits;
    }

    /**
     * Step4: 拆分循环，计算费用，内联变量
     */
    private static int totalAmount() {
        // 费用
        int result = 0;
        for (Performance performance : invoices.getPerformances()) {
            result += amountFor(performance);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(statement());
    }
}
