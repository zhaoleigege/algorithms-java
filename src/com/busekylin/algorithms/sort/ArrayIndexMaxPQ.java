package com.busekylin.algorithms.sort;

import java.util.NoSuchElementException;
import java.util.Random;

/**
 * 文件 ArrayIndexMaxPQ 创建于 2017/8/10
 *
 * @author 赵磊
 * @version 1.0
 */
public class ArrayIndexMaxPQ<E extends Comparable<E>> implements IndexMaxPQ<E> {
    private int size;           //优先队列的元素总数
    private E[] array;          //存放加入进来的元素
    private int[] pq;           //用来存放array中元素按照二叉堆排列的索引(即通过这个数组来决定元素的获取顺序)
    private int[] qp;           //用来保存pq数组中对应array数组的索引

    @SuppressWarnings("unchecked")
    public ArrayIndexMaxPQ(int capacity) {
        array = (E[]) new Comparable[capacity];
        pq = new int[capacity + 1];
        qp = new int[capacity];
        for (int i = 0; i < capacity; i++)
            qp[i] = -1;
    }

    private void backward(int org, int dest) {
        int pqNum = pq[org];

        while (org > dest) {
            int index = org / 2;
            qp[pq[index]] = org;
            pq[org] = pq[index];
            org = index;
        }

        qp[pqNum] = dest;
        pq[dest] = pqNum;
    }

    private void swim(int index) {
        int end = index;
        while (end > 1) {
            int j = end / 2;
            if (array[pq[index]].compareTo(array[pq[j]]) <= 0)
                break;

            end = j;
        }
        backward(index, end);
    }

    /**
     * @param k    关联的索引
     * @param item 元素的内容
     */
    @Override
    public void insert(int k, E item) {
        if (k >= array.length)
            throw new IndexOutOfBoundsException("插入越界");

        size++;
        array[k] = item;
        pq[size] = k;
        qp[k] = size;
        swim(size);
    }

    public E get(int index) {
        return array[index];
    }

    private void forward(int org, int dest) {
        int pqNum = pq[dest];
        qp[pq[org]] = dest;
        pq[dest] = pq[org];
        while (dest > org) {
            int j = dest / 2;
            qp[pqNum] = j;
            int temp = pqNum;
            pqNum = pq[j];
            pq[j] = temp;
            dest = j;
        }

    }

    private void skin(int index) {
        int end = index;
        while (2 * end < size) {
            int j = end * 2;
            if (j < size && array[pq[j]].compareTo(array[pq[j + 1]]) < 0) j++;
            if (array[pq[index]].compareTo(array[pq[j]]) >= 0) break;
            end = j;
        }
        forward(index, end);
    }

    @Override
    public void change(int k, E item) {
        if (!contains(k))
            throw new NoSuchElementException("下标不存在");

        array[k] = item;
        swim(qp[k]);
        skin(qp[k]);
    }

    @Override
    public boolean contains(int k) {
        if (k < 0 || k >= array.length)
            return false;
        return qp[k] != -1;
    }

    @Override
    public void delete(int k) {
        if (!contains(k))
            return;

        E item = array[pq[size]];
        array[pq[size]] = null;
        qp[pq[size]] = -1;
        size--;
        change(k, item);
    }

    @Override
    public E max() {
        return array[pq[1]];
    }

    @Override
    public int maxIndex() {
        return pq[1];
    }

    @Override
    public int delMax() {
        int index = pq[1];
        delete(index);
        return index;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        int length = 10;
        Random random = new Random();
        ArrayIndexMaxPQ<Integer> maxPQ = new ArrayIndexMaxPQ<>(length);

//        for (int i = 0; i < length; i++)
//            maxPQ.insert(i, random.nextInt(length));
        maxPQ.insert(0, 6);
        maxPQ.insert(1, 3);
        maxPQ.insert(2, 3);
        maxPQ.insert(3, 7);
        maxPQ.insert(4, 5);
        maxPQ.insert(5, 3);
        maxPQ.insert(6, 2);
        maxPQ.insert(7, 9);
        maxPQ.insert(8, 5);
        maxPQ.insert(9, 2);

        System.out.println(maxPQ.delMax());

        for (int i = 0; i < length; i++)
            System.out.print(maxPQ.get(i) + " ");
        System.out.println();

        for (int i = 1; i <= length; i++)
            System.out.print(maxPQ.pq[i] + " ");
        System.out.println();

        for (int i = 0; i < length; i++)
            System.out.print(maxPQ.qp[i] + " ");
    }
}
