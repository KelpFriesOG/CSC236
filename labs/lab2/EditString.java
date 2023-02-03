package labs.lab2;

import java.util.Scanner;

import chapter2.ArrayBoundedStack;
import chapter2.StackOverflowException;
import chapter2.StackUnderflowException;

public class EditString {

    public static void main(String[] args) {

        // Initializing the ArrayBoundedStack object with a default capacity.
        ArrayBoundedStack<String> changes = new ArrayBoundedStack<String>(50);

        // Initializing the Scanner object.
        Scanner input = new Scanner(System.in);

        // Creating a String reference that will be initialized to the value on the top
        // of the stack in every iteration of the loop.
        String latest;

        // Using a bunch of printf statements to neatly print out a table that describes
        // all possible things the user can enter to change the string.
        System.out.println("--------------- Operation Table -----------------------------------");
        System.out.printf("| %-15s | %-45s |%n", "Operation", "Meaning");
        System.out.printf("| %-15s | %-45s |%n", "U", "Make all letters UPPERCASE.");
        System.out.printf("| %-15s | %-45s |%n", "L", "Make all letters lowercase.");
        System.out.printf("| %-15s | %-45s |%n", "R", "Reverse the string.");
        System.out.printf("| %-15s | %-45s |%n", "C c1 c2", "Replace all instances of char c1 with c2.");
        System.out.printf("| %-15s | %-45s |%n", "Z", "Undo most recent change.");
        System.out.println("-------------------------------------------------------------------");

        // Prompting the user for the initial string and setting the response equal to a
        // string variable.
        System.out.println("Enter your string: ");
        String message = input.nextLine();

        // Pusing the initial string message onto the stack.

        // Displaying a disclaimer as to what operations can be performed after
        // submitting "X" as an operation. And how to get the final string
        // anytime after submitting "X".
        System.out.println("\nEnter your operations. Enter X to stop the changes.");
        System.out.println("Keep in mind that you may only undo after submitting 'X' ");
        System.out.println("Enter nothing anytime after entering X to get your changed string!\n");

        // Prompting the user to begin entering changes.
        System.out.println("Enter changes: ");

        // The operation variable will contain the input from the user
        // once the loop begins.
        String operation = "_";

        // The initial string will be "locked" once the user enters X,
        // it is also how we will determine when to leave the first
        // loop.
        boolean locked = false;

        // The initial string will first have to be locked, then it will
        // enter another while loop which will end when finalized becomes true.
        // This variable will become true when the user enters a null argument
        // i.e. the user just hits enter without typing in any character.
        boolean finalized = false;

        // Will enter the loop which will dynamically add / remove the latest
        // changed versions of the original message based on the inputted operation.
        while (!locked) {

            // We ask the user for an operation.
            operation = input.nextLine();

            // We set latest to be refer to the original message
            // (We will change this string by applying the
            // new change to it)!
            // If the stack is empty then the latest value is the
            // original message.
            // Else the latest string is on the top of the stack!
            latest = changes.isEmpty() ? message : changes.top();

            // If the operation has no operands then we enter this statement!
            if (operation.length() == 1) {

                switch (operation.charAt(0)) {
                    // We check the first and only character of the overall string
                    // the user enetered to determine the operation

                    case 'U':
                        // If the first letter is U and the Stack is not full
                        // then we take the most recent (latest) string and
                        // apply the toUpperCase() method to it and push
                        // the resulting string onto the stack.

                        // If the StackOverflowException is thrown we provide a
                        // message to the user to show indicate the stack is full,
                        // and telling the user to either finish or undo
                        // the most recent change before trying any other operations.
                        try {
                            changes.push(latest.toUpperCase());
                        } catch (StackOverflowException e) {
                            System.out.println(
                                    "Too many changes to handle! Try undoing a change or submit X and then exit.");
                        }
                        break;

                    case 'L':
                        // If the first letter is L and the Stack is not full
                        // then we take take the most recent (latest) string
                        // and apply the toLowerCase method to it and push
                        // the resulting string onto the stack.

                        // If the StackOverflowException is thrown we provide a
                        // message to the user to show indicate the stack is full,
                        // and telling the user to either finish or undo
                        // the most recent change before trying any other operations.
                        try {
                            changes.push(latest.toLowerCase());
                        } catch (StackOverflowException e) {
                            System.out.println(
                                    "Too many changes to handle! Try undoing a change or submit X and then exit.");
                        }
                        break;

                    case 'R':
                        // If the first letter is R and the Stack is not full
                        // then we take the most recent (latest) string
                        // and first convert it to a StringBuilder object
                        // (which is just a mutable string) and then we
                        // reverse that object via .reverse() and
                        // then return the String representation of the
                        // StringBuilder object via the .toString() method.

                        // If the StackOverflowException is thrown we provide a
                        // message to the user to show indicate the stack is full,
                        // and telling the user to either finish or undo
                        // the most recent change before trying any other operations.
                        try {
                            changes.push(new StringBuilder(latest).reverse().toString());
                        } catch (StackOverflowException e) {
                            System.out.println(
                                    "Too many changes to handle! Try undoing a change or submit X and then exit.");
                        }
                        break;

                    case 'C':
                        // If the user has just typed in C without any operands, then
                        // well they may have just mistyped or misread the C c1 c2
                        // operation. We just give the user a hint regarding their
                        // mistake and continue prompting them.
                        System.out.println("Please use the appropriate syntax after 'C'.");
                        System.out.println("Syntax: C c1 c2 [where c1 and c2 are characters!]");
                        System.out.println("Continue: \n");
                        break;

                    case 'Z':
                        // If the first letter is Z and the stack is not empty, (i.e. some version
                        // of the original message exists on the stack), then we allow the user to
                        // undo their change by popping the most top element off the stack.
                        // This effectively means that the most recent change is undone and we are
                        // left with the version of the message prior to the change being on the top
                        // of the stack.

                        // Otherwise if the stack is empty, and the StackUnderflowException is thrown
                        // We tell the user to first try another operation and then undo, or just
                        // submit their changes.
                        try {
                            changes.pop();
                        } catch (StackUnderflowException e) {
                            System.out.println(
                                    "Cannot undo, try another operation or finish by submitting X and then no operation.");
                        }
                        break;

                    case 'X':
                        locked = true;
                        break;

                    default:
                        System.out.println("Entered an invalid operation, try again!\n");
                        break;

                }
            }

            // If the operation was not just a single letter, then
            // we check if it was in the format of a C c1 c2 operation.
            // I use some fancy regex here but you can think of it as a template!
            // If the pattern of what the user entered looks like:
            // "C [some character] [some other character]"
            // then enter this else if.
            else if (operation.matches("^C [ -~] [ -~]")) {
                // If the pattern matches and the Stack is not full
                // then we take the most recent (latest) string and interpret
                // the operation. c1 is at the 2nd index and
                // c2 is the 4th index (if you start at 0
                // and count the spaces as character).
                // We use this info to perform the replace operation
                // on the latest string and push the result onto
                // the stack!

                // If the StackOverflowException is thrown we provide a
                // message to the user to show indicate the stack is full,
                // and telling the user to either finish or undo
                // the most recent change before trying any other operations.

                try {
                    changes.push(latest.replace(operation.charAt(2), operation.charAt(4)));
                } catch (StackOverflowException e) {
                    System.out.println("");
                }
            }

            // If the input was not a single letter and was not a C
            // operation then we know its invalid so we let the user know
            // and prompt the user to continue.
            else {
                System.out.println("Invalid operation: please examine the Operation table for valid operations.");
                System.out.println("Continue: ");
            }

        }

        // After the previous loop finishes, we know the
        // stack is "locked". Therefore we can only pop off
        // the stack via an undo command, or we can print
        // out the final result!
        while (!finalized) {

            // Reading user input
            operation = input.nextLine();

            // If the stack is empty after the previous loop, or after
            // any number of undos from this loop, then there is nothing left
            // to undo, so we will automatically exit the loop!
            if (changes.isEmpty()) {
                System.out.println("You have no changes to possibly undo!");
                System.out.println("Automatically finalizing...");
                // Setting finalized to true, so when checking it for the
                // next iteration we will not enter the loop again!
                finalized = true;
            }

            // The other way we can exit the loop is if the user enters in nothing!
            else if (operation.equals("")) {
                // Setting finalized to true, so when checking it for the
                // next iteration we will not enter the loop again!
                finalized = true;
            }

            // If the user submits "Z" then we interpret this as an undo operation.
            else if (operation.equals("Z")) {
                changes.pop();
            }

            // If the user enters in gibberish then well... We let them know
            // and remind them of their options.
            else {
                System.out.println("Invalid operation!");
                System.out.println("Try again: remember you can only undo or enter nothing at this point!");
            }
        }

        // Don't forget to close your input :)
        input.close();

        // If the stack is empty after all of this BS, then we didn't really
        // change anything so our result is our original message.
        // Otherwise, our result is at the top of the Stack!
        String result = !changes.isEmpty() ? changes.top() : message;

        System.out.println(result);

    }

}
