package com.learn.chapter_one;

import java.util.*;

/**
 * Create by liguo on 2022/7/12
 **/
public class finalPrintBill {

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

    private static String htmlstatement() {
        StatementData data = createStatementData();
        String result = "<h1>Statement for " + data.getCustomer() + "</h1>\n";
        result += "<table>\n";
        result += "<tr><th>play</th><th>seats</th><th>cost</th></tr>\n";
        for (Performance performances : data.getPerformances()) {
            result += "<tr><td>" + playFor(performances).getName() + "</td><td>" + performances.getAudience() + "</td>";
            result += "<td>" + performances.getAmount() / 100 + "￥</td></tr>\n";
        }
        result += "</table>\n";
        result += "<p>Amount owed is <em>" + data.getTotalAmount()/ 100  + "￥</em></p>\n";
        result += "<p>You earned <em>" + data.getTotalVolumeCredits() + "</em> credits</p>\n";
        return result;
    }

    private static String statement() {
        StatementData data = createStatementData();
        String result = "Statement for " + data.getCustomer() + "\n";

        for (Performance performances : data.getPerformances()) {
            result += "  " + playFor(performances).getName() + ": " + performances.getAmount() / 100 + "￥(" + performances.getAudience() + " seats)\n";
        }
        result += "Amount owed is " + data.getTotalAmount() / 100 + "￥ \n";
        result += "You earned " + data.getTotalVolumeCredits() + " credits\n";
        return result;
    }

    private static StatementData createStatementData() {
        StatementData result = new StatementData();
        int totalAmount = 0;
        int volumeCredits = 0;

        List<Performance> performancesList = initPerformanceData();
        for (Performance performance : performancesList) {
            totalAmount += performance.getAmount();
            volumeCredits += performance.getCredits();
        }
        result.setTotalAmount(totalAmount);
        result.setTotalVolumeCredits(volumeCredits);
        result.setCustomer(invoices.getCustomer());
        result.setPerformances(performancesList);
        return result;
    }

    private static List<Performance> initPerformanceData() {
        List<Performance> performanceList = invoices.getPerformances();
        for (Performance performance : performanceList) {
            Calculate calculate = selectType(performance);
            performance.setAmount(calculate.calAmount(performance));
            performance.setCredits(calculate.calCredits(performance));
        }
        return performanceList;
    }

    private static Plays playFor(Performance performance) {
        return playsMap.get(performance.getPalyId());
    }

    private static Calculate selectType(Performance performance) {
        switch (playFor(performance).getType()) {
            case "tragedy":
                return new CalTragedy();
            case "comedy":
                return new CalComedy();
            default:
                throw new Error("unknown type " + playFor(performance).getType());
        }
    }

    public static void main(String[] args) {
        System.out.println(statement());
    }

}
