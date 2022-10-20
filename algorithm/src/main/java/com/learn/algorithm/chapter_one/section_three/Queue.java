package com.learn.algorithm.chapter_one.section_three;

import java.util.Iterator;

/**
 * 先进先出队列
 * Create by liguo on 2022/10/12
 **/
public class Queue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        // first == null or N == 0
        return first == null;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        // 表尾添加元素
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        N++;
    }

    public Item dequeue() {
        // 表头删除元素
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        N--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
