package chapter5;

public interface CollectionInterface<T> {

    boolean add(T element);
    // Attempts to add an element to the collection.
    // Will return true if operation is successful and false otherwise.

    T get(T target);
    // Returns an element, e, from the collection such that
    // e.equals(target) is true, and returns true. If no such
    // element fulfills the condition we return false.

    boolean contains(T target);
    // Returns true if the collection contains an element e
    // such that e.equals(target) is true. Else returns false.

    boolean remove(T target);
    // Removes an element, e, from the collection such that
    // e.equals(target) is true and returns true. If no such
    // element fulfills the condition we return false.

    boolean isFull();
    // Returns true if the collection is full, otherwise returns false.

    boolean isEmpty();
    // Returns true if this collection is empty, otherwise returns false.

    int size();
    // Returns the number of elements in the collection.

}
