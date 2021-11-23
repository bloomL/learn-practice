package com.learn.concurrent.actual_combat.chapter_fifteen.four;

import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * Insertion in the Michael-Scott nonblocking queue algorithm
 *
 * Create by liguo on 2021/11/23
 **/
public class LinkedQueue<E> {

    public static class Node<E> {
        final E item;
        final AtomicReference<LinkedQueue.Node<E>> next;

        public Node(E item, LinkedQueue.Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }

    private final LinkedQueue.Node<E> sentinel = new LinkedQueue.Node<>(null, null);
    private final AtomicReference<LinkedQueue.Node<E>> head = new AtomicReference<>(sentinel);
    private final AtomicReference<LinkedQueue.Node<E>> tail = new AtomicReference<>(sentinel);

    public boolean put(E item) {
        LinkedQueue.Node<E> newNode = new LinkedQueue.Node<E>(item, null);
        while(true) {
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if (curTail == tail.get()) {
                if (tailNext != null) {
                    // Queue in intermediate state, advance tail
                    tail.compareAndSet(curTail, tailNext);
                } else {
                    // In quiescent state, try inserting new node
                    if (curTail.next.compareAndSet(null, newNode)) {
                        // Insertion succeeded, try advancing tail
                        tail.compareAndSet(curTail, newNode);
                        return true;
                    }
                }
            }
        }
    }
}
