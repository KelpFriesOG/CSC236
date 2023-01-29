package chapter4;

public class Counter {

    private int count;

    public Counter() {

        count = 0;

    }

    public void increment() {
        count += 1;
    }

    public int getCount() {
        return count;
    }

}
