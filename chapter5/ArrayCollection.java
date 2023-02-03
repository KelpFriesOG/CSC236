package chapter5;

public class ArrayCollection<T> implements CollectionInterface<T> {

    protected T[] elements;
    protected int numElements;
    protected final int DEFCAP = 100;

    int location = -1;
    boolean found = false;

    @SuppressWarnings("unchecked")
    public ArrayCollection() {
        elements = (T[]) new Object[DEFCAP];
    }

    @SuppressWarnings("unchecked")
    public ArrayCollection(int capacity) {
        elements = (T[]) new Object[capacity];
    }

    protected void find(T target) {

        location = 0;
        // Location to start search will be beginning of array
        found = false;
        // We will only change found to true if we find a matching element.

        while (location < numElements) {
            // While loop will iterate from location = 0 to location = numElements-1
            // It will break when location = numElements
            // In other words it will search through all possible indexes.
            if (elements[location].equals(target)) {
                // If the element at the current location index is equal
                // to what we are looking for, then we set found to true and exit!
                found = true;
                return;
            } else {
                // Otherwise we iterate the location and loop
                // again to look at the next index.
                location++;
            }
        }

    }

    public boolean add(T element) {

        if (isFull()) {
            // If the array is full, you cannot add another element
            return false;
        } else {

            // Add the element into the array at the position
            // of numElements, which should be empty
            // (since the first n positions from 0 to n-1 should be filled).
            elements[numElements] = element;

            // Increment the numElements to indicate another
            // element was added.
            numElements++;

            // Return true to let client know element was successfully added.
            return true;

        }

    }

    public boolean remove(T target) {

        find(target);
        // Lets see if we can find the element first

        if (found) {
            // If found was changed to true, then the element was found!

            // Lets overwrite the found element's location with
            // the element at the end of the list.
            // This both removes the existence of the found element,
            // and also removes the hole that may have been caused by
            // the removal alone by immeadiately copying over an element in the
            // spot.
            elements[location] = elements[numElements - 1];

            // If we copied the last element into the found element's location
            // then now we have an unwanted copy of that last element at the end
            // of the list, lets remove this copy by setting it to null!
            // This means this line and the last line combined basically
            // moved the last element into the hole.
            elements[numElements - 1] = null;

            // Decrement numElements to indicate the removal of the an element.
            numElements--;

        }

        // Instead of returning true or false we return found
        // because found is true if the element was found. If an
        // element was found we KNOW FOR A FACT IT WAS REMOVED SUCCESSFULLY.
        // Therefore we can return found which is just true.

        // If we did not find the element then found is false,
        // which is the same value we want to return
        // if we did not do the removal process
        // (i.e. go into the if statement).
        return found;

    }

    public boolean contains(T target) {

        find(target);
        // Calling find to see if the collection contains
        // the object.

        return found;
        // If the find() method found the object, found
        // changed to true, which is exactly what we want to
        // return if the collection contains the object.

        // Otherwise if the find() method did not find it,
        // found is false, which is again exactly what we want
        // to return if the collection does not contain the object.
    }

    public T get(T target) {

        find(target);
        // Calling find() to see if the collection contains
        // the object.

        if (found) {
            // If the find() method found the object,
            // and set found to true, then we
            // just return the object by utilzing the location
            // we found the object at to grab it.
            return elements[location];
        } else {
            // Else, found must be false, in that case the
            // object does not exist in the collection
            // so we return null to indicate that nothing
            // was found.
            return null;
        }

    }

    public boolean isFull() {
        return (numElements == elements.length);
    }

    public boolean isEmpty() {
        return (numElements == 0);
    }

    public int size() {
        return numElements;
    }

}
