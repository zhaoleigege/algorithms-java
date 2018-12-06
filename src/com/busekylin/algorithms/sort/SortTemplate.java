package com.busekylin.algorithms.sort;

/**
 * 文件 SortTemplate 创建于 2017/8/2
 *
 * @author 赵磊
 * @version 1.0
 */
public abstract class SortTemplate {
    public abstract <E extends Comparable<E>> void sort(E[] array);

    protected static <E extends Comparable<E>> boolean less(E v, E w) {
        return v.compareTo(w) < 0;
    }

    protected static <E extends Comparable<E>> void exchange(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    protected static <E extends Comparable<E>> void backward(E[] array, int start, int end, int step) {
        E item = array[start];
        while (start > end) {
            array[start] = array[start - step];
            start -= step;
        }
        array[end] = item;
    }

    protected static <E extends Comparable<E>> boolean isSorted(E[] array) {
        for (int i = 0; i < array.length - 1; i++)
            if (less(array[i + 1], array[i]))
                return false;

        return true;
    }

    protected static <E extends Comparable<E>> void show(E[] array) {
        for (E anArray : array) System.out.print(anArray + " ");

        System.out.println();
    }
}
