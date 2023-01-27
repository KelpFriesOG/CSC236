package chapter2;

public class PFixTesting {
    public static void main(String[] args) {
        String expression = "4 2 3 5 1 - + * + *";
        int result = PostFixEvaluator.evaluate(expression);
        System.out.println(result);
    }
}
