package com.busekylin.algorithms.find;

/**
 * 文件 Ordered 创建于 2017/8/11
 *
 * @author 赵磊
 * @version 1.0
 */
public interface Ordered<K extends Comparable<K>, V> extends Iterable<Entry<K, V>> {

    //插入一条数据到表中，若value为空则将健删除
    void put(K key, V value);

    //获取健的值，若健不存在则返回空
    V get(K key);

    //从表中删除键及其对应的值
    V delete(K key);

    //健key是否存在表中
    boolean contains(K key);

    //表的大小
    int size();

    //表是否为空
    default boolean isEmpty() {
        return size() == 0;
    }

    //最小的健
    K min();

    //最大的健
    K max();

    //小于等于key的最大健
    K floor(K key);

    //大于等于key的最小健
    K ceiling(K key);

    //返回表中小于该健的数量
    int rank(K key);

    //排名为k的健
    K select(int k);

    //删除最小的健
    V deleteMin();

    //删除最大的健
    V deleteMax();

    //[left, right]之间健的数量
    int size(K left, K right);

    //[left, right]之间所有的健，已排序
    Iterable<K> keys(K left, K right);

    //表中所有的健，已排序
    Iterable<K> keys();
}
