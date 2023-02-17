package chapter5;

public class BasicSet1<T> extends LinkedCollection<T> {

    public BasicSet1() {
        super();
    }

    @Override
    public boolean add(T element) {

        if (!this.contains(element)) {
            super.add(element);
            return true;
        } else {
            return false;
        }

    }

}
