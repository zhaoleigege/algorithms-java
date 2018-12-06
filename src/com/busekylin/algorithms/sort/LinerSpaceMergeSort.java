package com.busekylin.algorithms.sort;

import java.util.Random;

/**
 * 文件 LinerSpaceMergeSort 创建于 2017/8/7
 *
 * @author 赵磊
 * @version 1.0
 */
public class LinerSpaceMergeSort extends SortTemplate {
    private int chunk;

    public LinerSpaceMergeSort(int chunk) {
        this.chunk = chunk;
    }

    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        int divider = array.length / chunk;
        if (divider > chunk) {
            int change = chunk;
            chunk = divider;
            divider = change;
        }
        Comparable[] temp = new Comparable[chunk];

        //对每个块进行排序
        for (int i = 0; i < divider; i++)
            merge(array, temp, i * chunk, (i + 1) * chunk - 1);

        //块与块之间进行排序
        for (int i = 0; i < divider - 1; i++) {
            int index = i * chunk;
            E min = array[index];
            for (int j = i + 1; j < divider; j++) {
                if (less(array[j * chunk], min)) {
                    index = j * chunk;
                    min = array[index];
                }
            }
            if (index != i * chunk)
                chunkExchange(array, i * chunk, index, chunk);
        }

        //块与块之间归并
        for (int i = 0; i < divider - 1; i++) {
            int index = (i + 1) * chunk;
            System.arraycopy(array, index, temp, 0, chunk);
            chunkMerge(array, temp, index - 1, index + chunk - 1);
        }
    }

    @SuppressWarnings("unchecked")
    private void chunkMerge(Comparable[] a, Comparable[] temp, int mid, int right) {
        int i = mid, j = right, index = temp.length - 1;
        while (index >= 0) {
            if (less(a[i], temp[index])) a[j--] = temp[index--];
            else a[j--] = a[i--];
        }
    }

    private void chunkExchange(Comparable[] array, int first, int last, int length) {
        for (int i = 0; i < length; i++) {
            exchange(array, first + i, last + i);
        }
    }

    @SuppressWarnings("unchecked")
    private void merge(Comparable[] array, Comparable[] temp, int left, int right) {
        if (left >= right)
            return;

        int mid = (left + right) / 2;
        merge(array, temp, left, mid);
        merge(array, temp, mid + 1, right);

        if (less(array[mid + 1], array[mid]))
            merge(array, temp, left, mid, right);
    }

    @SuppressWarnings("unchecked")
    private void merge(Comparable[] a, Comparable[] temp, int left, int mid, int right) {
        int leftLen = mid - left + 1;
        for (int i = 0; i < leftLen; i++)
            temp[i] = a[i + left];
        for (int i = right, k = 0; i > mid; i--, k++)
            temp[leftLen + k] = a[i];

        int i = 0, j = right - left;
        for (int k = left; k <= right; k++) {
            if (less(temp[j], temp[i])) a[k] = temp[j--];
            else a[k] = temp[i++];
        }
    }

    public static void main(String[] args) {
        int length = 35;
        Random random = new Random();
        Integer[] a = new Integer[length];

        for (int i = 0; i < length; i++)
            a[i] = random.nextInt(length * 4);

        System.out.println(isSorted(a));
        show(a);
        LinerSpaceMergeSort spaceMergeSort = new LinerSpaceMergeSort(5);
        spaceMergeSort.sort(a);
        show(a);
        System.out.println(isSorted(a));
    }
}
