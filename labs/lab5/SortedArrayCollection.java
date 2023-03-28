package labs.lab5;

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

    protected void find(T target)
    // Searches collection for an occurrence of an element e such that
    // e.equals(target). If successful, sets instance variables
    // found to true and location to the array index of e. If
    // not successful, sets found to false and location to the
    // array index where target should be inserted.
    {
        location = 0;
        found = false;
        if (!isEmpty())
            recFind(target, 0, numElements - 1);
    }

    protected void recFind(T target, int first, int last)
    // Used by find.
    {
        int result; // result of the comparison

        if (first > last) { // BASE CASE
            found = false;
            result = target.compareTo(elements[location]);
            if (result > 0)
                location++; // adjust location to indicate insert index
        }

        else { // RECURSIVE CASE
            location = (first + last) / 2;
            result = target.compareTo(elements[location]);
            if (result == 0) // found target, BASE CASE
                found = true;

            else if (result > 0) // target too high
                recFind(target, location + 1, last);
            else // target too low
                recFind(target, first, location - 1);
        }
    }

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

    public T get(T target) {

        find(target);
        if (found) {
            return elements[location];
        } else {
            return null;
        }

    }

    public boolean contains(T target) {
        find(target);
        return found;
    }

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

    T smallest() {
        if (numElements == 0) {
            return null;
        } else {
            return elements[0];
        }
    }

    int greater(T element) {

        // int offset = 0;

        if (numElements == 0) {
            return 0;
        } else {

            location = 0;
            // Location to start search will be beginning of array
            found = false;
            // We will only change found to true if we find a matching element.

            int first = 0, last = numElements - 1, mid = 0;
            int atLeast = numElements;

            while (first < last) {

                found = false;
                mid = (first + last) / 2;
                location = mid;
                int comparison = element.compareTo(elements[mid]);

                if (comparison == 0) {
                    found = true;
                    break;
                } else if (comparison < 0) {
                    last = mid - 1;
                    atLeast = mid;
                } else {
                    first = mid + 1;
                }

            }

            if (first > last) {
                found = false;
                int comparison = element.compareTo(elements[location]);
                if (comparison > 0) {
                    location++; // adjust location to indicate insert index
                    System.out.println("HUH");
                }
            }

            return numElements - atLeast;
        }

    }

    SortedArrayCollection<T> combine(SortedArrayCollection<T> other) {

        SortedArrayCollection<T> combined = new SortedArrayCollection<>(elements.length + other.elements.length);

        int i = 0, j = 0;
        while (i < numElements && j < other.numElements) {

            if (elements[i].compareTo(other.elements[j]) < 0) {
                combined.add(elements[i]);
                i++;
            } else {
                combined.add(other.elements[j]);
                j++;
            }
        }

        if (i < numElements) {
            while (i < numElements) {
                combined.add(elements[i]);
                i++;
            }
        } else {
            while (j < numElements) {
                combined.add(other.elements[j]);
                j++;
            }
        }

        return combined;

    }

    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return numElements == 0;
    }

    public int size() {
        return numElements;
    }

    @Override
    public String toString() {

        String ret = "[ ";

        for (int i = 0; i < numElements - 1; i++) {
            ret += elements[i].toString() + ", ";
        }

        ret += elements[numElements - 1].toString() + " ]";

        return ret;

    }

}
