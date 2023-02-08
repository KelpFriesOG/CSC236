package labs.lab3;

public class DiminishingSquares {

    public static void main(String[] args) {

        // Initializing variable n!
        int n = -1;

        try {
            n = Integer.valueOf(args[0]);
            // Try to parse the commandline argument.
        } catch (NumberFormatException e) {
            System.out.println("Command line argument was not parseable as an int!");
        }

        drawSquare(n);
        // Call the static method that will draw the square!

    }

    static void drawSquare(int n) {

        if (n == 0) { // Base case (do nothing)

        } else { // Recursive case (print n * n square, call drawSquare(n-1))
            for (int i = 0; i < n * n; i++) {
                System.out.print("*");
                if ((i + 1) % n == 0) {
                    System.out.println();
                }
            }

            drawSquare(n - 1);
        }

    }

}
