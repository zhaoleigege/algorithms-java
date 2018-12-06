package com.busekylin.algorithms.sort;

/**
 * 文件 IndexMaxPQ 创建于 2017/8/10
 *
 * @author 赵磊
 * @version 1.0
 */
public interface IndexMaxPQ<E extends Comparable<E>> {
    void insert(int k, E item);

    void change(int k, E item);

    boolean contains(int k);

    void delete(int k);

    E max();

    int maxIndex();

    int delMax();

    boolean isEmpty();

    int size();
}
