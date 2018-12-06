package com.busekylin.algorithms.sort;

/**
 * 文件 SelectSort 创建于 2017/8/2
 * <p>
 * 选择排序
 *
 * @author 赵磊
 * @version 1.0
 */
public class SelectSort extends SortTemplate {
    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        E min;
        int index;
        for (int i = 0; i < array.length - 1; i++) {
            min = array[i];
            index = i;
            for (int j = i + 1; j < array.length; j++)
                if (less(array[j], min)) {
                    min = array[j];
                    index = j;
                }

            exchange(array, i, index);
        }
    }

    public static void main(String[] args) {
        Integer[] a = {2, 5, 1, 5, 2, 6, 9, 3};
        new SelectSort().sort(a);
        show(a);
    }
}
