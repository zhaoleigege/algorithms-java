package com.busekylin.algorithms.find;

import java.util.Iterator;
import java.util.Random;

/**
 * 文件 BST 创建于 2017/8/12
 * 二叉查找树
 *
 * @author 赵磊
 * @version 1.0
 */
public class BST<K extends Comparable<K>, V> implements Ordered<K, V> {
    private Node<K, V> root;

    private class Node<K, V> {
        K key;
        V value;
        Node<K, V> left, right;
        int size;

        Node(K key, V value, Node<K, V> left, Node<K, V> right, int size) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.size = size;
        }
    }

    /**
     * @param node  即将被插入的接待你
     * @param key   节点的健
     * @param value 节点的新值
     * @return 返回该节点
     */
    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value, null, null, 1);
        }

        int status = key.compareTo(node.key);
        if (status == 0) {
            node.value = value;
        } else if (status > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.left = put(node.left, key, value);
        }

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    /**
     * @param node 待比较的节点
     * @param key  需要找的健
     * @return 返回找到的值，若健不存在则返回null
     */
    private V get(Node<K, V> node, K key) {
        if (node == null)
            return null;

        int status = key.compareTo(node.key);
        if (status == 0)
            return node.value;
        else if (status > 0)
            return get(node.right, key);
        else
            return get(node.left, key);
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public V delete(K key) {
        V[] value = (V[]) new Object[1];
        root = deleteKey(root, key, value);
        return value[0];
    }

    private Node<K, V> findMin(Node<K, V> node) {
        if (node == null)
            return null;

        Node<K, V> next = findMin(node.left);
        return next == null ? node : next;
    }

    private Node<K, V> deleteKey(Node<K, V> node, K key, V[] value) {
        if (node == null)
            return null;

        int status = node.key.compareTo(key);
        if (status > 0) {
            node.left = deleteKey(node.left, key, value);
        } else if (status < 0) {
            node.right = deleteKey(node.right, key, value);
        } else {
            value[0] = node.value;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node<K, V> current = findMin(node.right);
            current.right = deleteMin(node.right).node;
            current.left = node.left;
            node = current;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    private int size(Node<K, V> node) {
        if (node == null)
            return 0;

        return node.size;
    }

    @Override
    public int size() {
        return size(root);
    }

    private K min(Node<K, V> node) {
        if (node == null)
            return null;

        K key = min(node.left);
        return key == null ? node.key : key;
    }

    @Override
    public K min() {
        return min(root);
    }

    private K max(Node<K, V> node) {
        if (node == null)
            return null;

        K key = max(node.right);
        return key == null ? node.key : key;
    }

    @Override
    public K max() {
        return max(root);
    }

    private K floor(Node<K, V> node, K key) {
        if (node == null)
            return null;

        int status = key.compareTo(node.key);
        if (status == 0) {
            return key;
        } else if (status < 0) {
            return floor(node.left, key);
        } else {
            K nextKey = floor(node.right, key);
            return nextKey == null ? node.key : nextKey;
        }
    }

    @Override
    public K floor(K key) {
        return floor(root, key);
    }

    private K ceiling(Node<K, V> node, K key) {
        if (node == null)
            return null;

        int status = key.compareTo(node.key);
        if (status == 0) {
            return node.key;
        } else if (status < 0) {
            K nextKey = ceiling(node.left, key);
            return nextKey == null ? node.key : nextKey;
        } else {
            return ceiling(node.right, key);
        }
    }

    @Override
    public K ceiling(K key) {
        return ceiling(root, key);
    }

    private int rank(Node<K, V> node, K key) {
        if (node == null)
            return 0;

        int status = key.compareTo(node.key);

        if (status == 0)
            return size(node.left) + 1;

        if (status < 0)
            return rank(node.left, key);

        return size(node.left) + 1 + rank(node.right, key);
    }

    @Override
    public int rank(K key) {
        return rank(root, key);
    }

    public K select(Node<K, V> node, int k) {
        if (node == null)
            return null;

        int index = size(node.left) + 1;    //  获得该节点相对自己的左子树的排名

        if (index == k)
            return node.key;

        if (index > k)
            return select(node.left, k);

        return select(node.right, k - index);
    }

    @Override
    public K select(int k) {
        return select(root, k);
    }

    private class NodeValue<K, V> {
        Node<K, V> node;
        V value;

        NodeValue(Node<K, V> node, V value) {
            this.node = node;
            this.value = value;
        }
    }

    private NodeValue<K, V> deleteMin(Node<K, V> node) {
        if (node == null)
            return null;

        NodeValue<K, V> nodeValue = new NodeValue<>(node.left, null);
        if (node.left == null) {
            nodeValue.value = node.value;
            nodeValue.node = node.right;
            node.key = null;
            node.value = null;
            node.left = null;
            node.right = null;
        } else {
            nodeValue = deleteMin(node.left);
            node.left = nodeValue.node;
            nodeValue.node = node;
            node.size = size(node.left) + size(node.right) + 1;
        }

        return nodeValue;
    }

    @Override
    public V deleteMin() {
        if (root == null)
            return null;

        return deleteMin(root).value;
    }

    private NodeValue<K, V> deleteMax(Node<K, V> node) {
        if (node == null)
            return null;

        NodeValue<K, V> nodeValue = null;
        if (node.right == null) {
            nodeValue = new NodeValue<>(null, null);
            nodeValue.value = node.value;
            nodeValue.node = node.left;
            node.key = null;
            node.value = null;
            node.left = null;
            node.right = null;
        } else {
            nodeValue = deleteMax(node.right);
            node.right = nodeValue.node;
            nodeValue.node = node;
            node.size = size(node.left) + size(node.right) + 1;
        }

        return nodeValue;
    }

    @Override
    public V deleteMax() {
        if (root == null)
            return null;

        return deleteMax(root).value;
    }

    @Override
    public int size(K left, K right) {
        return 0;
    }

    @Override
    public Iterable<K> keys(K left, K right) {
        return null;
    }

    @Override
    public Iterable<K> keys() {
        return null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return null;
    }

    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            int num = random.nextInt(100);
//            System.out.print(num + " ");
//            bst.put(num, "string" + i);
//        }
        bst.put(43, "asd43");
        bst.put(21, "asd21");
        bst.put(75, "asd75");
        bst.put(28, "asd28");
        bst.put(11, "asd11");
        bst.put(87, "asd87");
        bst.put(56, "asd56");
        bst.put(92, "asd92");
        bst.put(16, "asd16");
        bst.put(43, "asd43");
        System.out.println();
        System.out.println("大小-> " + bst.size());
        System.out.println(bst.floor(20));
        System.out.println(bst.ceiling(20));
        System.out.println(bst.select(7));
        System.out.println(bst.rank(20));
        System.out.println(bst.deleteMin());
        System.out.println(bst.deleteMax());
        System.out.println(bst.delete(56));
        System.out.println(bst.delete(16));
        System.out.println(bst.size());
    }
}
