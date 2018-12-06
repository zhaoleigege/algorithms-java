package com.busekylin.algorithms.basicStructure;

import java.util.NoSuchElementException;

/**
 * 文件 RingBuffer 创建于 2017/7/30
 *
 * @author 赵磊
 * @version 1.0
 */
public class RingBuffer<E> {
    private int head;
    private int size;
    private Object[] items;

    public RingBuffer(int capacity) {
        items = new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(E item) {
        int index = (size + head) % items.length;

        if (size != 0 && index == head)
            throw new IllegalArgumentException("队列已经满了");

        items[index] = item;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        if (isEmpty())
            throw new NoSuchElementException("队列为空");
        head = (head + 1) % items.length;
        E item = (E) items[head];
        size--;

        return item;
    }

    public static void main(String[] args) {
        RingBuffer<String> buffer = new RingBuffer<>(10);

        for (int i = 0; i < 8; i++) {
            buffer.add("string" + i);
            System.out.println(buffer.size());
        }

        System.out.println(buffer.pop());
        System.out.println(buffer.pop());
        System.out.println(buffer.size());

        for (int i = 0; i < 4; i++)
            buffer.add("string122" + i);

        System.out.println("============");
        while (!buffer.isEmpty())
            System.out.println(buffer.pop());

        for (int i = 0; i < 8; i++) {
            buffer.add("string" + i);
        }

        System.out.println("head= " + buffer.head);

    }
}
