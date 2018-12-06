package com.busekylin.algorithms.util;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * 文件 LinkedList 创建于 2017/8/2
 *
 * @author 赵磊
 * @version 1.0
 */
public class LinkedList<E extends Comparable<E>> implements List<E> {
    private boolean allowNullable;
    private int size;
    private Node<E> first;
    private Node<E> last;
    private int modCount = 0;

    public LinkedList() {
        this(true);
    }

    public LinkedList(boolean allowNullable) {
        this.allowNullable = allowNullable;
    }

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * 这是插入的时候判断插入位置
     *
     * @param index 输入的下标
     * @return 返回下标是否合法
     */
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 这是获取值时判断下标是否合法
     *
     * @param index 输入的下标
     * @return 返回下标是否合法
     */
    private boolean isIllegalIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * 在链表的最后一位添加一个元素
     *
     * @param item 添加的内容
     */
    @Override
    public void insert(E item) {
        if (!allowNullable && item == null)
            throw new IllegalArgumentException("参数不能为空");

        Node<E> node = new Node<>(item, last, null);
        if (last == null) {
            last = node;
            first = node;
        } else {
            last.next = node;
            last = node;
        }

        modCount++;
        size++;
    }

    /**
     * @param index 要寻找的Node节点下标
     * @return 返回找到的Node节点，调用此方法的类保证该方法返回值不为null
     */
    private Node<E> findNode(int index) {
        Node<E> node = first;
        while (index-- > 0)
            node = node.next;

        return node;
    }

    /**
     * 给链表添加一个节点
     *
     * @param index 添加的节点的下标，(0 <= index <= size)
     * @param item  节点的内容
     */
    @Override
    public void insert(int index, E item) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("下标越界: " + index);

        if (index == size) {
            insert(item);
        }

        if (!allowNullable && item == null)
            throw new IllegalArgumentException("参数不能为空");

