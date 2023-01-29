package chapter4;

public class IncreaseSync implements Runnable {

    SyncCounter c;
    int amount;

    public IncreaseSync(SyncCounter c, int amount) {
        this.c = c;
        this.amount = amount;
    }

    public void run() {
        for (int i = 1; i <= amount; i++) {
            c.increment();
        }
    }

}
