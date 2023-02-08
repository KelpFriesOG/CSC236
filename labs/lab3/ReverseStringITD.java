package labs.lab3;

import java.util.Scanner;

public class ReverseStringITD {

    public static void main(String[] args) {

        // Initializing scanner variable.
        Scanner input = new Scanner(System.in);

        // Prompting user for a string.
        System.out.println("Input a string: ");
        String str = input.nextLine();

        // Printing out the reverse of the string by calling
        // the static method reverse(String str)
        System.out.println("The reverse of your string is: ");
        System.out.print(reverse(str));

        input.close();

    }

    static String reverse(String str) {

        // Just literally following the prompt by the way...
        // Hopefully this needs no further explanation.

        if (str.equals("")) { // BASE CASE
            return "";
        } else { // RECURSIVE CASE
            return str.charAt(str.length() - 1) +
                    reverse(str.substring(0, str.length() - 1));
        }

    }

}
