package labs.lab4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LinkedQueueITD {

    public static void main(String[] args) {

        System.out.println("Creating the empty queue...");
        LinkedQueue<Integer> myQ = new LinkedQueue<>();
        Scanner input = new Scanner(System.in);
        String option = "!";
        int value;
        boolean success;

        do {

            System.out.println("A) Add an element");
            System.out.println("B) Remove first n elements.");
            System.out.println("C) Swap first two elements.");
            System.out.println("D) Swap front and rear elements.");
            System.out.println("Note that there is no limit on queue size!");

            System.out.println("Enter your choice, or enter \"X\" to exit!");
            option = input.next();
            option = option.toUpperCase();

            switch (option) {
                case "A":
                    System.out.println("Enter the INTEGER value to enqueue: ");

                    try {
                        value = input.nextInt();
                        myQ.enqueue(value);
                    } catch (InputMismatchException e) {
                        System.out.println("Did not enter a valid int!");
                    } catch (QueueOverflowException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case "B":
                    System.out.println("Enter the NUMBER of VALUES to dequeue: ");

                    try {
                        value = input.nextInt();
                        myQ.remove(value);
                    } catch (InputMismatchException e) {
                        System.out.println("Did not enter a valid int!");
                    } catch (QueueUnderflowException e) {
                        System.out.println("Cannot remove more elements than are currently in the queue!");
                    }

                    break;

                case "C":
                    System.out.println("Processing the operation...");

                    success = myQ.swapStart();

                    if (success) {
                        System.out.println("Sucessfully swap");
                    } else {
                        System.out.println("There were less than two elements, swap was unsucessful!");
                    }

                    break;

                case "D":
                    System.out.println("Processing the operation...");

                    success = myQ.swapEnds();

                    if (success) {
                        System.out.println("Sucessfully swap");
                    } else {
                        System.out.println("There were less than two elements, swap was unsucessful!");
                    }

                    break;

                case "X":
                    continue;

                default:

                    System.out.println("Invalid operation, try again!");

                    break;
            }

            System.out.println("\nChanged queue: ");
            System.out.println(myQ + "\n");

        } while (!option.equals("X"));

        input.close();

    }
}
