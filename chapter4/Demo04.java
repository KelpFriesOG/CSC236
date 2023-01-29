package chapter4;

public class Demo04 {

    public static void main(String[] args) throws InterruptedException {

        Counter c = new Counter();
        Runnable r1 = new Increase(c, 5000);
        Runnable r2 = new Increase(c, 5000);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Expected: 10000");
        System.out.println("Actual count: " + c.getCount());

    }

}
