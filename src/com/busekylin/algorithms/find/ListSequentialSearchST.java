package com.busekylin.algorithms.find;

import java.util.Iterator;

/**
 * 文件 ListSequentialSearchST 创建于 2017/8/11
 *
 * @author 赵磊
 * @version 1.0
 */
public class ListSequentialSearchST<K, V> implements Unordered<K, V> {
    private Node<K, V> head;
    private int size;

    private class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node<K, V> find(K key) {
        Node<K, V> node = head;
        while (node != null) {
            if (node.key.equals(key))
                return node;
            node = node.next;
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        Node<K, V> node = find(key);
        if (node != null) {
            node.value = value;
        } else {
            node = new Node<>(key, value, head);
            head = node;
        }

        size++;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = find(key);
        if (node == null)
            return null;

        return node.value;
    }

    private void cleanNode(Node<K, V> node) {
        node.value = null;
        node.key = null;
        node.next = null;
    }

    @Override
    public V delete(K key) {
        V value = null;
        Node<K, V> prev, node;
        prev = null;
        node = head;
        while (node != null) {
            if (node.key.equals(key))
                break;
            prev = node;
            node = node.next;
        }

        if (prev == null) {
            if (node != null) {
                value = node.value;
                cleanNode(node);
                head = null;
            }
        } else {
            prev.next = node.next;
            value = node.value;
            cleanNode(node);
        }

        size--;
        return value;
    }

    @Override
    public boolean contains(K key) {
        return find(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<K> keys() {
        return new STIterable<>(head);
    }

    private class KeysIterator<K> implements Iterator<K> {

        private Node<K, V> node;

        public KeysIterator(Node<K, V> node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public K next() {
            K key = node.key;
            node = node.next;
            return key;
        }
    }

    private class STIterable<K> implements Iterable<K> {
        private Node<K, V> node;

        public STIterable(Node<K, V> node) {
            this.node = node;
        }

        @Override
        public Iterator<K> iterator() {
            return new KeysIterator<>(node);
        }
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new STIterator<>(head);
    }

    private class STIterator<K, V> implements Iterator<Entry<K, V>> {
        private Node<K, V> node;

        public STIterator(Node<K, V> node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Entry<K, V> next() {
            Entry<K, V> entry = new Entry<>(node.key, node.value);
            node = node.next;

            return entry;
        }
    }


    public static void main(String[] args) {
        Unordered<Integer, String> unordered = new ListSequentialSearchST<>();
        for (int i = 0; i < 10; i++)
            unordered.put(i, "string" + i);

        unordered.delete(6);

        for (Entry<Integer, String> entry : unordered)
            System.out.println("key-> " + entry.key + ", value-> " + entry.value);

        Iterable<Integer> keys = unordered.keys();
        for (int key : keys)
            System.out.println(key);
    }
}
