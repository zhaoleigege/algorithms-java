package com.busekylin.algorithms.find;

/**
 * 文件 Unordered 创建于 2017/8/11
 *
 * @author 赵磊
 * @version 1.0
 */
public interface Unordered<K, V> extends Iterable<Entry<K, V>> {
    void put(K key, V value);

    V get(K key);

    V delete(K key);

    boolean contains(K key);

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    Iterable<K> keys();
}
