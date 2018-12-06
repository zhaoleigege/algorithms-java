package com.busekylin.algorithms.sort;

import java.util.Random;

/**
 * 文件 QuickThreeWaySort 创建于 2017/8/8
 * 三切分的快速排序
 *
 * @author 赵磊
 * @version 1.0
 */
public class QuickThreeWaySort extends SortTemplate {
    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        sort(array, 0, array.length - 1);
    }

    @SuppressWarnings("unchecked")
    private void sort(Comparable[] array, int left, int right) {
        if (left >= right)
            return;

        int i = left + 1, lt = left, gt = right;

        while (i <= gt) {
            int status = array[i].compareTo(array[lt]);
            if (status < 0) {
                exchange(array, i++, lt++);
            } else if (status == 0) {
                i++;
            } else {
                exchange(array, i, gt--);
            }
        }

        sort(array, left, lt - 1);
        sort(array, gt + 1, right);
    }

    public static void main(String[] args) {
        int length = 600;
        Random random = new Random();
        long start, end;

        Integer[] a = new Integer[length];

        for (int i = 0; i < length; i++) {
            a[i] = random.nextInt(length * 10);
        }

        SortTemplate sort = new QuickThreeWaySort();

        show(a);
        System.out.println(isSorted(a));
        start = System.currentTimeMillis();
        sort.sort(a);
        end = System.currentTimeMillis();
        show(a);
        System.out.println(isSorted(a));
        System.out.println("time-> " + (end - start));
    }
}
