package homework.chapter1Dot3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * 文件 SupplyBracket 创建于 2017/7/29
 * 编写一段程序把缺少的左括号补回来 如输入: 1 + 2 ) * 3 )，则返回( ( 1 + 2 ) * 3 );
 *
 * @author 赵磊
 * @version 1.0
 */
public class SupplyBracket {
    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine().trim();
        Stack<Expression> valueStack = new Stack<>();
        Stack<Expression> operatorStack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ') {
                if (Character.isDigit(c)) {
                    valueStack.add(new Expression(c));
                } else if (c == ')') {
                    Expression lastEx = valueStack.pop();
                    Expression firstEx = valueStack.pop();
                    Expression expression = new Expression();
                    expression.left = firstEx;
                    expression.right = lastEx;
                    expression.operator = operatorStack.pop();
                    valueStack.add(expression);
                } else {
                    operatorStack.add(new Expression(c));
                }
            }
        }

        System.out.println(valueStack.pop());

    }

    private static class Expression {
        private Expression left;
        private Expression right;
        private Expression operator;
        private Character value;

        public Expression() {
        }

        public Expression(Character value) {
            this.value = value;
        }

        @Override
        public String toString() {
            if (value != null) {
                return value.toString();
            } else {
                return "( " + left + " " + operator + " " + right + " )";
            }
        }
    }

}
