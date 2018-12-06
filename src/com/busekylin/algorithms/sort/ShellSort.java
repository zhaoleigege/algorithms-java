package com.busekylin.algorithms.sort;

import java.util.Random;

/**
 * 文件 ShellSort 创建于 2017/8/2
 *
 * @author 赵磊
 * @version 1.0
 */
public class ShellSort extends SortTemplate {

    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        int length = array.length;
        int h = 1;
        while (h < length) h = 3 * h + 1;
        h = (h - 1) / 3;

        while (h >= 1) {

            for (int i = 0; i < length - h; i += h) {
                int index = i;
                while (index >= 0 && less(array[i + h], array[index]))
                    index -= h;
                backward(array, i + h, index + h, h);
            }

            h = (h - 1) / 3;
        }
    }

    public static void main(String[] args) {
        int length = 10000;
        Random random = new Random();
        Integer[] a = new Integer[length];

        for (int i = 0; i < length; i++) {
            a[i] = random.nextInt(1000);
        }

        new ShellSort().sort(a);
        show(a);

    }
}
