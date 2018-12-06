package algorithms.basic.stack;

import java.util.Iterator;

/**
 * 基于数组的泛型定容栈的实现，实现了Iterable所以可以用for each遍历表
 * 栈是先入后出的，所以每次数据添加是在尾部添加，并且读取数据时也是从尾部开始读取
 *
 * @author 赵磊
 */
public class FixedCapacityStack<Item> implements Iterable<Item> {

    /**
     * 没有指定容量时提供的默认大小
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 容量的大小
     */
    private int capacity;

    /**
     * 栈中数据的多少
     */
    private int size;

    /**
     * 实际保存数据的数组
     */
    private Object[] items;

    /**
     * 无参构造方法，默认大小是10
     */
    public FixedCapacityStack() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * @param capacity 一开始就输入了表的大小
     * @throws IllegalArgumentException 输入负数的大小
     */
    public FixedCapacityStack(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Illegal Capacity " + capacity);
        this.capacity = capacity;
        items = new Object[capacity];
        size = 0;
    }

    /**
     * 对数组进行扩容的函数
     *
     * @param max 数组新的容量大小
     */
    private void resize(int max) {
        Object[] newItem = new Object[max];
        System.arraycopy(items, 0, newItem, 0, size);
        capacity = max;
        items = newItem;
    }

    /**
     * 添加一个元素到标中
     *
     * @param item 元素
     * @return boolean 添加成功返回true
     */
    public boolean push(Item item) {
        if (size == capacity)
            resize(2 * capacity + 1);
        items[size++] = item;

        return true;
    }

    /**
     * 弹出一个元素(列表中最后一个元素)
     * 如果弹出后列表中数据数量小于数组容量的四分之一，则数组容量缩小一半
     *
     * @return Item
     */
    public Item pop() {
        if (isEmpty())
            throw new IndexOutOfBoundsException("列表中数据为空");
        Object object = items[--size];
        items[size] = null;
        if (size < capacity / 4)
            resize(capacity / 2);
        return (Item) object;
    }

    /**
     * 当为true时列表为空，否则非空
     *
     * @return boolean
     */
    public boolean isEmpty() {

        return size == 0;
    }

    /**
     * @return int 返回列表中数据的多少
     */
    public int size() {

        return size;
    }

    /**
     * 返回一个Iterator对象，这是为了能够通过foreach来遍历列表
     *
     * @return Iterator
     */
    @Override
    public Iterator iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public Item next() {
            return (Item) items[current++];
        }
    }

    public static void main(String[] args) {
        FixedCapacityStack<String> fixedCapacityStack = new FixedCapacityStack<>();
        for (int i = 0; i < 100; i++)
            fixedCapacityStack.push("String" + i);

        //即使不实现Iterable这个方法也可以用
        //因为它直接调用iterator()方法，并且这个方法返回的Iterator已经是有泛型乐的
        Iterator iterator = fixedCapacityStack.iterator();
        while (iterator.hasNext())
            System.out.println("值 -> " + iterator.next());

        //foreach的话必须实现Iterable才可用且Iterable得添加泛型参数
        for (String number : fixedCapacityStack)
            System.out.println("值 -> " + number);

    }
}
