package chapter5;

public interface BagInterface<T> extends CollectionInterface<T> {

    T grab();

    int count(T target);

    int removeAll(T target);

    void clear();

}
