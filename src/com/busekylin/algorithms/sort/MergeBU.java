package com.busekylin.algorithms.sort;

import java.util.Random;

/**
 * 文件 MergeBU 创建于 2017/8/6
 * 自底向上实现归并
 *
 * @author 赵磊
 * @version 1.0
 */

public class MergeBU extends SortTemplate {

    private void merge(Comparable[] a, Comparable[] temp, int left, int mid, int right) {
        System.arraycopy(a, left, temp, left, right - left + 1);
        int i = left, j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (j > right) a[k] = temp[i++];
            else if (i > mid) a[k] = temp[j++];
            else if (less(temp[j], temp[i])) a[k] = temp[j++];
            else a[k] = temp[i++];
        }
    }

    private void mergeReverse(Comparable[] a, Comparable[] temp, int left, int mid, int right) {
        for (int i = left; i <= mid; i++)
            temp[i] = a[i];
        for (int i = right, k = 1; i > mid; i--, k++)
            temp[mid + k] = a[i];

        int i = left, j = right;
        for (int k = left; k <= right; k++) {
            if (less(temp[j], temp[i])) a[k] = temp[j--];
            else a[k] = temp[i++];
        }
    }

    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        Comparable[] temp = new Comparable[array.length];
        for (int gap = 1; gap < array.length; gap *= 2) {
            for (int i = 0; i < array.length - gap; i += 2 * gap) {
                mergeReverse(array, temp, i, i + gap - 1, Math.min(i + 2 * gap - 1, array.length - 1));
            }
        }

    }

    public static void main(String[] args) {
        int length = 1000;
        Random random = new Random();
        Integer[] a = new Integer[length];

        for (int i = 0; i < length; i++)
            a[i] = random.nextInt(length * 4);

        show(a);
        MergeBU mergeBU = new MergeBU();
        mergeBU.sort(a);
        System.out.println(isSorted(a));
        show(a);
    }
}
