package com.busekylin.algorithms.basicStructure;

/**
 * 文件 GenerabilityStack 创建于 2017/7/31
 * 栈的可生成性
 *
 * @author 赵磊
 * @version 1.0
 */
public class GenerabilityStack {
    public static boolean testGenerable(int[] input, int[] output) {
        ArrayStack<Integer> inputStack = new ArrayStack<>();
        ArrayStack<Integer> outputStack = new ArrayStack<>();
        int index = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == output[index]) {
                index++;
            } else {
                if (inputStack.isEmpty() || inputStack.peek() != output[index]) {
                    inputStack.push(input[i]);
                } else {
                    while (!inputStack.isEmpty() && inputStack.peek() == output[index]) {
                        index++;
                        inputStack.Pop();
                        outputStack.push(-1);
                    }
                    inputStack.push(input[i]);
                }
            }

            outputStack.push(input[i]);
        }

        while (!inputStack.isEmpty() && inputStack.peek() == output[index]) {
            index++;
            outputStack.push(-1);
            inputStack.Pop();
        }

        for (int number : outputStack)
            System.out.print(number + " ");

        System.out.println();

        if (index == output.length)
            return true;

        return false;
    }


    public static void main(String[] args) {
        int[] input = new int[10];
        int[] output = {2, 5, 6, 7, 4, 8, 9, 3, 1, 0};

        for (int i = 0; i < 10; i++)
            input[i] = i;
        System.out.println(testGenerable(input, output));

        int[] a = {2, 3, 4, 5, 6, 8, 9};
        int[] b = {4, 6, 8, 9};

        for (int i = 0, j = 0; i < a.length && j < b.length; ) {
            if (a[i] > b[j]) {
                j++;
            } else if (a[i] < b[j]) {
                i++;
            } else {
                System.out.println(a[i]);
                i++;
                j++;
            }
        }
    }
}
