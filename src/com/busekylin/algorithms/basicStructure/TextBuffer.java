package com.busekylin.algorithms.basicStructure;

import java.util.Iterator;

/**
 * 文件 TextBuffer 创建于 2017/7/30
 * 文本编辑器的缓冲区
 *
 * @author 赵磊
 * @version 1.0
 */
public class TextBuffer<E> implements Iterable<E> {
    private int size;
    private Node<E> head;
    private Node<E> tail;
    private Cursor cursor;

    private class Node<E> {
        E item;
        Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    private class Cursor {
        Node<E> current;    //指向当前添加的元素
        Node<E> prev;       //指向当前添加元素的上一个元素
        int position;       //记录当前位置

        Cursor(Node<E> current, Node<E> prev, int position) {
            this.current = current;
            this.prev = prev;
            this.position = position;
        }
    }

    public void insert(E c) {
        size++;

        Node<E> node = new Node<>(c, null);
        if (head == null) {
            head = node;
            tail = head;
            cursor = new Cursor(head, null, 1);

            return;
        }

        if (cursor.position == 0) {//插入到头部
            node.next = head;
            head = node;
            cursor.current = node;
        } else {                    //插入到别的位置
            node.next = cursor.current.next;
            cursor.current.next = node;
            cursor.prev = cursor.current;
            cursor.current = node;
        }
        cursor.position++;

        if (cursor.position == size) {//插入到尾部
            tail = cursor.current;
        }
    }

    private Node<E> find(int key) {
        if (key < 0 || key > size)
            throw new IllegalArgumentException(key + " 不合法");

        if (key == 0)
            return null;

        Node<E> node = head;
        while (node != null && --key > 0)
            node = node.next;

        if (key == 0)
            return node;
        else
            return null;
    }

    public E delete() {
        Node<E> node = cursor.current;

        if (node == null)
            return null;

        Node<E> prevNode = cursor.prev;
        E item = node.item;

        if (prevNode != null) {
            prevNode.next = node.next;
            cursor.current = prevNode;
            if (node.next == null)
                tail = cursor.current;
            cursor.prev = find(cursor.position - 2);
        } else {
            head = node.next;
            cursor.current = head;
            if (size == 1)
                tail = head;
        }

        cursor.position--;
        size--;

        node.item = null;
        node.next = null;

        return item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void left(int k) {
        int position = cursor.position - k;
        if (position < 1) {
            cursor.position = 0;
            cursor.prev = null;
            cursor.current = null;
        } else if (position == 1) {
            cursor.position = 1;
            cursor.prev = null;
            cursor.current = head;
        } else {
            Node<E> node = find(position - 1);
            if (node == null) {
                throw new RuntimeException("左移位出现未知错误");
            }

            cursor.position = position;
            cursor.prev = node;
            cursor.current = node.next;
        }
    }

    public void right(int k) {
        int position = cursor.position + k;
        if (position >= size) {
            cursor.position = size;
            cursor.current = tail;
            cursor.prev = find(size - 1);
        } else if (position == 1) {
            cursor.position = 1;
            cursor.prev = null;
            cursor.current = head;
        } else {
            cursor.position = position + 1;
            cursor.prev = find(position - 1);
            cursor.current = cursor.prev.next;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new BufferIterator<>(head);
    }

    private class BufferIterator<E> implements Iterator<E> {
        private Node<E> node;

        public BufferIterator(Node<E> node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            E item = node.item;
            node = node.next;
            return item;
        }
    }

    public static void main(String[] args) {
        TextBuffer<String> buffer = new TextBuffer<>();

        for (int i = 0; i < 10; i++)
            buffer.insert("string" + i);

        buffer.left(9);

        for (int i = 0; i < 2; i++)
            buffer.delete();

        buffer.left(2);

        buffer.insert("zhaolei");
        buffer.insert("asd");
        buffer.insert("asda");
        buffer.insert("aqweqwesd");

        buffer.right(1);
        buffer.insert("asd");
        buffer.insert("zhaolei");
        buffer.insert("qweqw");
        buffer.insert("sdfsdf");

        buffer.delete();

        for (String string : buffer)
            System.out.println(string);
    }
}
