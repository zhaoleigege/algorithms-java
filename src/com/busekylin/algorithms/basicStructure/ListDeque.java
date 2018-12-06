package com.busekylin.algorithms.basicStructure;

import java.util.Iterator;

/**
 * 文件 ListDeque 创建于 2017/7/30
 *
 * @author 赵磊
 * @version 1.0
 */
public class ListDeque<Item> implements Deque<Item> {
    private class Node {
        Item item;
        Node left;
        Node right;
    }

    private Node leftFirst;
    private Node rightFirst;
    private int size;

    public ListDeque() {
        Node node = new Node();
        leftFirst = rightFirst = node;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void pushLeft(Item item) {
        size++;

        if (leftFirst.item == null) {
            leftFirst.item = item;
            return;
        }

        Node node = new Node();
        node.item = item;

        node.right = leftFirst;
        leftFirst.left = node;
        leftFirst = node;
    }

    @Override
    public void pushRight(Item item) {
        size++;

        if (rightFirst.item == null) {
            rightFirst.item = item;
            return;
        }

        Node node = new Node();
        node.item = item;

        node.left = rightFirst;
        rightFirst.right = node;
        rightFirst = node;
    }

    @Override
    public Item popLeft() {
        if (isEmpty())
            return null;

        if (size == 1) {
            Item item = leftFirst.item;
            leftFirst.item = null;
            size--;

            return item;
        }

        Item item = leftFirst.item;

        leftFirst = leftFirst.right;
        leftFirst.left = null;

        size--;

        return item;
    }

    @Override
    public Item popRight() {
        if (isEmpty())
            return null;

        if (size == 1) {
            Item item = rightFirst.item;
            rightFirst.item = null;
            size--;

            return item;
        }

        Item item = rightFirst.item;

        rightFirst = rightFirst.left;
        rightFirst.right = null;

        size--;

        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListDequeIterator<>();
    }

    private class ListDequeIterator<Item> implements Iterator<Item> {
        Node node;

        public ListDequeIterator() {
            node = leftFirst;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Item next() {
            Item item = (Item) node.item;
            node = node.right;
            return item;
        }
    }

    public static void main(String[] args) {
        ListDeque<String> listDeque = new ListDeque<>();

        for (int i = 0; i < 5; i++)
            listDeque.pushLeft("string" + i);

        for (int i = 6; i < 10; i++)
            listDeque.pushRight("string" + i);

        for (String string : listDeque)
            System.out.println(string);
    }
}
