package chapter2;

public class Balanced {
    protected String openSet;
    protected String closeSet;

    public Balanced(String openSet, String closeSet) {
        /*
         * Preconditions:
         * - No character is contained more than once
         * in the combined values of the two sets.
         * - openSet.length() == closeSet.length()
         * 
         */
        this.openSet = openSet;
        this.closeSet = closeSet;
    }

    public int test(String expression) {

        boolean isBalanced = true;
        ArrayListStack<Integer> stack = new ArrayListStack<>();
        char current;
        int openIndex;
        int closeIndex;

        int i = 0;

        while (isBalanced && i < expression.length()) {

            current = expression.charAt(i);

            boolean isOpenChar = (openSet.indexOf(current) != -1);
            boolean isCloseChar = (closeSet.indexOf(current) != -1);

            if (isOpenChar) {

                openIndex = openSet.indexOf(current);
                stack.push(openIndex);

            } else if (isCloseChar) {

                closeIndex = closeSet.indexOf(current);

                try {
                    openIndex = stack.top();
                    stack.pop();

                    if (openIndex != closeIndex) {
                        isBalanced = false;
                    }

                } catch (StackUnderflowException e) {
                    isBalanced = false;
                }

            }

            i++;
        }

        if (!isBalanced) {
            return 1;
        } else if (!stack.isEmpty()) {
            return 2;
        } else {
            return 0;
        }

    }

}