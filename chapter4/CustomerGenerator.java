package chapter4;

import java.util.Random;

public class CustomerGenerator {

    protected int minIAT; // Minimum interarrival time
    protected int minST; // Minimum service time
    protected int maxIAT; // Maximum interarrival time
    protected int maxST; // Maximum service time

    protected int currTime = 0;
    // current time

    Random rand = new Random();

    public CustomerGenerator(int minIAT, int maxIAT, int minST, int maxST) {
        this.minIAT = minIAT;
        this.minST = minST;
        this.maxIAT = maxIAT;
        this.maxST = maxST;
    }

    public void reset() {
        currTime = 0;
    }

    public Customer nextCustomer() {
        // Creates and return the next random customer.

        int IAT = minIAT + rand.nextInt(maxIAT - minIAT + 1);
        // Initializing the next random IAT

        int ST = minST + rand.nextInt(maxST - minST + 1);
        // Initializing the next random ST

        currTime += IAT;
        // updates current time to the arrival time of the next customer.

        Customer ret = new Customer(currTime, ST);
        return ret;

    }

}
