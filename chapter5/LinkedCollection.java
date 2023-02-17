package chapter5;

import chapter2.LLNode;

public class LinkedCollection<T> implements CollectionInterface<T> {

    protected LLNode<T> head;
    protected int numElements = 0;

    // Set the variables for the find method.
    protected boolean found;
    protected LLNode<T> location;
    protected LLNode<T> previous;

    public LinkedCollection() {
        numElements = 0;
        head = null;
    }

    public LinkedCollection(T element) {
        numElements = 1;
        head = new LLNode<T>(element);
    }

    protected void find(T target) {
        location = head;
        found = false;

        while (location != null) {

            if (location.getInfo().equals(target)) {
                found = true;
                return;
            }

            else {
                previous = location;
                location = location.getLink();
            }

        }

    }

    public boolean contains(T target) {
        find(target);
        return found;
    }

    public T get(T target) {

        find(target);
        if (found) {
            return location.getInfo();
        } else {
            return null;
        }

    }

    public boolean add(T element) {

        LLNode<T> newNode = new LLNode<T>(element);
        newNode.setLink(head);
        numElements++;
        return true;

    }

    public boolean remove(T target) {

        find(target);

        if (found) {

            if (head != location) {
                previous.setLink(location.getLink());
            } else {
                head = head.getLink();
            }
            numElements--;
        }

        return found;
    }

    public boolean isEmpty() {
        return (numElements == 0);
    }

    public boolean isFull() {
        return false;
    }

    public int size() {
        return numElements;
    }

}
