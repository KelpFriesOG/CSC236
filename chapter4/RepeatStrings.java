package chapter4;

import java.util.Scanner;

public class RepeatStrings {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        QueueInterface<String> stringQueue = new ArrayBoundedQueue<String>();
        String line;

        try {

            for (int i = 1; i <= 3; i++) {
                System.out.println("Enter a line of text >> ");
                line = input.nextLine();
                stringQueue.enqueue(line);
            }

            System.out.println("\nOrder is:\n");
            while (!stringQueue.isEmpty()) {
                line = stringQueue.dequeue();
                System.out.println(line);
            }

        } catch (Exception e) {

        }

        input.close();

    }
}
