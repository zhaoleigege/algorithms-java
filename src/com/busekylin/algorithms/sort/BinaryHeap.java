package com.busekylin.algorithms.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 文件 BinaryHeap 创建于 2017/8/9
 *
 * @author 赵磊
 * @version 1.0
 */
public class BinaryHeap<E extends Comparable<E>> extends Sort implements MaxPQ<E> {
    private int size;

    private void swim(int index) {
        while (index > 1) {
            int prev = index / 2;
            if (!less(prev, index))
                break;
            exchange(index, prev);
            index = prev;
        }
    }

    private void resize(int capacity) {
        array = Arrays.copyOf(array, capacity);
    }

    @Override
    public void Insert(E key) {
        if (size == array.length - 1)
            resize(size + (size >> 1));

        array[++size] = key;
        swim(size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public E max() {
        return (E) array[1];
    }

    private void skin(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exchange(k, j);
            k = j;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public E delMax() {
        E item = (E) array[1];
        array[1] = array[size];
        array[size--] = null;
        skin(1);

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
    public void sort() {

    }

    public static void main(String[] args) {
        int length = 10;
        Random random = new Random();
        BinaryHeap<Integer> pq = new BinaryHeap<>();

        for (int i = 0; i < length; i++)
            pq.Insert(random.nextInt(length / 2));

        pq.show();
        while (!pq.isEmpty())
            System.out.println(pq.delMax());
//        pq.show();
    }
}
