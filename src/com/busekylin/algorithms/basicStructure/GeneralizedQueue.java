package com.busekylin.algorithms.basicStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 文件 GeneralizedQueue 创建于 2017/7/30
 * <p>
 * 这是一个先进先出的队列
 * 添加节点，新加入的节点在尾部
 *
 * @author 赵磊
 * @version 1.0
 * @see #enqueue(Object)
 */
public class GeneralizedQueue<E> implements Queue<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private class Node<E> {
        E item;
        Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    /**
     * @return 返回节点元素，但是不从队列中删除
     */
    public E peek() {
        return head == null ? null : head.item;
    }

    /**
     * 给队列中添加一个元素
     *
     * @param item
     */
    @Override
    public void enqueue(E item) {
        Node<E> node = new Node<>(item, null);
        node.item = item;
        if (tail != null) {
            tail.next = node;
            tail = node;
        } else {
            tail = node;
            head = node;
        }

        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("队列为空");

        Node<E> node = head;

        head = head.next;
        if (head == null)
            tail = null;

        E item = node.item;
        node.item = null;
        node.next = null;
        size--;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public E delete(int k) {
        if (k <= 0)
            throw new IllegalArgumentException(k + "不合法");

        if (isEmpty())
            throw new NoSuchElementException("队列为空");

        Node<E> node = head;
        Node<E> prev = null;
        E item;

        while (node != null && --k > 0) {
            prev = node;
            node = node.next;
        }

        if (prev == null) {
            item = node.item;
            head = node.next;
            node.item = null;
            node.next = null;
            if (head == null)
                tail = null;

            size--;
            return item;
        }

        if (k == 0) {
            item = node.item;
            prev.next = node.next;
            if (prev.next == null)
                tail = prev;

            node.item = null;
            node.next = null;

            size--;
            return item;
        }

        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator<>(head);
    }

    private class QueueIterator<E> implements Iterator<E> {
        Node<E> node;

        public QueueIterator(Node<E> first) {
            node = first;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            E item = node.item;
            node = node.next;
            return item;
        }
    }

    public static void main(String[] args) {
        GeneralizedQueue<String> queue = new GeneralizedQueue<>();
        for (int i = 0; i < 10; i++)
            queue.enqueue("string" + i);

        System.out.println("删除的为-> " + queue.delete(10));

        while (!queue.isEmpty())
            System.out.println(queue.dequeue());
    }
}
