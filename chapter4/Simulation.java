package chapter4;

public class Simulation {

    final int MAXTIME = Integer.MAX_VALUE;

    CustomerGenerator custGen;
    float avgWaitTime = 0.0f;

    public Simulation(int minIAT, int maxIAT, int minST, int maxST) {
        custGen = new CustomerGenerator(minIAT, maxIAT, minST, maxST);
    }

    public float getAvgWaitTime() {
        return avgWaitTime;
    }

    @SuppressWarnings("unchecked")
    public void simulate(int numQueues, int numCustomers) {

        LinkedGlassQueue<Customer>[] queues = new LinkedGlassQueue[numQueues];

        Customer cust;
        int totalWaitTime = 0;
        int custInCount = 0;
        int custOutCount = 0;

        int nextArrTime;
        int nextDepTime;
        int nextQueue; // Index of queue for the next departure.

        // Initializing all the queues in the array.
        for (int i = 0; i < numQueues; i++) {
            queues[i] = new LinkedGlassQueue<Customer>();
        }

        // Resetting customer generator and getting the next customer!
        custGen.reset();
        Customer nextCust = custGen.nextCustomer();

        while (custOutCount < numCustomers) {
            // While there are still customers to be processed...

            // Get the next arrival time
            if (custInCount != numCustomers) {
                // If not all the customers have gotten into some queue...
                // The next arrival time is the next customer's arrival time
                nextArrTime = nextCust.getArrivalTime();
            } else {
                // Else no more customers will arrive and we indicate this
                // by setting the arrival time to the cap!
                nextArrTime = MAXTIME;
            }

            nextDepTime = MAXTIME;
            nextQueue = -1;
            for (int i = 0; i < numQueues; i++) {

                if (queues[i].size() != 0) {
                    cust = queues[i].peekFront();

                    if (cust.getFinishTime() < nextDepTime) {
                        nextDepTime = cust.getFinishTime();
                        nextQueue = i;
                    }
                }
            }

            int shortest;
            int shortestSize;
            Customer rearCust;
            int finishTime;

            if (nextArrTime < nextDepTime) {
                shortest = 0;
                shortestSize = queues[0].size();

                for (int i = 1; i < numQueues; i++) {
                    if (queues[i].size() < shortestSize) {
                        shortest = i;
                        shortestSize = queues[i].size();
                    }
                }

                if (shortestSize == 0) {
                    finishTime = nextCust.getArrivalTime() + nextCust.getServiceTime();
                } else {
                    rearCust = queues[shortest].peekRear();
                    finishTime = rearCust.getFinishTime() + nextCust.getServiceTime();
                }

                nextCust.setFinishTime(finishTime);
          
                queues[shortest].enqueue(nextCust);          
                custInCount += 1;

                if (custInCount < numCustomers) {
                    nextCust = custGen.nextCustomer();
                }
            } else {

                cust = queues[nextQueue].peekFront();

                try {
                    cust = queues[nextQueue].dequeue();
                } catch (QueueUnderflowException e) {
                    e.printStackTrace();
                }

                totalWaitTime = totalWaitTime + cust.getWaitTime();
                custOutCount += 1;
            }
        }

        avgWaitTime = totalWaitTime / (float) numCustomers;

    }

}
