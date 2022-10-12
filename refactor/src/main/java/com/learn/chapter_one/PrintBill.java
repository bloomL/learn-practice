package com.learn.chapter_one;

import java.util.*;


/**
 * Create by liguo on 2022/7/8
 **/
public class PrintBill {

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
        // 费用
        int totalAmount = 0;
        // 积分
        int volumeCredits = 0;
        StringBuilder result = new StringBuilder("Statement for " + invoices.getCustomer() + "\n");
        for (Performance performance : invoices.getPerformances()) {
            Plays plays = playsMap.get(performance.getPalyId());
            int thisAmount = 0;
            switch (plays.getType()) {
                case "tragedy":
                    thisAmount = 40000;
                    if (performance.getAudience() > 30) {
                        thisAmount += 1000 * (performance.getAudience() - 30);
                    }
                    break;
                case "comedy":
                    thisAmount = 30000;
                    if (performance.getAudience() > 20) {
                        thisAmount += 10000 + 500 * (performance.getAudience() - 20);
                    }
                    thisAmount += 300 * performance.getAudience();
                    break;
                default:
                    throw new Error("unknown type: " + plays.getType());
            }
            volumeCredits += Math.max(performance.getAudience() - 30, 0);
            if ("comedy".equals(plays.getType())) {
                volumeCredits += Math.floor(performance.getAudience() / 5);
            }
            result.append("  ").append(plays.getName()).append(": ").append(thisAmount / 100).append("￥(").append(performance.getAudience()).append(" seats)\n");
            totalAmount += thisAmount;
        }
        result.append("Amount owed is ").append(totalAmount / 100).append("￥ \n");
        result.append("You earned ").append(volumeCredits).append(" credits\n");
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(statement());
    }
}
