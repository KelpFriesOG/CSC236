package chapter4;

public class Demo03 {

    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        Runnable r = new Increase(c, 10000);
        Thread t = new Thread(r);
        

        t.start();
        t.join();

        System.out.println("Expected: 10000");
        System.out.println("Actual count: " + c.getCount());


        
    }

}
