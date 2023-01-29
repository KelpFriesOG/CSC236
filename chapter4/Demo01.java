package chapter4;

public class Demo01 {

    public static void main(String[] args) {

        Counter c = new Counter();
        c.increment();
        c.increment();
        c.increment();
        System.out.println("Count is: " + c.getCount());

    }

}
