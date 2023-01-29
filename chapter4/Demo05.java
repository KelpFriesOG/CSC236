package chapter4;

public class Demo05 {

    public static void main(String[] args) throws InterruptedException {

        SyncCounter sc = new SyncCounter();
        Runnable r1 = new IncreaseSync(sc, 5000);
        Runnable r2 = new IncreaseSync(sc, 5000);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Expected: 10000");
        System.out.println("Actual count: " + sc.getCount());

    }

}
