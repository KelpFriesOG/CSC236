package labs.lab6;

import java.util.ArrayList;
import java.util.Scanner;

import chapter6.ABList;
import chapter6.LBList;
import chapter6.ListInterface;

public class ListCompare {

    public static void main(String[] args) {

        LBList<String> list1 = new LBList<String>();
        ABList<String> list2 = new ABList<String>();
        Scanner input = new Scanner(System.in);

        String selection = "";

        System.out.println("Welcome to the ListCompare simulation! ");

        System.out.println("\nLets first enter values into the Link-Based List: ");

        do {

            System.out.println("Enter a value to put in, enter nothing to exit: ");
            selection = input.nextLine();

            if (selection.isBlank()) {
                continue;
            }

            list1.add(selection);

        } while (!selection.isBlank());

        System.out.println("\n\nNow lets enter values into the Array-Based List: ");

        do {

            System.out.println("Enter a value to put in, enter nothing to exit: ");
            selection = input.nextLine();

            if (selection.isBlank()) {
                continue;
            }

            list2.add(selection);

        } while (!selection.isBlank());

        System.out.println("\n\nNumber of common elements: " + compare(list1, list2));

    }
    

    static int compare(ListInterface<String> list1, ListInterface<String> list2) {

        if (list1.isEmpty() || list2.isEmpty()) {
            return 0;
        }

        int count = 0;
        ArrayList<String> foundElements = new ArrayList<String>();

        for (String str1 : list1) {
            for (String str2 : list2) {
                if (str1.equals(str2) && !foundElements.contains(str1)) {
                    count++;
                    foundElements.add(str1);
                }
            }
        }

        return count;

    }

}
