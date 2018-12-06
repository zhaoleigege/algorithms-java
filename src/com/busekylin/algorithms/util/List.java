package com.busekylin.algorithms.util;

/**
 * 文件 List 创建于 2017/8/2
 *
 * @author 赵磊
 * @version 1.0
 */
public interface List<E> extends Iterable<E> {
    void insert(E item);

    void insert(int index, E item);

    E find(int index);

    /**
     * 从头部开始查询
     *
     * @param item
     * @return
     */
    int find(E item);

    /**
     * 从链表末尾开始查询
     *
     * @param item
     * @return
     */
    int findLast(E item);

    E delete(int index);

    /**
     * 默认删除从头部开始找到的第一个元素
     *
     * @param item
     * @return
     */
    boolean delete(E item);


    void addAll(List<? extends E> items);

    void addAll(int index, List<? extends E> items);

    void removeRange(int fromIndex, int toIndex);

    void reverse();

    void clean();

    boolean isEmpty();

    int size();

    E[] toArray(E[] array);
}
