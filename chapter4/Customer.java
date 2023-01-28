package chapter4;

public class Customer {

    protected int arrivalTime;
    protected int serviceTime;
    protected int finishTime;

    public Customer(int arrivalTime, int finishTime) {
        this.arrivalTime = arrivalTime;
        this.finishTime = finishTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int time) {
        finishTime = time;
    }

    public int getWaitTime() {
        return (finishTime - arrivalTime - serviceTime);
    }

}
