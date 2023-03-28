package chapter5;

import java.util.Arrays;

public class SortedArrayCollection<T extends Comparable<T>> implements CollectionInterface<T> {

    protected final int DEFCAP = 100;
    protected int origCap;
    protected T[] elements;
    protected int numElements = 0;
    int location = -1;
    boolean found = false;

    @SuppressWarnings("unchecked")
    public SortedArrayCollection() {
        elements = (T[]) new Comparable[DEFCAP];
        origCap = DEFCAP;
    }

    @SuppressWarnings("unchecked")
    public SortedArrayCollection(int capacity) {
        elements = (T[]) new Comparable[capacity];
        origCap = capacity;
    }

    protected void enlarge() {

        T[] newArray = Arrays.copyOf(elements, elements.length + origCap);
        elements = newArray;

    }

    protected void find(T target) {

        if (isEmpty()) {
            location = 0;
            return;
        }

        location = 0;
        // Location to start search will be beginning of array
        found = false;
        // We will only change found to true if we find a matching element.

        int first = 0, last = numElements - 1, mid = 0;

        while (first < last) {

            found = false;
            mid = (first + last) / 2;
            location = mid;
            int comparison = target.compareTo(elements[mid]);

            if (comparison == 0) {
                found = true;
                break;
            } else if (comparison < 0) {
                last = mid - 1;
            } else {
                first = mid + 1;
            }

        }

        if (!found) {
            int comparison = target.compareTo(elements[location]);
            if (comparison > 0) {
                location++;
            }
        }

    }

    @Override
    public boolean add(T element) {

        // Since the collection is unbounded this method
        // cannot fail as long as the type of T is accurate.

        if (numElements == elements.length)
            enlarge();

        find(element);

        for (int i = numElements; i > location; i--) {
            elements[i] = elements[i - 1];
        }

        elements[location] = element;
        numElements++;
        return true;

    }

    @Override
    public T get(T target) {

        find(target);
        if (found) {
            return elements[location];
        } else {
            return null;
        }

    }

    @Override
    public boolean contains(T target) {
        find(target);
        return found;
    }

    @Override
    public boolean remove(T target) {

        find(target);

        if (found) {
            for (int i = location; i <= numElements - 2; i++) {
                elements[i] = elements[i + 1];
            }

            elements[numElements - 1] = null;
            numElements--;

        }

        return found;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return numElements == 0;
    }

    @Override
    public int size() {
        return numElements;
    }

    public static void main(String[] args) {

        // SortedArrayCollection<Integer> myColl = new SortedArrayCollection<>();
        // myColl.add(7);
        // myColl.add(3);
        // myColl.add(4);
        // myColl.add(5);
        // myColl.add(3);


        

        // System.out.println(myColl.toString());

    }

}
