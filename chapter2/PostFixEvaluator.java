package chapter2;

import java.util.ArrayList;
import java.util.Scanner;

public class PostFixEvaluator {

    public static int evaluate(String expression) {

        StackInterface<Integer> stack = new ArrayListStack<Integer>();

        int value, operand1, operand2;
        String operator;
        ArrayList<String> possibleOperators = new ArrayList<>();
        possibleOperators.add("+");
        possibleOperators.add("-");
        possibleOperators.add("*");
        possibleOperators.add("/");
        int result = 0;

        try (Scanner token = new Scanner(expression)) {
            while (token.hasNext()) {
                if (token.hasNextInt()) {
                    value = token.nextInt();
                    stack.push(value);
                } else {
                    operator = token.next();

                    if (!possibleOperators.contains(operator)) {
                        throw new Exception("Illegal symbol: " + operator);
                    }

                    if (stack.isEmpty()) {
                        throw new Exception("Not enough operands, stack underflow!");
                    }
                    operand2 = stack.top();
                    stack.pop();

                    if (stack.isEmpty()) {
                        throw new Exception("Not enough operands, stack underflow!");
                    }
                    operand1 = stack.top();
                    stack.pop();

                    if (operator.equals("/")) {
                        result = operand1 / operand2;
                    } else if (operator.equals("*")) {
                        result = operand1 * operand2;
                    } else if (operator.equals("+")) {
                        result = operand1 + operand2;
                    } else {
                        result = operand1 - operand2;
                    }

                    stack.push(result);
                }
            }

            if (stack.isEmpty()) {
                throw new Exception("Not enough operands, stack underflow!");
            }
            result = stack.top();
            stack.pop();

            if (!stack.isEmpty()) {
                throw new Exception("Too many operands left over!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

        return result;

    }

}