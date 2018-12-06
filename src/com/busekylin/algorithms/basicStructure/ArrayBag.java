package com.busekylin.algorithms.basicStructure;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 文件 ArrayBag 创建于 2017/7/29
 *
 * @author 赵磊
 * @version 1.0
 */
public class ArrayBag<Item> implements Bag<Item> {
    private static final int DEFAULT_INITIAL_SIZE = 10;
    private transient Object[] items;
    private int size;

    public ArrayBag() {
        this(DEFAULT_INITIAL_SIZE);
    }

    public ArrayBag(int size) {
        items = new Object[size];
    }

    private void resize(int capacity) {
        items = Arrays.copyOf(items, capacity);
    }


    @Override
    public void add(Item item) {
        if (size == items.length)
            resize(size + size >> 1);

        items[size++] = item;
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
        return new BagIterator<>();
    }

    private class BagIterator<Item> implements Iterator<Item> {
        private int index;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Item next() {
            return (Item) items[index++];
        }
    }

    public static void main(String[] args) {
        ArrayBag<String> bag = new ArrayBag<>(10);

        for (int i = 0; i < 10; i++)
            bag.add("string" + i);

        for (String string : bag)
            System.out.println(string);
    }
}
