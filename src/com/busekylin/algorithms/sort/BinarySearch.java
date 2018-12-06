package com.busekylin.algorithms.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 文件 BinarySearch 创建于 2017/7/31
 *
 * @author 赵磊
 * @version 1.0
 */
public class BinarySearch {
    //对一个有序数组进行二分查找
    public static <E extends Comparable<E>> int rank(E key, E[] a) {
        return rank(key, a, 0, a.length - 1);
    }

    private static <E extends Comparable<E>> int rank(E key, E[] a, int start, int end) {
        if (start > end)
            return -1;

        int center = (start + end) / 2;
        if (key.compareTo(a[center]) > 0)
            return rank(key, a, center + 1, end);
        else if (key.compareTo(a[center]) < 0)
            return rank(key, a, start, center - 1);
        else {
            if (center + 1 < a.length && key.compareTo(a[center + 1]) < 0) {
                return center;
            }

            int temp = rank(key, a, center + 1, end);
            return center > temp ? center : temp;
        }
    }

    //返回key应该插在array数组中的下标
    public static <E extends Comparable<E>> int rank(E[] array, E key, int left, int right) {
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            int status = key.compareTo(array[mid]);
            if (status == 0) {
                if (mid + 1 <= right && key.compareTo(array[mid]) == 0) left = mid + 1;
                else return mid + 1;
            } else if (status > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    //数组array中小于value的数量
    public static int rank(int[] array, int value) {
        int left = 0, right = array.length - 1, length;
        int mid;

        while (left <= right) {
            length = right - left + 1;
            if (right != left)
                mid = length * (value - array[left]) / (array[right] - array[left]);
            else
                mid = left;

            if (mid < 0)
                break;

            mid = mid + left;

            if (mid > right)
                return right + 1;

            if (value == array[mid])
                return mid;
            else if (value > array[mid])
                left = mid + 1;
            else
                right = mid - 1;
        }

        return left;
    }

    public static void main(String[] args) {
        Random random = new Random();
//        Integer[] a = new Integer[10000];
//        for (int i = 0; i < 10000; i++)
//            a[i] = random.nextInt(6666666);
//
//        Arrays.sort(a);
//
//        for (int i = 0; i < 10000 - 1; ) {
//            int temp = rank(a[i], a);
//            if (temp > i) {
//                while (i < temp) {
//                    for (int j = i + 1; j <= temp; j++) {
//                        System.out.println("a[" + i + "]= " + a[i] + ", a[" + j + "]= " + a[j]);
//                    }
//                    i++;
//                }
//                i++;
//            } else {
//                i++;
//            }
//        }

        int length = 20;
        Integer[] num = new Integer[length];
        for (int i = 0; i < length; i++)
            num[i] = random.nextInt(5);
//
//        System.out.println(rank(num, 13));
        Arrays.sort(num);
        for (int n : num)
            System.out.print(n + " ");
        System.out.println();
        System.out.println(rank(num, 90, 0, num.length - 1));
    }
}
