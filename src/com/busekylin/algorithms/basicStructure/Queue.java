package com.busekylin.algorithms.basicStructure;

/**
 * 文件 Queue 创建于 2017/7/29
 *先进先出队列(简称队列)是一种基于先进先出(FIFO)策略的集合类型
 * @author 赵磊
 * @version 1.0
 */
public interface Queue<Item> extends Iterable<Item> {
    void enqueue(Item item);

    Item dequeue();

    boolean isEmpty();

    int size();
}
