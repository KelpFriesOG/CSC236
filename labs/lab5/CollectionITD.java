package labs.lab5;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CollectionITD {

    public static void main(String[] args) {

        System.out.println("Creating the empty collection...");
        ArrayCollection<Integer> myColl = new ArrayCollection<Integer>();
        Scanner input = new Scanner(System.in);
        String option = "!";
        int value;
        boolean success;

        do {

            System.out.println("A) Add an element.");
            System.out.println("B) Remove an element.");
            System.out.println("C) Count the occurences of an element.");
            System.out.println("D) Remove all instances of an element.");
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
                    System.out.println("Enter the element to look for: ");

                    try {
                        value = input.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Did not enter a valid int!");
                        break;
                    }

                    System.out.println("There are " + myColl.count(value) +
                            " instances of the element in the collection!");

                    break;

                case "D":
                    System.out.println("Enter the element to delete (all instances of): ");

                    try {
                        value = input.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Did not enter a valid int!");
                        break;
                    }

                    myColl.removeAll(value);

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

                    ArrayCollection<Integer> other = new ArrayCollection<>(value);

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
