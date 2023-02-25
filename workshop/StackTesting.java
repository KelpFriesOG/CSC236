package workshop;

import chapter2.StackOverflowException;

public class StackTesting {

    public static void main(String[] args) {

        ArrayBoundedStack<Integer> myStack = new ArrayBoundedStack<>(50);
        try {
            myStack.push(9);
            myStack.push(15);
            myStack.push(20);
            System.out.println(myStack.toString());
        } catch (StackOverflowException e) {
            e.printStackTrace();
        }

    }

}
