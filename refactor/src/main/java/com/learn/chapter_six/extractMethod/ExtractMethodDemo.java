package com.learn.chapter_six.extractMethod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 提炼方法
 * 反向重构：内联方法
 * 创造一个新函数，根据这个函数的意图来对它命名（做什么）
 * Create by liguo on 2022/7/19
 **/
public class ExtractMethodDemo{
    private static void printOwing(Invoice invoice) {
        // Step1
        printBanner();
        // Step2
        int outstanding = calculateOutstanding(invoice);
        // Step3
        printDetails(invoice, outstanding);
    }

    private static void printBanner() {
        System.out.println("***********************");
        System.out.println("**** Customer Owes ****");
        System.out.println("***********************");
    }

    private static int calculateOutstanding(Invoice invoice) {
        int result = 0;
        for (Order order : invoice.getOrders()) {
            result += order.getAmount();
        }
        return result;
    }

    private static void printDetails(Invoice invoice, Integer outstanding) {
        System.out.println("name: " +  invoice.getCustomer());
        System.out.println("amount: " + outstanding);
    }

    private static Invoice initInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCustomer("tzs");
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Order order = new Order();
            order.setName("第" + i + "道");
            order.setPrice(new BigDecimal( i * 10 + 5));
            order.setAmount( i + 1);
            orders.add(order);
        }
        invoice.setOrders(orders);
        return invoice;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.stream().skip()
        printOwing(initInvoice());
    }


}
