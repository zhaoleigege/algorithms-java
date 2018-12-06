package com.busekylin.algorithms.basicStructure;

import java.util.Iterator;

/**
 * 文件 ListStack 创建于 2017/7/29
 *
 * @author 赵磊
 * @version 1.0
 */
public class ListStack<Item extends Comparable<Item>> implements Stack<Item> {
    private class Node {
        Item item;
        Node next;
    }

    private Node head;
    private int size;

    @Override
    public void push(Item item) {
        Node node = head;
        head = new Node();
        head.item = item;
        head.next = node;
        size++;
    }

    @Override
    public Item Pop() {
        Node node = head;
        head = node.next;
        size--;
        return node.item;
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
    public Iterator<Item> iterator() {
        return new ListStackIterator<>();
    }

    public Item removeLast() {
        if (size == 0)
            return null;

        size--;

        if (size == 0) {
            Item item = head.item;
            head = null;
            return item;
        }

        Node prev, node;
        prev = node = head;

        while (node.next != null) {
            prev = node;
            node = node.next;
        }

        Item item = node.item;
        prev.next = null;

        return item;
    }

    public Item delete(int k) {
        if (k <= 0)
            throw new IllegalArgumentException("参数错误");

        if (size <= 0)
            return null;

        size--;

        if (k == 1) {
            Item item = head.item;
            head = head.next;

            return item;
        }

        Node node, prev;
        prev = node = head;

        while (node != null && --k > 0) {
            prev = node;
            node = node.next;
        }

        if (k != 0)
            return null;

        Item item = node.item;
        prev.next = node.next;

        return item;

    }

    private class ListStackIterator<Item> implements Iterator<Item> {
        private Node node;

        public ListStackIterator() {
            this.node = head;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Item next() {
            Item item = (Item) node.item;
            node = node.next;
            return item;
        }
    }

    public boolean find(String key) {
        Node node = head;

        while (node != null) {
            if (node.item.equals(key))
                return true;
            node = node.next;
        }

        return false;
    }

    private void remove(Node node) {
        if (node.next != null) {
            remove(node.next);
            size--;
            node.next = null;
        }
    }

    public boolean removeAfter(Item item) {
        Node node = head;

        while (node != null) {
            if (node.item.equals(item)) {
                remove(node);
                return true;
            }
            node = node.next;
        }

        return false;
    }

    public boolean insertAfter(Item prev, Item item) {
        if (prev == null || item == null)
            return false;


        size++;
        Node node = head;
        while (node.next != null) {
            if (node.item.equals(prev)) {
                Node temp = new Node();
                temp.item = item;
                temp.next = node.next;
                node.next = temp;
                return true;
            }

            node = node.next;
        }

        Node temp = new Node();
        temp.item = item;
        node.next = temp;

        return true;
    }

    public Item max(Node node) {
        Item item = node.item;
        Item temp = null;
        if (node.next != null)
            temp = max(node.next);

        return temp == null || item.compareTo(temp) > 0 ? item : temp;
    }

    //递归版本
    public Node listReverse(Node node) {
        if (node.next != null) {
            Node temp;
            temp = listReverse(node.next);
            temp.next = node;
            node.next = null;

            return node;
        } else {
            head = node;
            return head;
        }
    }

    //迭代版本
    public void iterationReverse(Node node) {
        Node reverse, temp;
        reverse = null;

        while (head != null) {
            temp = head;
            head = head.next;
            temp.next = reverse;
            reverse = temp;
        }

        head = reverse;
    }

    public static void main(String[] args) {
        ListStack<String> listStack = new ListStack<>();

        for (int i = 0; i < 12; i++)
            listStack.push("string" + i);


//        System.out.println(listStack.removeLast());

//        System.out.println(listStack.delete(12));

//        System.out.println(listStack.find("string13"));

//        listStack.removeAfter("string10");

//        listStack.insertAfter("string18", "string111");

//        System.out.println("max = " + listStack.max(listStack.head));

        System.out.println("反转前============================");

        for (String string : listStack)
            System.out.println(string);

        System.out.println("反转后============================");

        listStack.iterationReverse(listStack.head);

        for (String string : listStack)
            System.out.println(string);

        System.out.println(listStack.size());


        ListStack<Integer> stack = new ListStack<>();
        int N = 7, M = 3, start = 0;

        for (int i = 0; i < N; i++)
            stack.push(i);

        stack.listReverse(stack.head);

        int prev = 0;
        while (N != 1) {
            prev++;
            if (prev > N)
                prev = 1;
            if (++start % M == 0) {
                System.out.print(stack.delete(prev) + " ");
                prev--;
                N--;
            }
        }

        System.out.println(stack.Pop());
    }
}
