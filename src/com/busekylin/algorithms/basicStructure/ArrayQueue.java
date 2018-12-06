package com.busekylin.algorithms.basicStructure;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 文件 ArrayQueue 创建于 2017/7/29
 *
 * @author 赵磊
 * @version 1.0
 */
public class ArrayQueue<Item> implements Queue<Item> {
    private static final int INITIAL_SIZE = 10;
    private transient Object[] items;
    private int tail;
    private int size;
    private int head;

    public ArrayQueue() {
        this(INITIAL_SIZE);
    }

    public ArrayQueue(int size) {
        items = new Object[size];
    }

    private void resize(int capacity) {
        items = Arrays.copyOf(items, capacity);
    }

    @Override
    public void enqueue(Item item) {
        if (size == items.length)
            resize(size + (size >> 1));

        size++;
        items[tail++ % items.length] = item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Item dequeue() {
        if (size < items.length / 4)
            resize(items.length / 2);

        size -= 1;
        Item item = (Item) items[head++];
        items[head - 1] = null;
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

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator<Item> implements Iterator<Item> {
        private int count;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Item next() {
            return (Item) items[(head + count++) % items.length];
        }
    }

    public static void main(String[] args) {
        Queue<String> queue = new ArrayQueue<>();
        for (int i = 0; i < 12; i++)
            queue.enqueue("string" + i);

        for (int i = 0; i < 10; i++)
            queue.dequeue();


        for (int i = 0; i < 6; i++)
            queue.enqueue("string" + i);

        for (String string : queue)
            System.out.println(string);
    }
}
