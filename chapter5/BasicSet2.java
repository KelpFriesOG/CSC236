package chapter5;

public class BasicSet2<T> implements CollectionInterface<T> {

    LinkedCollection<T> set;

    public BasicSet2() {
        set = new LinkedCollection<T>();
    }

    public boolean add(T element) {

        if (!this.contains(element)) {
            set.add(element);
            return true;
        } else {
            return false;
        }

    }

    public int size() {
        return set.size();
    }

    public boolean contains(T target) {
        return set.contains(target);
    }

    public boolean remove(T target) {
        return set.remove(target);
    }

    public T get(T target) {
        return set.get(target);
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public boolean isFull() {
        return set.isFull();
    }

}
