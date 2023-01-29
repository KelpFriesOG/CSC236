package chapter4;

import java.util.Scanner;

public class SimulationITD {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int minIAT;
        int maxIAT;
        int minST;
        int maxST;
        int nCustomers;
        int nQueues;
        Simulation sim;

        boolean again;

        do {

            System.out.println("Enter minimum interarrival time: ");
            minIAT = input.nextInt();
            System.out.println("Enter maximum interarrival time: ");
            maxIAT = input.nextInt();
            System.out.println("Enter minimum service time: ");
            minST = input.nextInt();
            System.out.println("Enter maximum service time: ");
            maxST = input.nextInt();

            System.out.println("\nEnter number of queues: ");
            nQueues = input.nextInt();

            System.out.println("Enter number of customers: ");
            nCustomers = input.nextInt();

            sim = new Simulation(minIAT, maxIAT, minST, maxST);

            sim.simulate(nQueues, nCustomers);

            System.out.println("The average waiting time is: "
                    + sim.getAvgWaitTime());

            System.out.println("\nEvaluate another simulation? (y / n): ");
            String resp = input.next();
            again = resp.equals("y") ? true : false;

        } while (again);

        System.out.println("Program finished.");
        input.close();

    }
}
