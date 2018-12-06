package algorithms.basic.stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by BUSE on 2016/11/27.
 */
public class Evaluate {

    public static void main(String[] args) {
        Queue<String> expression = new ArrayDeque<>();

        while (!StdIn.isEmpty()) {
            String character = StdIn.readString();
            if (!character.equals("="))
                expression.add(character);
            else
                break;
        }

        StdOut.print("表达式: ");
        for (String c : expression) {
            StdOut.print(c + " ");
        }
        StdOut.print("= ");

        StdOut.println(evaluateBracket(expression));
    }

    private static double evaluateExp(Queue<String> expression) {
        Stack<Double> vals = new Stack<>();
        while (expression.size() > 0) {
            String c = expression.poll();
            switch (c) {
                case "+":
                    break;
                case "-":
                    vals.push(-Double.parseDouble(expression.poll()));
                    break;
                case "*":
                    vals.push(vals.pop() * Double.parseDouble(expression.poll()));
                    break;
                case "/":
                    vals.push(vals.pop() / Double.parseDouble(expression.poll()));
                    break;
                default:
                    vals.push(Double.parseDouble(c));
                    break;
            }
        }

        while (vals.size() > 1) {
            vals.push(vals.pop() + vals.pop());
        }

        return vals.pop();
    }

    public static double evaluateBracket(Queue<String> expression) {
        Queue<String> epr = new ArrayDeque<>();
        while (expression.size() > 0) {
            String c = expression.poll();
            switch (c) {
                case "(":
                    epr.add(evaluateBracket(expression) + "");
                    break;
                case ")":
                    return evaluateExp(epr);
                default:
                    epr.add(c);
                    break;
            }
        }

        return evaluateExp(epr);
    }
}
