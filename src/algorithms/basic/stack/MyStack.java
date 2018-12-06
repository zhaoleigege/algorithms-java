package algorithms.basic.stack;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

/**
 * 通过链表的形式实现的栈
 *
 * @author 赵磊
 */
public class MyStack<Item> implements Iterable<Item> {

    /**
     * 内部类，代表表中的每一个节点类型
     */
    private class Node {
        Item item;
        Node next;
    }

    /**
     * 表头
     */
    private Node first;

    /**
     * 表的大小
     */
    private int size;

    /**
     * 构造函数
     */
    public MyStack() {
        size = 0;
    }

    /**
     * 添加一个元素来构造这个栈
     */
    public MyStack(Item item) {
        push(item);
        size = 0;
    }

    /**
     * @param item 添加一个元素
     * @return boolean  添加成功返回true
     */
    public boolean push(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;
        first = newNode;
        size++;
        return true;
    }

    /**
     * @return boolean 表是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 弹出一个元素
     *
     * @return Item
     */
    public Item pop() {
        if (isEmpty())
            throw new IllegalArgumentException("表中没有任何数据");
        Node oldNode = first;
        first = first.next;
        size--;
        return oldNode.item;
    }

    /**
     * @return Item 返回一个元素但是不弹出
     */
    public Item peek() {
        if (isEmpty())
            throw new IllegalArgumentException("表中没有任何数据");
        return first.item;
    }

    /**
     * @return int 获得表的大小
     */
    public int getSize() {
        return size;
    }


    @Override
    public Iterator iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item> {

        private Node currentNode = null;
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public Item next() {
            if (currentNode == null)
                currentNode = first;
            else
                currentNode = currentNode.next;
            current++;
            return currentNode.item;
        }

    }


    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>(0);

        /**测试Iterable接口*/
//        for (int i = 0; i < 10; i++)
//            stack.push(i);
//
//        for (int number : stack) {
//            System.out.println("值 -> " + number);
//        }

        /**括号匹对*/
//        char c, value;
//        MyStack<Character> characters = new MyStack<>();
//        while (!StdIn.isEmpty()) {
//            c = StdIn.readChar();
//            if (c == 'q')
//                break;
//            switch (c) {
//                case ']':
//                    value = characters.pop();
//                    if (value != '[') {
//                        System.out.println("false");
//                        return;
//                    }
//                    break;
//                case ')':
//                    value = characters.pop();
//                    if (value != '(') {
//                        System.out.println("false");
//                        return;
//                    }
//                    break;
//                case '}':
//                    value = characters.pop();
//                    if (value != '{') {
//                        System.out.println("false");
//                        return;
//                    }
//                    break;
//                default:
//                    characters.push(c);
//            }
//        }
//
//        System.out.println("true");

        /**中序表达式转后序表达式*/
        char c;
        while (!StdIn.isEmpty()){
            c = StdIn.readChar();
            if(c == '=')
                break;
//            switch (c){
//                case
//            }
        }
    }

}
