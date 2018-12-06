package com.busekylin.algorithms.basicStructure;

import java.util.*;
import java.util.Stack;

/**
 * 文件 MoveToFront 创建于 2017/7/30
 *
 * @author 赵磊
 * @version 1.0
 */
public class MoveToFront {
    public static void main(String[] args) {
        java.util.Stack<String> list = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        String string;
        while (!(string = scanner.nextLine()).equals("quit")) {
            list.remove(string);
            list.add(string);
        }

        while (!list.isEmpty())
            System.out.println(list.pop());
    }
}
