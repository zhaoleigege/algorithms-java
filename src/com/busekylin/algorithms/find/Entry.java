package com.busekylin.algorithms.find;

/**
 * 文件 Entry 创建于 2017/8/11
 *
 * @author 赵磊
 * @version 1.0
 */
public class Entry<K, V> {
    public K key;
    public V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

}
