package com.busekylin.algorithms.basicStructure;

/**
 * 文件 Deque 创建于 2017/7/30
 *
 * @author 赵磊
 * @version 1.0
 */
public interface Deque<Item> extends Iterable<Item> {
    boolean isEmpty();

    int size();

    void pushLeft(Item item);

    void pushRight(Item item);

    Item popLeft();

    Item popRight();
}
