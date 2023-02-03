package labs.lab2;

import java.util.Scanner;

public class ShapesCLI {

        public static void main(String[] args) {

                // Initializing the Scanner object.
                Scanner input = new Scanner(System.in);

                // Variables that will get used and reused to calculate
                // the areas and perimeters of the various shapes.
                double side;
                double base;
                double height;
                double angleDeg;

                // total will be set to the sum of all the areas of
                // the created objects.
                double total;

                System.out.println("Lets make some shapes!");
                System.out.println("--------------------------\n");

                // Getting the base and the height from user input to create a right triangle.
                System.out.println("Enter the base (leg 1) for a right triangle: ");
                base = input.nextDouble();
                System.out.println("Enter the height (leg 2) for the same right triangle: ");
                height = input.nextDouble();

                // Creating the RightTriangle object based on user specifications.
                RightTriangle shape1 = new RightTriangle(base, height);

                // Getting the side length from the user to create a square.
                System.out.println("Enter the side length for the square: ");
                side = input.nextDouble();

                // Creating the Square object based on user specifications.
                Square shape2 = new Square(side);

                // Getting the base and height from the user to create an isosceles triangle.
                System.out.println("Enter the base length for an isosceles triangle"
                                + "(the side that is not the same length as the other two): ");
                base = input.nextDouble();

                System.out.println("Enter the height of the isosceles triangle"
                                + "(not a side length, but distance from center of base to peak): ");
                height = input.nextDouble();

                // Creating the IsoscelesTriangle object based on user specifications.
                IsoscelesTriangle shape3 = new IsoscelesTriangle(base, height);

                // Getting the base, height, and angle from the user to create a parallelogram.
                System.out.println("Enter the base length for a parallelogram: ");
                base = input.nextDouble();

                System.out.println("Enter the height of the parallelogram "
                                + "(not a side length, but the vertical distance between the two base lengths): ");
                height = input.nextDouble();

                System.out.println("Enter the angle (in degrees) between the base and the adjacent slanted side: ");
                angleDeg = input.nextDouble();

                // Creating the Parallelogram object based on user specifications.
                Parallelogram shape4 = new Parallelogram(base, height, angleDeg);

                // newline
                System.out.println("");

                // A bunch of printf statements to create a neat formatted table
                // for the perimeter and area of each shape.
                System.out.printf("| %-20s | %-8s | %10s |%n", "SHAPE", "PERIMETER", "AREA");
                System.out.println("_________________________________________________");
                System.out.printf("| %-20s | %9.4f | %10.4f |%n", "Right Triangle", shape1.perimeter(), shape1.area());
                System.out.printf("| %-20s | %9.4f | %10.4f |%n", "Square", shape2.perimeter(), shape2.area());
                System.out.printf("| %-20s | %9.4f | %10.4f |%n", "Isosceles Triangle", shape3.perimeter(),
                                shape3.area());
                System.out.printf("| %-20s | %9.4f | %10.4f |%n", "Parallelogram", shape4.perimeter(), shape4.area());
                System.out.println("_________________________________________________");

                // Setting the total to be equal to the sum of the areas of all shapes.
                total = shape1.area() + shape2.area() + shape3.area() + shape4.area();

                // Printing out the total area!
                System.out.println("\nTotal Area: " + total);

                // Close off the input to be safe :)
                input.close();

        }

}
