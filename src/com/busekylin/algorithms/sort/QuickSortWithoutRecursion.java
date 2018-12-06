package com.busekylin.algorithms.sort;

import com.busekylin.algorithms.basicStructure.ArrayStack;
import com.busekylin.algorithms.basicStructure.Stack;

import java.util.Random;

/**
 * 文件 QuickSortWithoutRecursion 创建于 2017/8/9
 *
 * @author 赵磊
 * @version 1.0
 */
public class QuickSortWithoutRecursion extends SortTemplate {
    private class Info {
        int left;
        int right;

        Info(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        Stack<Info> stack = new ArrayStack<>();
        stack.push(new Info(0, array.length - 1));

        while (!stack.isEmpty()) {
            Info info = stack.Pop();
            int index = quickSort(array, info.left, info.right);
            if (index < info.right - 1)
                stack.push(new Info(index + 1, info.right));
            if (index > info.left + 1)
                stack.push(new Info(info.left, index - 1));
        }
    }

    private int quickSort(Comparable[] array, int left, int right) {
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

        exchange(array, j, left);

        return j;
    }

    public static void main(String[] args) {
        int length = 30000000;
        Random random = new Random();
        long start, end;

        Integer[] a = new Integer[length];
        Integer[] b = new Integer[length];

        for (int i = 0; i < length; i++) {
            a[i] = random.nextInt(length * 10);
            b[i] = a[i];
        }

        SortTemplate sort = new QuickSortWithoutRecursion();

//        show(a);
        System.out.println(isSorted(a));
        start = System.currentTimeMillis();
        sort.sort(a);
        end = System.currentTimeMillis();
//        show(a);
        System.out.println(isSorted(a));
        System.out.println("time-> " + (end - start));

        sort = new QuickSort();
        System.out.println(isSorted(b));
        start = System.currentTimeMillis();
        sort.sort(b);
        end = System.currentTimeMillis();
        System.out.println(isSorted(b));
        System.out.println("time-> " + (end - start));
    }
}
