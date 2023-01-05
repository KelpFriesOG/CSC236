public class TowersOfHanoi {

    public static void doTowers(int n) {
        doTowers(n, 1, 2, 3);
    }

    private static void doTowers(int n, int startPeg, int auxPeg, int endPeg) {

        if (n == 1) {
            // Base case, we simply move one ring
            System.out.println("Move ring " + n + " from peg " + startPeg + " to peg " + endPeg);
        } else {
            // Recursive case

            // Move n-1 rings from starting peg to aux peg
            doTowers(n - 1, startPeg, endPeg, auxPeg);

            // Move the nth ring from starting peg to ending peg
            System.out.println("Move ring " + n + " from peg " + startPeg + " to peg " + endPeg);

            // Move n-1 rings from the auxillary peg to ending peg.
            doTowers(n - 1, auxPeg, startPeg, endPeg);

        }

    }

    public static void main(String[] args) {
        doTowers(4);
    }

}
