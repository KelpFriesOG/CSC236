package chapter2;

import java.util.Scanner;

public class BalancedTest {
    public static void main(String[] args) {

        Balanced bal = new Balanced("({[", ")}]");

        final String STOP = "X";
        String expression = null;
        Scanner input = new Scanner(System.in);

        while (!STOP.equals(expression)) {
            System.out.println("Type expression (ENTER '" + STOP + "' to stop):");
            expression = input.nextLine();
            if (!STOP.equals(expression)) {
                int result = bal.test(expression);
                if (result == 1) {
                    System.out.println("Unbalanced! \n");
                } else if (result == 2) {
                    System.out.println("Premature end of expression! \n");
                } else {
                    System.out.println("Balanced! \n");
                }
            }
        }

        input.close();

    }
}
