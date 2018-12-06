package com.busekylin.algorithms.sort;

import java.util.Random;

/**
 * 文件 QuickThreeWayQuickSort 创建于 2017/8/9
 * 快速三向切分快速排序
 *
 * @author 赵磊
 * @version 1.0
 */
public class QuickThreeWayQuickSort extends SortTemplate {
    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        threeWaySort(array, 0, array.length - 1);
    }

    @SuppressWarnings("unchecked")
    private void threeWaySort(Comparable[] array, int left, int right) {
        if (left >= right)
            return;

        Comparable sign = array[left];
        int p = left, i = left, j = right, q = right;
        while (true) {
            //保证[j + 1, q]大于sign
            while (j >= i) {
                int status = sign.compareTo(array[j]);
                if (status < 0) {
                    j--;
                } else if (status == 0) {
                    exchange(array, q--, j--);
                } else {
                    exchange(array, j, i++);
                    break;
                }
            }

            //保证[p, i - 1]小于v
            while (i <= j) {
                int status = sign.compareTo(array[i]);
                if (status > 0) {
                    i++;
                } else if (status == 0) {
                    exchange(array, p++, i++);
                } else {
                    exchange(array, i, j--);
                }
            }

            if (j < i)
                break;
        }

//        System.out.println("sign-> " + sign);
//        System.out.println("p-> " + p + ", i - 1-> " + (i - 1) + ", j + 1-> " + (j + 1) + ",  q-> " + q);
//        show(array);

        //让右边的元素全大于标记元素
        while (q < right)
            exchange(array, ++q, i++);

        //让左边的元素全小于标记元素
        for (int k = left; k < p; k++) {
            exchange(array, k, j--);
        }

        threeWaySort(array, left, j);
        threeWaySort(array, i, right);
    }

    public static void main(String[] args) {
        int length = 10000000;
        Random random = new Random();
        long start, end;

        Integer[] a = new Integer[length];
        Integer[] b = new Integer[length];

        for (int i = 0; i < length; i++) {
            a[i] = random.nextInt(1000);
            b[i] = a[i];
        }


        SortTemplate sort = new QuickThreeWayQuickSort();

//        show(a);
        System.out.println(isSorted(a));
        start = System.currentTimeMillis();
        sort.sort(a);
        end = System.currentTimeMillis();
//        show(a);
        System.out.println(isSorted(a));
        System.out.println("time-> " + (end - start));

//        show(b);
        sort = new QuickSort();
        System.out.println(isSorted(b));
        start = System.currentTimeMillis();
        sort.sort(b);
        end = System.currentTimeMillis();
        System.out.println(isSorted(b));
        System.out.println("time-> " + (end - start));
        show(b);
    }
}
