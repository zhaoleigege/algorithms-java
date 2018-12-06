package com.busekylin.algorithms.basicStructure;

import java.util.NoSuchElementException;

/**
 * 文件 Steque 创建于 2017/7/30
 *
 * @author 赵磊
 * @version 1.0
 */
public class Steque<Item> {
    private class Node {
        public Node() {
        }

        public Node(Item item) {
            this.item = item;
        }

        Item item;
        Node next;
    }

    private Node stackNode;
    private Node queueNode;
    private int size;

    public void push(Item item) {
        Node node = new Node();
        node.item = item;
        node.next = stackNode;
        stackNode = node;

        if (queueNode == null)
            queueNode = node;

        size++;
    }

    public Item pop() {
        if (isEmpty())
            throw new NoSuchElementException("没有数据了");

        Node node = stackNode;
        stackNode = node.next;
        size--;

        return node.item;
    }

    public void enqueue(Item item) {
        Node node = new Node(item);
        if (queueNode != null) {
            queueNode.next = node;
            queueNode = node;
        } else {
            queueNode = node;
        }

        if (stackNode == null)
            stackNode = node;

        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        Steque<String> steque = new Steque<>();

        for (int i = 0; i < 5; i++)
            steque.push("string" + i);

        for (int i = 0; i < 5; i++)
            steque.enqueue("string" + (i + 1) * 2);

        while (!steque.isEmpty())
            System.out.println(steque.pop());
    }
}
