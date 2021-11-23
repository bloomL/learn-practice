package com.learn.concurrent.actual_combat.chapter_fifteen.four;


import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * Nonblocking stack using Treiber's algorithm
 *
 * Create by liguo on 2021/11/23
 **/
public class ConcurrentStack<E> {
    AtomicReference<Node<E>> top = new AtomicReference<>();

    public void push(E item) {
        Node<E> newHead = new Node<>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));
    }

    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = top.get();
            if (oldHead == null) {
                return null;
            }
            newHead = oldHead.next;
        } while (!top.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }

    private static class Node<E> {
        private final E item;
        private Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }
}
