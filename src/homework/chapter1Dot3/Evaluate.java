package homework.chapter1Dot3;

import java.util.*;

/**
 * 文件 Evaluate 创建于 2017/7/29
 *
 * @author 赵磊
 * @version 1.0
 */
public final class Evaluate {
    private static Map<String, Integer> priority = new HashMap<>();

    static {
        priority.put("+", 0);
        priority.put("-", 0);
        priority.put("*", 1);
        priority.put("/", 1);
        priority.put("sin", 2);
        priority.put("cos", 2);
        priority.put("^", 3);
    }

    //判断是否是数字
    public static boolean isNumeric(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
//        Pattern pattern = Pattern.compile("[\\d]*$");
//
//        return pattern.matcher(string).matches();
    }

    //实现具体的解析过程
    private static List<String> parse(Queue<String> source) {
        List<String> target = new ArrayList<>();
        Stack<String> operatorStack = new Stack<>();

        while (!source.isEmpty()) {
            String temp = source.poll();

            if (isNumeric(temp)) {
                target.add(temp);
            } else if (temp.equals("(")) {
                target.addAll(parse(source));
            } else if (temp.equals(")")) {
                break;
            } else {
                while (!operatorStack.isEmpty()) {
                    if (priority.get(temp) <= priority.get(operatorStack.peek())) {
                        target.add(operatorStack.pop());
                    } else {
                        break;
                    }
                }
                operatorStack.add(temp);
            }
        }

        while (!operatorStack.isEmpty()) {
            target.add(operatorStack.pop());
        }

        return target;
    }

    //中序表达式转后序表达式
    public static List<String> InfixToPostfix(String[] source) {
        Queue<String> sourceQue = new ArrayDeque<>(source.length);
        sourceQue.addAll(Arrays.asList(source));

        return parse(sourceQue);
    }

    //求出后序表达式的值
    public static double EvaluatePostfix(List<String> string) {
        Stack<Double> valueStack = new Stack<>();

        for (int i = 0; i < string.size(); i++) {
            String item = string.get(i);
            if (isNumeric(item)) {
                valueStack.add(Double.parseDouble(item));
            } else {
                double next;
                double prev;
                switch (item) {
                    case "+":
                        valueStack.add(valueStack.pop() + valueStack.pop());
                        break;
                    case "-":
                        next = valueStack.pop();
                        prev = valueStack.pop();
                        valueStack.add(prev - next);
                        break;
                    case "*":
                        valueStack.add(valueStack.pop() * valueStack.pop());
                        break;
                    case "/":
                        next = valueStack.pop();
                        prev = valueStack.pop();
                        valueStack.add(prev / next);
                        break;
                    case "sin":
                        valueStack.add(Math.sin(valueStack.pop()));
                        break;
                    case "cos":
                        valueStack.add(Math.cos(valueStack.pop()));
                        break;
                    case "^":
                        next = valueStack.pop();
                        prev = valueStack.pop();
                        valueStack.add(Math.pow(prev, next));
                        break;

                }
            }
        }


        return valueStack.pop();
    }

    public static void main(String[] args) {
        System.out.println(EvaluatePostfix(InfixToPostfix(new String[]{"(", "(", "1.2", "+", "2", ")", "*", "(", "(", "3", "-", "4", ")", "*", "(", "5", "-", "6", ")", ")", ")", "^", "2"})));
        System.out.println(EvaluatePostfix(InfixToPostfix(new String[]{"cos", "(", "sin", "(", "1", "+", "2", ")", ")", "^", "2"})));
        System.out.println(EvaluatePostfix(InfixToPostfix(new String[]{"9", "*", "4", "^", "9", "-", "9", "*", "2", "^", "9", "+", "1"})));
    }
}
