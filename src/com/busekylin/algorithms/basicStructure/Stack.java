package com.busekylin.algorithms.basicStructure;

/**
 * 文件 Stack 创建于 2017/7/29
 * 下压栈(简称栈)是栈一种基于先进后出策略的集合类型
 *
 * @author 赵磊
 * @version 1.0
 */
public interface Stack<Item> extends Iterable<Item> {
    void push(Item item);

    Item Pop();

    boolean isEmpty();

    int size();
}
