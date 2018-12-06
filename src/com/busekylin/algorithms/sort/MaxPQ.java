package com.busekylin.algorithms.sort;

/**
 * 文件 MaxPQ 创建于 2017/8/9
 * 优先队列
 *
 * @author 赵磊
 * @version 1.0
 */
public interface MaxPQ<E extends Comparable<E>> {
    void Insert(E key);

    E max();

    E delMax();

    boolean isEmpty();

    int size();
}
