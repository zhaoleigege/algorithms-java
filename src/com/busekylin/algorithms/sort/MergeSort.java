package com.busekylin.algorithms.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 文件 MergeSort 创建于 2017/8/6
 *
 * @author 赵磊
 * @version 1.0
 */
public class MergeSort extends SortTemplate {
    private boolean allowInsert = false;
    private int cutoff;

    public MergeSort() {

    }

    public MergeSort(boolean allowInsert, int cutoff) {
        this.allowInsert = allowInsert;
        this.cutoff = cutoff;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends Comparable<E>> void sort(E[] array) {
        Comparable[] temp = array.clone();
        mergeSort(array, temp, 0, array.length - 1);
    }

    private void mergeSort(Comparable[] array, Comparable[] temp, int left, int right) {
        if (left >= right)
            return;

        if (allowInsert && (right - left) < cutoff) {
            mergeInsert(array, left, right);
            return;
        }

        int mid = (left + right) / 2;
        mergeSort(temp, array, left, mid);
        mergeSort(temp, array, mid + 1, right);

        if (!less(temp[mid + 1], temp[mid])) {
            System.arraycopy(temp, left, array, left, right - left + 1);
            return;
        }

        mergeSort(array, temp, left, mid, right);
    }

    //在这里temp是两边都已经按照递增序列排好，然后array再组合这两个各自递增的序列最后变为一个在left到right的递增数组
    @SuppressWarnings("unchecked")
    private void mergeSort(Comparable[] array, Comparable[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (j > right) array[k] = temp[i++];
            else if (i > mid) array[k] = temp[j++];
            else if (less(temp[j], temp[i])) array[k] = temp[j++];
            else array[k] = temp[i++];
        }
    }

    //这里a[left]和a[right]皆可访问
    @SuppressWarnings("unchecked")
    public static void mergeInsert(Comparable[] a, int left, int right) {
        for (int i = left; i < right; i++) {
            int index = i;
            while (index >= left && less(a[i + 1], a[index]))
                index--;

            backward(a, i + 1, index + 1, 1);
        }
    }

    public static void main(String[] args) {
        int length = 30;
        Random random = new Random();
        long start, end;

        Integer[] a = new Integer[length];

        for (int i = 0; i < length; i++) {
            a[i] = random.nextInt(length * 10);
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (map.get(a[i]) == null) map.put(a[i], 1);
            else map.put(a[i], map.get(a[i]) + 1);
        }

        show(a);
        System.out.println(isSorted(a));

        MergeSort sort = new MergeSort();

        start = System.currentTimeMillis();
        sort.sort(a);
        end = System.currentTimeMillis();

        System.out.println(isSorted(a));

        System.out.println("time-> " + (end - start));
        show(a);
    }
}
