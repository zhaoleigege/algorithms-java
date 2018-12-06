package com.busekylin.algorithms.sort;

import java.util.Random;

/**
 * 文件 QuickSort 创建于 2017/8/7
 *
 * @author 赵磊
 * @version 1.0
 */
public class QuickSort extends SortTemplate {
    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(Comparable[] array, int left, int right) {
        if (left >= right)
            return;

        int index = partition(array, left, right);
        quickSort(array, left, index - 1);
        quickSort(array, index + 1, right);
    }

    @SuppressWarnings("unchecked")
    private int partition(Comparable[] array, int left, int right) {
        int i = left, j = right + 1;
        Comparable sign = array[left];
        while (true) {
            while (!less(array[--j], sign))
                if (i >= j)
                    break;

            while (!less(sign, array[++i]))
                if (i >= j)
                    break;

            if (i >= j)
                break;

            exchange(array, j, i);
        }

        exchange(array, left, j);
        return j;
    }

    public static void main(String[] args) {
        int length = 12;
        Random random = new Random();
        long start, end;

//        Map<Integer, Integer> map = new HashMap<>();

        Integer[] a = new Integer[length];

        for (int i = 0; i < length; i++) {
            a[i] = random.nextInt(5);
//            if (map.get(a[i]) == null)
//                map.put(a[i], 1);
//            else
//                map.put(a[i], map.get(a[i]) + 1);
        }

        show(a);
        System.out.println(isSorted(a));

        QuickSort sort = new QuickSort();

        start = System.currentTimeMillis();
        sort.sort(a);
        end = System.currentTimeMillis();

        System.out.println(isSorted(a));

        System.out.println("time-> " + (end - start));
        show(a);
//        for (int num : a)
//            map.put(num, map.get(num) - 1);
//
//        map.forEach((k, v) -> {
//            if(v < 0)
//                System.out.println(k);
//        });
    }
}