        Node<E> nodeNext = findNode(index);
        Node<E> node = new Node<>(item, nodeNext.prev, nodeNext);
        if (nodeNext.prev == null) {
            first = node;
        } else {
            nodeNext.prev.next = node;
        }
        nodeNext.prev = node;
        modCount++;
        size++;
    }

    @Override
    public E find(int index) {
        if (!isIllegalIndex(index))
            throw new IndexOutOfBoundsException("下标越界: " + index);

        if (isEmpty())
            throw new NoSuchElementException();

        return findNode(index).item;
    }

    private Node<E> findNode(E item) {
        Node<E> node = first;

        while (node != null) {
            if (node.item.equals(item))
                return node;
            node = node.next;
        }

        return null;
    }

    @Override
    public int find(E item) {
        Node<E> node = first;
        int index = 0;

        while (node != null) {
            if (node.item.equals(item))
                return index;
            node = node.next;
            index++;
        }

        return -1;
    }

    private Node<E> findNodeLast(E item) {
        Node<E> node = last;

        while (node != null) {
            if (node.item.equals(item)) {
                return node;
            }

            node = node.prev;
        }
        return null;
    }

    @Override
    public int findLast(E item) {
        Node<E> node = last;
        int index = 0;

        while (node != null) {
            if (node.item.equals(item))
                return size - 1 - index;
            node = node.prev;
            index++;
        }

        return -1;
    }

    /**
     * @param node 链表中要删除的节点
     */
    private E delete(Node<E> node) {
        Node<E> nodePrev = node.prev;
        Node<E> nodeNext = node.next;

        if (nodePrev == null) {
            if (nodeNext == null) {
                first = last = null;
            } else {
                nodeNext.prev = null;
                first = nodeNext;
                node.next = null;
            }
        } else {
            nodePrev.next = node.next;
            if (nodeNext == null) {
                last = nodePrev;
                node.prev = null;
            } else {
                nodeNext.prev = nodePrev;
                node.prev = null;
                node.next = null;
            }
        }
        E item = node.item;
        node.item = null;

        modCount++;
        size--;

        return item;
    }

    /**
     * 删除链表的一个节点
     *
     * @param index 节点的下标，(0 <= index <= size)
     * @return 返回被删除节点的内容
     */
    @Override
    public E delete(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("下标越界: " + index);

        if (isEmpty())
            throw new NoSuchElementException();

        Node<E> node = findNode(index);

        return delete(node);
    }

    /**
     * @param item 传入要删除的节点
     * @return 节点存在就删除并且返回true，如果节点不存在返回false
     */
    @Override
    public boolean delete(E item) {
        Node<E> node = findNode(item);

        if (node == null)
            return false;

        delete(node);

        return true;
    }

    @SuppressWarnings("unchecked")
    private Node<E>[] getHeadAndTail(List<? extends E> items) {
        Node<E> tempFirst, tempLast, temp;
        tempFirst = tempLast = temp = null;

        for (E item : items) {
            if (tempFirst == null) {
                tempFirst = new Node<>(item, null, null);
                tempLast = tempFirst;
                temp = tempFirst;
            } else {
                tempLast = new Node<>(item, temp, null);
                temp.next = tempLast;
                temp = tempLast;
            }
        }

        Node<E>[] nodes = (Node<E>[]) new Node[2];
        nodes[0] = tempFirst;
        nodes[1] = tempLast;

        return nodes;
    }

    @Override
    public void addAll(List<? extends E> items) {
        Node<E>[] nodes = getHeadAndTail(items);
        Node<E> tempFirst, tempLast;
        tempFirst = nodes[0];
        tempLast = nodes[1];

        if (first == null) {
            first = tempFirst;
        } else {
            last.next = tempFirst;
            tempFirst.prev = last;
        }
        last = tempLast;

        size += items.size();
        modCount++;
    }

    @Override
    public void addAll(int index, List<? extends E> items) {
        if (items.size() < 1)
            return;

        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("下标越界: " + index);

        if (index == size)
            addAll(items);

        Node<E> node = findNode(index);
        Node<E>[] nodes = getHeadAndTail(items);
        Node<E> tempFirst, tempLast;
        tempFirst = nodes[0];
        tempLast = nodes[1];

        if (node == first) {
            first.prev = tempLast;
            tempLast.next = first;
            first = tempFirst;
        } else {
            tempLast.next = node.next;
            node.next.prev = tempLast;
            node.next = tempFirst;
            tempFirst.prev = node;
        }
        size += items.size();
        modCount++;
    }

    private void nodeDelete(Node<E> node) {
        node.prev = null;
        node.next = null;
        node.item = null;
    }

    @Override
    public void removeRange(int fromIndex, int toIndex) {
        if (!isIllegalIndex(fromIndex) || !isIllegalIndex(toIndex))
            throw new IndexOutOfBoundsException("不合法的下标: " + fromIndex + ", " + toIndex);

        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex必须小于等于toIndex");

        if (fromIndex == toIndex) {
            delete(fromIndex);
            return;
        }

        size -= (toIndex - fromIndex + 1);
        modCount++;

        Node<E> node = findNode(fromIndex);
        Node<E> tempLast = node.next;

        while (toIndex > fromIndex + 1) {
            Node<E> temp = tempLast;
            temp.prev = null;
            temp.item = null;
            tempLast = temp.next;
            temp.next = null;
            toIndex--;
        }

        if (node == first) {
            if (tempLast.next == null) {
                first = last = null;
            } else {
                first = tempLast.next;
                tempLast.next.prev = null;
            }
            nodeDelete(node);
            nodeDelete(tempLast);
        } else {
            if (tempLast.next == null) {
                last = node.prev;
                last.next = null;
            } else {
                node.prev.next = tempLast.next;
                tempLast.next.prev = node.prev;
            }
            nodeDelete(node);
            nodeDelete(tempLast);
        }
    }

    /**
     * 实现链表的反转
     */
    @Override
    @SuppressWarnings("unchecked")
    public void reverse() {
        Node<E>[] nodes = (Node<E>[]) new Node[2];
        nodes[0] = first;
        nodes[1] = last;
        nodes = recursiveReverse(nodes);
        first = nodes[0];
        last = nodes[1];
        modCount++;
    }

    /**
     * 递归方式实现链表的反转
     *
     * @param nodes nodes[0]代表要反转的链表的头指针，nodes[1]代表要反转的链表的尾指针
     * @return 返回的nodes中把nodes[0]和nodes[1]的值换了，因为java方法只能返回一个值，所以得通过数组的方法返回两个值
     */
    private Node<E>[] recursiveReverse(Node<E>[] nodes) {
        if (nodes[0] == null)
            return null;

        if (nodes[0].next == null)
            return nodes;

        Node<E> start = nodes[0];
        nodes[0] = nodes[0].next;

        nodes = recursiveReverse(nodes);

        Node<E> temp = nodes[0];
        Node<E> end = nodes[1];

        temp.prev = null;
        end.next = start;
        start.prev = end;
        start.next = null;
        end = start;
        nodes[0] = temp;
        nodes[1] = end;

        return nodes;
    }

    /**
     * 清除所有的元素，为了java的自动垃圾回收必须把所有元素的指向变为null
     */
    @Override
    public void clean() {
        Node<E> node = first;
        Node<E> temp;

        while (node != null) {
            temp = node.next;
            node.item = null;
            node.prev = null;
            node.next = null;
            node = temp;
            size--;
        }

        first = null;
        last = null;
        modCount++;
    }

    /**
     * @return 链表是否为空，不为空返回false，否则返回true
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return 链表的大小
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] array) {
        if (array.length < size) {
            array = (E[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), size);
        }
        int index = 0;
        Node<E> node = first;

        while (node != null) {
            array[index++] = node.item;
            node = node.next;
        }

        if (array.length > size)
            array[size] = null;

        return array;
    }

    //对链表进行排序
    @SuppressWarnings("unchecked")
    public void sort() {
        if (!isEmpty())
            mergeSort(new Node[]{first, last}, size);

        modCount++;
    }

    @SuppressWarnings("unchecked")
    private Node<E>[] mergeSort(Node<E>[] nodes, int length) {
        Node<E> head = nodes[0], tail = nodes[1];

        if (head == tail)
            return nodes;

        int index = length / 2;
        Node<E> mid = head;
        while (--index > 0) {
            mid = mid.next;
        }

        Node<E>[] nodePrev = mergeSort(new Node[]{head, mid}, length / 2);
        Node<E>[] nodeNext = mergeSort(new Node[]{nodePrev[1].next, tail}, length - length / 2);

        if (nodeNext[0].item.compareTo(nodePrev[1].item) < 0) {
            nodes = mergeSort(new Node[]{nodePrev[0], nodePrev[1], nodeNext[1]});
        } else {
            nodes = new Node[]{nodePrev[0], nodeNext[1]};
        }

        return nodes;
    }

    @SuppressWarnings("unchecked")
    private Node<E>[] mergeSort(Node<E>[] nodes) {
        Node<E> head = nodes[0], mid = nodes[1], tail = nodes[2];
        boolean in = true;
        mid = mid.next;
        while (head != mid && in) {
            if (mid.item.compareTo(head.item) < 0) {
                if (head == nodes[0])
                    nodes[0] = mid;

                Node<E> temp = mid.next;
                if (temp == null) {
                    last = mid.prev;
                } else {
                    temp.prev = mid.prev;
                }
                mid.prev.next = temp;

                Node<E> headTemp = head.prev;
                if (headTemp == null) {
                    first = mid;
                } else {
                    headTemp.next = mid;
                }
                mid.prev = headTemp;

                head.prev = mid;
                mid.next = head;

                if (mid == tail) {
                    nodes[2] = head;
                    in = false;
                }
                mid = temp;
            } else {
                head = head.next;
            }
        }

        return new Node[]{nodes[0], nodes[2]};
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator(first, modCount);
    }

    private class ListIterator implements Iterator<E> {
        Node<E> node;
        int expectedModCount;

        ListIterator(Node<E> node, int expectedModCount) {
            this.node = node;
            this.expectedModCount = expectedModCount;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            if (modCount != this.expectedModCount)
                throw new ConcurrentModificationException("非法操作");

            E item = node.item;
            node = node.next;
            return item;
        }
    }

    public static void main(String[] args) {
        int length = 12;
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < length; i++)
            list.insert("string" + i);

        list.insert(0, "asdasd");

        List<String> stringList = new LinkedList<>();

        for (int i = 0; i < length; i++)
            stringList.insert("string100" + i);

        list.addAll(10, stringList);

        list.removeRange(list.size() - 2, list.size() - 1);

        System.out.println("size = " + list.size());

        String[] strings = new String[10];
        strings = list.toArray(strings);

        for (String string : strings) System.out.println(string);

        LinkedList<Integer> link = new LinkedList<>();
        Random random = new Random();
        length = 15000000;

        for (int i = 0; i < length; i++)
            link.insert(random.nextInt(length * 4));

//        for (int num : link)
//            System.out.println(num);

        System.out.println("排序==========");

        long start = System.currentTimeMillis();
        link.sort();
        long end = System.currentTimeMillis();

        System.out.println("time-> " + (end - start));

//        for (int num : link)
//            System.out.println(num);

//
//        System.out.println("反转后=================");
//
//        list.reverse();
//
//        for (int i = 0; i < length; i++)
//            System.out.println(list.find(i));
    }
}
