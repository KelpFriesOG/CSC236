package chapter4;

public class Demo07 {

    public static void main(String[] args) throws InterruptedException {

        int LIMIT = 100;
        SyncCounter c = new SyncCounter();
        QueueInterface<Integer> q;

        q = new SyncArrayBoundedQueue<Integer>(LIMIT);

        for (int i = 1; i <= LIMIT; i++) {

            try {
                q.enqueue(i);
            } catch (QueueOverflowException e) {

            }

        }

        Runnable r1 = new IncreaseUseArray(c, q);
        Runnable r2 = new IncreaseUseArray(c, q);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count is: " + c.getCount());

    }

}
