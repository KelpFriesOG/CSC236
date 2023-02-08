package chapter5;

public class Duo<T extends Comparable<T>> {

    protected T first;
    protected T second;

    public Duo(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T larger() {

        if (first.compareTo(second) > 0) {
            // In other words if first is greater than second
            return first;
        } else {
            // If both or equal, or second > first, then
            return second;
        }

    }
}
