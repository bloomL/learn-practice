package com.learn.algorithm.chapter_one.section_three;

import java.util.Iterator;

/**
 * Create by liguo on 2022/10/12
 **/
public class ResizingArrayStack<Item> implements Iterable<Item>{
    // 栈元素
    private Item[] a = (Item[]) new Object[1];
    // 元素数量
    private int N = 0;

    public boolean isEmpty() {
        return N ==0;
    }

    public int size() {
        return N;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        // 将栈移到大小为max的数组
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    public void push(Item item) {
        // 加到栈顶
        if (N == a.length) {
            resize(2 * a.length);
            a[N++] = item;
        }
    }

    public Item pop() {
        // 栈顶删除
        Item item = a[N--];
        // 避免游离对象
        a[N] = null;
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }

        @Override
        public void remove() {

        }
    }
}
