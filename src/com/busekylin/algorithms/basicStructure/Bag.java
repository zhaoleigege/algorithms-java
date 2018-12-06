package com.busekylin.algorithms.basicStructure;

/**
 * 文件 Bag 创建于 2017/7/29
 * <p>
 * 背包是一种不支持从中删除元素的集合数据类型，它的目的是帮助从用例中收集元素并迭代遍历所有的收集到的元素
 *
 * @author 赵磊
 * @version 1.0
 */
public interface Bag<Item> extends Iterable<Item> {
    void add(Item item);

    boolean isEmpty();

    int size();
}
