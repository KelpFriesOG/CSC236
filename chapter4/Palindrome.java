package chapter4;

import java.util.Scanner;

import chapter2.ArrayBoundedStack;
import chapter2.StackInterface;

public class Palindrome {

    public static boolean test(String message) {

        int length = message.length();
        char fromStack;
        char fromQueue;
        boolean isPalindrome = true;

        StackInterface<Character> stack = new ArrayBoundedStack<Character>(length);
        QueueInterface<Character> queue = new ArrayBoundedQueue<Character>(length);

        try {
            for (char ch : message.toCharArray()) {
                if (Character.isLetter(ch)) {
                    ch = Character.toLowerCase(ch);
                    stack.push(ch);
                    queue.enqueue(ch);
                }
            }

            while (isPalindrome && !queue.isEmpty()) {
                fromStack = stack.top();
                stack.pop();
                fromQueue = queue.dequeue();
                if (fromQueue != fromStack) {
                    isPalindrome = false;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return isPalindrome;

    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String message;

        do {
            System.out.println("Enter a message (enter 'X' to exit): ");
            message = input.nextLine();

            if (message == "X") {
                continue;
            }

            if (test(message)) {
                System.out.println("\nYour message is a palindrome!\n");
            } else {
                System.out.println("\nYour message is not a palindrome :(\n");
            }

        } while (message != "X");

        input.close();
    }
}
