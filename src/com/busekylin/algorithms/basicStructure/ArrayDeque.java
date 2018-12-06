package com.busekylin.algorithms.basicStructure;

import java.util.Iterator;

/**
 * 文件 ArrayDeque 创建于 2017/7/30
 *
 * @author 赵磊
 * @version 1.0
 */
public class ArrayDeque<Item> implements Deque<Item> {
    private static final int INITIAL_SIZE = 10;
    private Object[] items;
    private int left, right, size;

    public ArrayDeque() {
        this(INITIAL_SIZE);
    }

    public ArrayDeque(int size) {
        items = new Object[size];
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
    public void pushLeft(Item item) {

    }

    @Override
    public void pushRight(Item item) {

    }

    @Override
    public Item popLeft() {
        return null;
    }

    @Override
    public Item popRight() {
        return null;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
