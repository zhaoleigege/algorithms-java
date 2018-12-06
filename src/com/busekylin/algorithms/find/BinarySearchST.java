package com.busekylin.algorithms.find;

import com.busekylin.algorithms.basicStructure.ArrayQueue;
import com.busekylin.algorithms.basicStructure.Queue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * 文件 BinarySearchST 创建于 2017/8/11
 *
 * @author 赵磊
 * @version 1.0
 */
public class BinarySearchST<K extends Comparable<K>, V> implements Ordered<K, V> {
    private static final int INITIAL_CAPACITY = 15;
    private K[] keys;
    private V[] values;
    private int size;
    private int cache = 0;

    public BinarySearchST() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public BinarySearchST(int capacity) {
        keys = (K[]) new Comparable[capacity];
        values = (V[]) new Object[capacity];
    }

    private void resize(int capacity) {
        keys = Arrays.copyOf(keys, capacity);
        values = Arrays.copyOf(values, capacity);
    }

    @Override
    public void put(K key, V value) {
        if (value == null) {
            delete(key);
            return;
        } else if (cache < size && key.compareTo(keys[cache]) == 0) {
            values[cache] = value;
            return;
        }

        int index = rank(key);

        if (index < size && key.compareTo(keys[index]) == 0)
            values[index] = value;
        else {
            if (size == keys.length)
                resize(size + (size >> 1));

            for (int i = size; i > index; i--) {
                keys[i] = keys[i - 1];
                values[i] = values[i - 1];
            }
            keys[index] = key;
            values[index] = value;
            size++;
        }
    }

    @Override
    public V get(K key) {
        if (cache < size && key.compareTo(keys[cache]) == 0)
            return values[cache];

        int index = rank(key);
        if (index < size && key.compareTo(keys[index]) == 0)
            return values[index];

        return null;
    }

    @Override
    public V delete(K key) {
        int index;
        if (cache < size && key.compareTo(keys[cache]) == 0)
            index = cache;
        else
            index = rank(key);

        if (index < size && key.compareTo(keys[index]) == 0) {
            V value = values[index];
            for (int i = index; i < size - 1; i++) {
                keys[i] = keys[i + 1];
                values[i] = values[i + 1];
            }
            keys[--size] = null;
            values[size] = null;
            return value;
        }

        return null;
    }

    @Override
    public boolean contains(K key) {
        int index = rank(key);
        return index < size && key.compareTo(keys[index]) == 0;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public K min() {
        return keys[0];
    }

    @Override
    public K max() {
        return size > 0 ? keys[size - 1] : null;
    }

    @Override
    public K floor(K key) {
        if (cache < size && key.compareTo(keys[cache]) == 0)
            return keys[cache];

        int index = rank(key);
        if (index < size && key.compareTo(keys[index]) >= 0)
            return keys[index];

        return null;
    }

    @Override
    public K ceiling(K key) {
        if (cache < size && key.compareTo(keys[cache]) == 0)
            return keys[cache];

        int index = rank(key);
        if (index > 0) {
            if (index == size)
                return keys[size - 1];

            int status = key.compareTo(keys[index]);
            if (status == 0)
                return keys[index];
            else
                return keys[index + 1];
        }

        return null;
    }

    @Override
    public int rank(K key) {
        int left = 0, right = size - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int status = key.compareTo(keys[mid]);

            if (status == 0) {
                return mid;
            } else if (status < 0)
                right = mid - 1;
            else
                left = mid + 1;
        }

        cache = left;
        return left;
    }

    @Override
    public K select(int k) {
        if (k > 0 && k <= size)
            return keys[k - 1];

        return null;
    }

    @Override
    public V deleteMin() {
        V value = values[0];
        for (int i = 0; i < size; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }
        keys[--size] = null;
        values[size] = null;

        return value;
    }

    @Override
    public V deleteMax() {
        V value = values[--size];
        values[size] = null;
        keys[size] = null;

        return value;
    }

    @Override
    public int size(K left, K right) {
        int max = rank(right);
        if (max < size && right.compareTo(keys[max]) == 0)
            max++;

        return max - rank(left);
    }

    @Override
    public Iterable<K> keys(K left, K right) {
        Queue<K> queue = new ArrayQueue<>();
        int min = rank(left);
        int max = rank(right);
        if (max == size)
            max--;

        for (int i = min; i <= max; i++)
            queue.enqueue(keys[i]);

        return queue;
    }

    @Override
    public Iterable<K> keys() {
        Queue<K> queue = new ArrayQueue<>();
        for (int i = 0; i < size; i++)
            queue.enqueue(keys[i]);

        return queue;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new STIterator<>();
    }

    private class STIterator<K, V> implements Iterator<Entry<K, V>> {
        private int index;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Entry<K, V> next() {
            Entry entry = new Entry<>(keys[index], values[index]);
            index++;

            return entry;
        }
    }

    public static void main(String[] args) {
        int length = 10;
        BinarySearchST<Integer, Integer> searchST = new BinarySearchST<>();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(length * 10);
            searchST.put(num, num);
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.println(searchST.size(20, 90));

        for (Entry<Integer, Integer> entry : searchST)
            System.out.println("K-> " + entry.key + ", V-> " + entry.value);
    }
}
