package chapter4;

public class SyncCounter {

    private int count;

    public SyncCounter() {
        count = 0;
    }

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

}
