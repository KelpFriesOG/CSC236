package chapter5;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CSInfo {

    public static void main(String[] args) {
        // Instantiating the collection
        final int CAPACITY = 300;
        ArrayCollection<FamousPerson> people = new ArrayCollection<FamousPerson>(CAPACITY);
        Scanner input = new Scanner(System.in);

        // Variables that will be used to cycle through
        // each famous person in the text file
        FamousPerson person;
        String fname, lname, fact;
        int year;

        try {
            // Setting up file reading
            FileReader reader = new FileReader("chapter5/FamousCS.txt");
            Scanner fileInput = new Scanner(reader);
            fileInput.useDelimiter("[,\\n]");

            while (fileInput.hasNext()) {
                fname = fileInput.next();
                lname = fileInput.next();
                year = fileInput.nextInt();
                fact = fileInput.next();
                person = new FamousPerson(fname, lname, year, fact);
                people.add(person);
            }

            fileInput.close();

        } catch (IOException e) {
            System.out.println("File not found or is inaccesible!");
        }

        final String STOP = "X";
        System.out.println("Enter names of computer scientists.");
        System.out.println("Enter: firstname lastname (" + STOP + " to exit)\n");
        fname = "";
        lname = "";

        while (!fname.equals(STOP)) {
            System.out.print("Enter Name: ");
            String[] fullName = input.nextLine().split(" ");
            fname = fullName[0];
            lname = fullName[1];

            if (!fname.equals(STOP)) {
                person = new FamousPerson(fname, lname, 0, "");
                if (people.contains(person)) {
                    person = people.get(person);
                    System.out.println(person + "\n");
                } else {
                    System.out.println("No information available.\n");
                }
            }

        }

        input.close();

    }

}
