package com.busekylin.algorithms.sort;

import java.util.Random;

/**
 * 文件 CompareDifferenceIncreaseOrder 创建于 2017/8/5
 *
 * @author 赵磊
 * @version 1.0
 */
public class CompareDifferenceIncreaseOrder extends SortTemplate {
    public static void main(String[] args) {
        int length = 10000000;
        Random random = new Random();
        Integer[] array = new Integer[length];
        Integer[] arrayCopy = new Integer[length];

        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(length);
            arrayCopy[i] = array[i];
        }

        System.out.println(isSorted(array));
        new CompareDifferenceIncreaseOrder().sort(array);
        System.out.println(isSorted(array));

        System.arraycopy(arrayCopy, 0, array, 0, length);
        System.out.println(isSorted(array));
        long start = System.currentTimeMillis();
        new QuickSort().sort(array);
        long end = System.currentTimeMillis();
        System.out.println("快速排序-> " + (end - start));
        System.out.println(isSorted(array));

        for (int i = 2; i < 4; i++) {
            System.arraycopy(arrayCopy, 0, array, 0, length);
            System.out.println(isSorted(array));
            System.out.println(new CompareDifferenceIncreaseOrder().sort(array, i));
            System.out.println(isSorted(array));
        }

//        show(array);

    }

    private static int[] orderArray = {1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905, 8929, 16001, 36289, 64769, 146305, 260609, 2354689, 10000001};

    private static <E extends Comparable<E>> void sortKernal(E[] array, int index) {
        for (int i = index; i >= 0; i--) {
            for (int j = 0; j < array.length - orderArray[i]; j++) {
                int start = j + orderArray[i];
                int subscript = j;
                while (subscript >= 0 && less(array[start], array[subscript]))
                    subscript -= orderArray[i];
                backward(array, start, subscript + orderArray[i], orderArray[i]);
            }
        }
    }

    public <E extends Comparable<E>> int sort(E[] array, int t) {
        int index = 1;

        int[] newArray = new int[100];
        newArray[0] = 1;
        newArray[1] = t;

        while (newArray[index] < array.length) {
            newArray[index + 1] = (int) Math.pow(newArray[1], index + 1);
            index++;
        }

        orderArray = newArray;


        long startTime = System.currentTimeMillis();

        sortKernal(array, index - 1);

        long endTime = System.currentTimeMillis();

        return (int) (endTime - startTime);
    }

    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        int index = 1;
        while (orderArray[++index] < array.length)
            ;

        long startTime = System.currentTimeMillis();

        sortKernal(array, index - 1);

        long endTime = System.currentTimeMillis();

        System.out.println("time-> " + (endTime - startTime));
    }
}
