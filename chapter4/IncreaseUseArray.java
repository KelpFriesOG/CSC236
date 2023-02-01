package chapter4;

public class IncreaseUseArray implements Runnable {

    private SyncCounter c;
    private QueueInterface<Integer> q;

    public IncreaseUseArray(SyncCounter c, QueueInterface<Integer> q) {
        this.c = c;
        this.q = q;
    }

    public void run() {
        int hold = 0;

        while (!q.isEmpty()) {
            try {
                hold = q.dequeue();
            } catch (QueueUnderflowException e) {
                System.out.println("Attempted to dequeue from an empty queue!");
            }

            for (int i = 1; i <= hold; i++) {
                c.increment();
            }
        }
    }

}
