package com.busekylin.algorithms.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 文件 SumFast 创建于 2017/7/31
 *
 * @author 赵磊
 * @version 1.0
 */
public class SumFast {
    private static final int LENGTH = 100;

    public static void main(String[] args) {
        Random random = new Random();
        Integer[] a = new Integer[LENGTH];

        for (int i = 0; i < LENGTH; i++)
            a[i] = random.nextInt(LENGTH) - LENGTH / 2;

        Arrays.sort(a);

        int start = 0, end = LENGTH - 1;
        int count = 0;

        while (start < end) {
            int sum = a[start] + a[end];
            if (sum > 0) {
                end--;
            } else if (sum < 0) {
                start++;
            } else {
                int tempX = start;
                while (tempX + 1 < end && a[tempX].equals(a[tempX + 1]))
                    tempX++;

                int tempY = end;
                while (tempY - 1 > tempX && a[tempY].equals(a[tempY - 1]))
                    tempY--;

                if (a[start] == 0) {
                    count = count + (tempX - start + 1) * (tempX - start + 2) / 2;
                } else {
                    count = count + (tempX - start + 1) * (end - tempY + 1);
                }
                start = tempX + 1;
                end = tempY - 1;
            }
        }

        System.out.println(count);
    }
}
