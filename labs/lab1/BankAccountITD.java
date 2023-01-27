package labs.lab1;

import java.util.Scanner;

public class BankAccountITD {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        // Initializing Scanner object

        System.out.println("*************************************");
        System.out.println("******* WELCOME TO KELP'S BANK ******");
        System.out.println("*************************************");
        // Printing out header for bank

        int selection = 0;
        // Initializing the selection (which will have values from 1-4)
        // when the loop begins!

        System.out.println("Enter initial funds: ");
        double funds = input.nextDouble();
        // Getting the initial funds from the user

        BankAccount myAccount = new BankAccount(funds);
        // Creating the BankAccount class with the funds

        do {
            // This loop will execute at least once. Will print out a menu
            // and the value of selection will be used in a switch statement
            // to print the desirable prompts and output.

            System.out.println("1 - Check account balance");
            System.out.println("2 - Make withdrawal");
            System.out.println("3 - Make deposit");
            System.out.println("4 - Exit");
            // Printing out the menu.

            System.out.println("Selection: ");
            selection = input.nextInt();
            // Prompting for and assigning user selection.

            double amount;
            // Initializing an amount variable in case the
            // user has chosen to withdraw or deposit money.

            switch (selection) {
                case 2:
                    System.out.println("Enter amount: ");
                    amount = input.nextDouble();
                    try {
                        myAccount.withdraw(amount);
                    } catch (BankAccountException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                // If the user has chosen to withdraw,
                // we prompt for the ammount and execute
                // the withdraw method if given a valid input.
                case 3:
                    System.out.println("Enter amount: ");
                    amount = input.nextDouble();
                    try {
                        myAccount.deposit(amount);
                    } catch (BankAccountException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                // If the user has chosen to deposit,
                // we prompt for the ammount and execute
                // the withdraw method if given a valid input
                case 4:
                    continue;
                // If the user has chosen to exit, we continue the loop for
                // another iteration. Since selection = 4, we will exit the loop.
                default:
                    break;
                // If the selection was neither of the cases above I assume
                // the user typed in 1. In which case we simply need
                // to print the current status which comes after the switch.
            }

            String result = "Current account balance is $" +
                    myAccount.getBalance();

            System.out.println(result + "\n");
            // We display the result if the case was 1,2, or 3.
            // Otherwise we exit without showing this message.

        } while (selection != 4);

        System.out.println("\n**************************************");
        System.out.println("******** Thank you for using *********");
        System.out.println("************* KELP'S BANK ************");
        System.out.println("**************************************");
        // Bank footer to print after exiting the loop.

        input.close();

    }

}
