package com.busekylin.algorithms.util;

import java.util.Random;

/**
 * 文件 UtilRandom 创建于 2017/8/8
 *
 * @author 赵磊
 * @version 1.0
 */
public class UtilRandom {
    //使数组乱序
    public static <E> void random(E[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length - 1; i++) {
            exChange(array, i, random.nextInt(array.length - i) + i);
        }
    }

    public static <E> void exChange(E[] array, int index1, int index2) {
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
