package labs.lab5;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SortedCollectionITD {
    public static void main(String[] args) {

        System.out.println("Creating the empty collection...");
        SortedArrayCollection<Integer> myColl = new SortedArrayCollection<Integer>();
        Scanner input = new Scanner(System.in);
        String option = "!";
        int value;
        boolean success;

        do {

            System.out.println("A) Add an element.");
            System.out.println("B) Remove an element.");
            System.out.println("C) Get the smallest element.");
            System.out.println("D) Get the number of elements greater"
                    + " than a given element.");
            System.out.println("E) Combine with another collection.");

            System.out.println("Enter your choice, or enter \"X\" to exit!");
            option = input.next();
            option = option.toUpperCase();

            switch (option) {
                case "A":
                    System.out.println("Enter the INTEGER value to add: ");

                    try {
                        value = input.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Did not enter a valid int!");
                        break;
                    }

                    success = myColl.add(value);

                    if (success) {
                        System.out.println("Successfully added element");
                    } else {
                        System.out.println("Element was not added, queue is full!");
                    }

                    break;

                case "B":
                    System.out.println("Enter the INTEGER value to remove: ");

                    try {
                        value = input.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Did not enter a valid int!");
                        break;
                    }

                    success = myColl.remove(value);

                    if (success) {
                        System.out.println("Successfully removed element");
                    } else {
                        System.out.println("Element was not found in the collection!");
                    }

                    break;

                case "C":
                    Integer result = myColl.smallest();

                    if (result == null) {
                        System.out.println("Collection was empty!");
                    } else {
                        System.out.println("The smallest element: " + result);
                    }

                    break;

                case "D":
                    System.out.println("Enter the element to compare against: ");

                    try {
                        value = input.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Did not enter a valid int!");
                        break;
                    }

                    System.out.println("There are " + myColl.greater(value) +
                            " elements greater than " + value + ".");

                    break;

                case "E":

                    System.out.println("Lets make the second collection first...");

                    System.out.println("What is the max capacity of your collection?: ");

                    try {
                        value = input.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Did not enter a valid int!");
                        break;
                    }

                    SortedArrayCollection<Integer> other = new SortedArrayCollection<>(value);

                    System.out.println("Lets enter in some values. Enter \"X\" to exit!");
                    System.out.println("(loop auto stops if you reach capacity)");

                    int capacity = value;
                    int counter = 0;
                    String element = "";

                    do {

                        System.out.println("Enter an integer: ");
                        element = input.next();

                        if (element.equals("X")) {
                            break;
                        }

                        try {
                            value = Integer.parseInt(element);
                            other.add(value);
                        } catch (NumberFormatException e) {
                            System.out.println("You did not enter a valid int! Try again.");
                        }

                        counter++;

                    } while (counter < capacity);

                    myColl = myColl.combine(other);

                    break;

                case "X":
                    continue;

                default:

                    System.out.println("Invalid operation, try again!");

                    break;
            }

            System.out.println("\nChanged collection: ");
            System.out.println(myColl + "\n");

        } while (!option.equals("X"));

        input.close();
    }

}
