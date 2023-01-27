package labs.lab1;

import java.util.Scanner;

public class ShoppingBagITD {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        // Creating the Scanner object

        int count = -1;
        double cost;
        // Declaring and initializing cost and count

        ShoppingBag myBag = new ShoppingBag(0.06);
        // Creating ShoppingBag object

        while (true) {
            // While loop will only break if the entered count is 0.
            
            System.out.println("Enter count (use 0 to stop): ");
            count = input.nextInt();
            // Prompting user for count and assigning it.

            if (count == 0) {
            // If the count value entered is 0, exit the loop
                break;
            }

            System.out.println("Enter cost: ");
            cost = input.nextDouble();
            // Prompting user for cost and assigning it.

            myBag.place(count, cost);
            // Executing the place method based on inputs.
        }

        System.out.println(myBag.toString());
        // Printing out the status (count of object and total cost with/without
        // tax).

        input.close();
        // Closing the input stream.

    }
}
