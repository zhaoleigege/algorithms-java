package com.busekylin.algorithms.basicStructure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * 文件 RandomBag 创建于 2017/7/30
 *
 * @author 赵磊
 * @version 1.0
 */
public class RandomBag<Item> implements Bag<Item> {
    private static final int INITIAL_SIZE = 10;
    private Object[] items;
    private int size;

    public RandomBag() {
        this(INITIAL_SIZE);
    }

    public RandomBag(int size) {
        items = new Object[size];
    }

    private void resize(int capacity) {
        items = Arrays.copyOf(items, capacity);
    }

    @Override
    public void add(Item item) {
        if (size == items.length)
            resize(size + (size >> 1));

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
        return new RandomBagIterator<>();
    }

    private class RandomBagIterator<Item> implements Iterator<Item> {
        int index;
        Random random;

        public RandomBagIterator() {
            this.index = size;
            this.random = new Random();
        }

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public Item next() {
            int current = random.nextInt(index);
            index--;
            
            Item item = (Item) items[current];
            Object temp = items[index];
            items[index] = items[current];
            items[current] = temp;

            return item;
        }
    }

    public static void main(String[] args) {
        RandomBag<String> stringRandomBag = new RandomBag<>();

        for (int i = 0; i < 12; i++)
            stringRandomBag.add("string" + i);

        for (String string : stringRandomBag)
            System.out.println(string);
    }
}
