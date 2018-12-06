package com.busekylin.algorithms.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 文件 HeapSort 创建于 2017/8/10
 * 堆排序
 *
 * @author 赵磊
 * @version 1.0
 */
public class HeapSort<E extends Comparable<E>> extends Sort {
    private boolean isSorted;
    private int size;

    public HeapSort() {
        super();
    }

    public HeapSort(int size) {
        super(size);
        isSorted = false;
    }

    private void resize(int capacity) {
        array = Arrays.copyOf(array, capacity);
    }

    public void insert(E item) {
        if (size == array.length - 1)
            resize(size + (size >> 1));

        array[++size] = item;
        isSorted = false;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index <= 0 || index > size)
            throw new IllegalArgumentException("访问越界");

        return (E) array[index];
    }

    private void forward(int org, int dest) {
        Comparable item = array[dest];
        array[dest] = array[org];
        while (dest > org) {
            int index = dest / 2;
            Comparable temp = array[index];
            array[index] = item;
            item = temp;
            dest = index;
        }
    }

    @SuppressWarnings("unchecked")
    private void skin(int index, int end) {
        int j = index;
        while (2 * j <= end) {
            int k = 2 * j;
            if (k < end && array[k].compareTo(array[k + 1]) < 0) k++;
            if (array[k].compareTo(array[index]) < 0) break;
            j = k;
        }
        forward(index, j);
    }

    @Override
    public void sort() {
        //把数组变成堆有序
        for (int k = size / 2; k >= 1; k--)
            skin(k, size);

        int temp = size;
        //然后下沉排序
        for (int i = 1; i < size; i++) {
            exchange(1, temp--);
            skin(1, temp);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int length = 100;
        HeapSort<Integer> sort = new HeapSort<>();
        for (int i = 0; i < length; i++)
            sort.insert(random.nextInt(length * 2));
        sort.show();
        sort.sort();
        sort.show();
        System.out.println(sort.isSorted(1, sort.size));
    }
}
