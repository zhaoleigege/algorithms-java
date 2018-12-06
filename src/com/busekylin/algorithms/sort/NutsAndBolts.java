package com.busekylin.algorithms.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.busekylin.algorithms.util.UtilRandom.exChange;
import static com.busekylin.algorithms.util.UtilRandom.random;

/**
 * 文件 NutsAndBolts 创建于 2017/8/8
 * 螺丝和螺帽
 *
 * @author 赵磊
 * @version 1.0
 */

public class NutsAndBolts {
    private static class NutBolt {
        int type;       //判定是螺丝还是螺帽 0螺丝 1螺帽
        int size;       //尺寸

        NutBolt(int type, int size) {
            this.type = type;
            this.size = size;
        }
    }

    private static void sortNuts(NutBolt[] bolts) {
        int i = -1, j = bolts.length;

        while (true) {
            while (bolts[++i].type < 1)
                if (i >= j)
                    break;

            while (bolts[--j].type > 0)
                if (j <= i)
                    break;

            if (i >= j)
                break;

            exChange(bolts, i, j);
        }
    }

    /**
     * 比较方法为：先找螺丝的第一个下标，然后在螺帽的小数组中把螺帽分成小于这个螺丝的一部份，等于这个螺丝的一部分，大于螺丝的一部分
     * 然后根据这个螺帽反过来区分螺丝的数组，这样螺丝螺帽各有三部分，然后在递归调用此方法，分别调用小于这个螺丝的和大于这个螺丝的一部分
     *
     * @param bolts  螺丝螺帽数组
     * @param bolt   螺丝的起始下标
     * @param nut    螺帽的起始下标
     * @param length 比较的长度
     */
    private static void sortSize(NutBolt[] bolts, int bolt, int nut, int length) {
        if (length <= 1)
            return;

        int nutIndex = sort(bolts[bolt].size, bolts, nut, length);
        int boltIndex = sort(bolts[nutIndex].size, bolts, bolt, length);

        sortSize(bolts, bolt, nut, boltIndex - bolt);
        sortSize(bolts, boltIndex + 1, nutIndex + 1, length - 1 - boltIndex + bolt);
    }

    //从右边开始扫描数组如果大于标记值就继续往前扫描，如果遇到等于或者小于的那么就停止，这时j指向这个元素
    //从左边开始扫描数组如果小于标记值就继续往前扫描，如果遇到等于或者大于的那么就停止，这时i指向这个元素
    //这时如果i不等于j，那么两者交换，可以保证左边的数小于等于标记值，右边的数大于等于标记值，
    //如果交换后两者都不等于标记值那么继续重复前述步骤，如果其中一方等于标记值，那么它就不能动，等待另外一方来交换，
    //这样标记值其实一直在i或者j的控制下，当i等于j时其实这两者都指向等于标记值的下标。

    //这种情况仅适用于标记值只有一个并且该标记值一定在这个数组中
    private static int sort(int sign, NutBolt[] bolts, int index, int length) {
        int i = index, j = index + length - 1;

        while (true) {
            while (bolts[j].size > sign) {
                if (j == i)
                    break;
                j--;
            }

            while (bolts[i].size < sign) {
                if (i == j)
                    break;
                i++;
            }

            if (i == j)
                break;

            exChange(bolts, i, j);
        }

        return j;
    }

    public static void main(String[] args) {
        int length = 200;
        Random random = new Random();
        NutBolt[] bolts = new NutBolt[length * 2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 2 * length; i += 2) {
            int size = random.nextInt(length * 10);
            if (map.get(size) == null) {
                map.put(size, 1);
            } else {
                i -= 2;
                continue;
            }
            bolts[i] = new NutBolt(0, size);
            bolts[i + 1] = new NutBolt(1, size);
        }

        //打乱数组
        random(bolts);

        //按照螺丝螺帽来分类
        sortNuts(bolts);

        for (NutBolt bolt : bolts)
            System.out.println(bolt.size);

        //螺丝和螺帽按照大小来排序
        sortSize(bolts, 0, bolts.length / 2, bolts.length / 2);

        for (NutBolt bolt : bolts)
            System.out.println("type-> " + bolt.type + ", size-> " + bolt.size);
    }
}
