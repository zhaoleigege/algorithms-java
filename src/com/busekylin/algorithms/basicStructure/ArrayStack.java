package com.busekylin.algorithms.basicStructure;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 文件 ArrayStack 创建于 2017/7/29
 *
 * @author 赵磊
 * @version 1.0
 */
public class ArrayStack<Item> implements Stack<Item> {
    private static final int DEFAULT_INITIAL_SIZE = 10;
    private transient Object[] items;
    private int size;

    public ArrayStack() {
        this(DEFAULT_INITIAL_SIZE);
    }

    public ArrayStack(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("错误的初始大小: " + size);
        items = new Object[size];
    }


    private void resize(int capacity) {
        items = Arrays.copyOf(items, capacity);
    }

    @Override
    public void push(Item item) {
        if (size == items.length)
            resize(size + (size >> 1));

        items[size++] = item;
    }


    @SuppressWarnings("unchecked")
    public Item peek() {
        if (size == 0)
            return null;

        return (Item) items[size - 1];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Item Pop() {
        if (size == 0)
            return null;

        if (size < items.length / 4)
            resize(items.length / 2);

        Item item = (Item) items[--size];
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

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator<>();
    }

    private class StackIterator<Item> implements Iterator<Item> {
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

    public static Stack<String> copy(Stack<String> stack) {
        Stack<String> stringStack = new ArrayStack<>(stack.size());
        for (String string : stack)
            stringStack.push(string);

        return stringStack;
    }

    public static void main(String[] args) {
        Stack<String> strings = new ArrayStack<>();
        for (int i = 0; i < 12; i++)
            strings.push("string" + i);

        for (String s : strings)
            System.out.println(s);

        while (!strings.isEmpty())
            System.out.println(strings.Pop());
    }
}
