package com.busekylin.algorithms.sort;

import java.util.Random;

import static com.busekylin.algorithms.sort.MergeSort.mergeInsert;

/**
 * 文件 MultiplePartitionQuickSort 创建于 2017/8/8
 *
 * @author 赵磊
 * @version 1.0
 */
public class MultiplePartitionQuickSort extends SortTemplate {
    private static final int CUTOFF = 15;
    private static final int WAY = 3;

    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(Comparable[] array, int left, int right) {
        if (left >= right)
            return;

        if (right - left <= CUTOFF)
            mergeInsert(array, left, right);

        int index = partition(array, left, right);
        quickSort(array, left, index - 1);
        quickSort(array, index + 1, right);
    }

    @SuppressWarnings("unchecked")
    private int partition(Comparable[] array, int left, int right) {
        int index = median(array, left, (left + right) / 2, right);
        exchange(array, index, left);
        int i = left, j = right + 1;

        while (true) {
            while (!less(array[--j], array[left]))
                if (i >= j)
                    break;

            while (!less(array[left], array[++i]))
                if (i >= j)
                    break;

            if (i >= j)
                break;

            exchange(array, i, j);
        }

        exchange(array, left, j);

        return j;
    }

    @SuppressWarnings("unchecked")
    private int median(Comparable[] array, int left, int mid, int right) {

        return less(array[left], array[right])
                ? (less(array[mid], array[left]) ? left : (less(array[mid], array[right]) ? mid : right))
                : (less(array[mid], array[right]) ? right : (less(array[mid], array[left]) ? mid : left));
//        Integer[] num = new Integer[WAY];
//        int size = right - left + 1;
//        Random random = new Random();
//
//        for (int i = 0; i < WAY; i++) {
//            num[i] = random.nextInt(size) + left;
//        }
//
//        for (int i = 0; i < WAY - 1; i++)
//            for (int j = i + 1; j < WAY; j++)
//                if (less(array[num[j]], array[num[i]]))
//                    exchange(num, i, j);
//
//        return num[(int) Math.ceil(WAY / 2)];
    }


    public static void main(String[] args) {
        int length = 10000000;
        Random random = new Random();
        long start, end;

//        Map<Integer, Integer> map = new HashMap<>();
        for (int k = 0; k < 10; k++) {
            Integer[] a = new Integer[length];
            Integer[] b = new Integer[length];

            for (int i = 0; i < length; i++) {
                a[i] = random.nextInt(length * 10);
                b[i] = a[i];
//            if (map.get(a[i]) == null)
//                map.put(a[i], 1);
//            else
//                map.put(a[i], map.get(a[i]) + 1);
            }

//        show(a);
            System.out.println(isSorted(a));

            SortTemplate sort = new MultiplePartitionQuickSort();

            start = System.currentTimeMillis();
            sort.sort(a);
            end = System.currentTimeMillis();

            System.out.println(isSorted(a));

            System.out.println("time-> " + (end - start));
//        show(a);

            System.out.println(isSorted(b));

            sort = new QuickSort();

            start = System.currentTimeMillis();
            sort.sort(b);
            end = System.currentTimeMillis();

            System.out.println(isSorted(b));

            System.out.println("time-> " + (end - start));

            System.out.println("===================================");
        }
    }
}
