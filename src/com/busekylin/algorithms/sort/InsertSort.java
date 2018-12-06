package com.busekylin.algorithms.sort;

import java.util.Random;

/**
 * 文件 InsertSort 创建于 2017/8/2
 *
 * @author 赵磊
 * @version 1.0
 */
public class InsertSort extends SortTemplate {
    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        int index;
        for (int i = 0; i < array.length - 1; i++) {
            index = i;
            while (index >= 0 && less(array[i + 1], array[index]))
                index--;
            backward(array, i + 1, index + 1, 1);
        }
    }

    public static void main(String[] args) {
        int length = 1000000;
        Random random = new Random();
        Integer[] a = new Integer[length];
        Integer[] b = new Integer[length];
        Integer[] c = new Integer[length];

        for (int i = 0; i < length; i++) {
            a[i] = random.nextInt(length);
            b[i] = a[i];
            c[i] = b[i];
        }
        long time1, time2, time3;
        long start = System.currentTimeMillis();
        new InsertSort().sort(a);
        time1 = System.currentTimeMillis() - start;
        System.out.println("tim1-> " + time1);

        start = System.currentTimeMillis();
        new SelectSort().sort(b);
        time2 = System.currentTimeMillis() - start;
        System.out.println("time2-> " + time2);


        start = System.currentTimeMillis();
        new ShellSort().sort(c);
        time3 = System.currentTimeMillis() - start;
        System.out.println("time3-> " + time3);
//        show(c);

//        System.out.println("选择排序比插入排序为: " + 1.0 * time2 / time1);
    }
}
