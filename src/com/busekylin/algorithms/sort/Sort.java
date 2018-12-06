package com.busekylin.algorithms.sort;

/**
 * 文件 Sort 创建于 2017/8/9
 *
 * @author 赵磊
 * @version 1.0
 */
public abstract class Sort {
    protected Comparable[] array;
    private static final int INITIAL_SIZE = 15;

    public Sort() {
        this(INITIAL_SIZE);
    }

    public Sort(int size) {
        this.array = new Comparable[size];
    }

    public long coastTime() {
        long start = System.currentTimeMillis();
        this.sort();
        long end = System.currentTimeMillis();

        return (end - start);
    }

    public abstract void sort();

    @SuppressWarnings("unchecked")
    public boolean less(int i, int j) {
        return array[i].compareTo(array[j]) < 0;
    }

    public void exchange(int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void show() {
        for (Comparable anArray : array) System.out.print(anArray + " ");

        System.out.println();
    }

    public boolean isSorted() {
        for (int i = 0; i < array.length - 1; i++)
            if (less(i + 1, i))
                return false;

        return true;
    }

    public boolean isSorted(int start, int end) {
        for (int i = start; i < end; i++)
            if (less(i + 1, i))
                return false;
        
        return true;
    }

    public void backward(int org, int dest, int step) {
        Comparable temp = array[org];
        for (int i = org; i >= dest + step; i -= step)
            array[i] = array[i - step];

        array[dest] = temp;
    }
}
