package com.busekylin.algorithms.basicStructure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 文件 GeneralizedArrayQueue 创建于 2017/7/30
 *
 * @author 赵磊
 * @version 1.0
 */
public class GeneralizedArrayQueue<E> implements Queue<E> {
    private static final int INITIAL_SIZE = 10;
    private int size;
    private int head;
    private Object[] items;

    public GeneralizedArrayQueue() {
        this(INITIAL_SIZE);
    }

    public GeneralizedArrayQueue(int capacity) {
        items = new Object[capacity];
    }

    private void resize(int capacity) {
        items = Arrays.copyOf(items, capacity);
    }

    @Override
    public void enqueue(E e) {
        if (size == items.length)
            resize(size + (size >> 1));

        items[size++] = e;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("队列为空");

        E item = (E) items[--size];
        items[size] = null;
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

    @SuppressWarnings("unchecked")
    public E delete(int k) {
        if (k > size || k < 1)
            throw new IllegalArgumentException(k + "不合法");

        E item = (E) items[k - 1];

        while (k < size) {
            items[k - 1] = items[k];
            k++;
        }

        items[k - 1] = null;
        size--;

        return item;
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator<>(size);
    }

    private class QueueIterator<E> implements Iterator<E> {
        int index;

        public QueueIterator(int index) {
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            return (E) items[--index];
        }
    }

    public static void main(String[] args) {
        GeneralizedArrayQueue<String> queue = new GeneralizedArrayQueue<>();
        for (int i = 0; i < 10; i++)
            queue.enqueue("string" + i);

        System.out.println("删除的为-> " + queue.delete(0));

        while (!queue.isEmpty())
            System.out.println(queue.dequeue());
    }
}
