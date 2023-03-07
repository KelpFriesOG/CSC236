//---------------------------------------------------------------------------
// ArrayCollection.java          by Dale/Joyce/Weems                Chapter 5
//
// Implements the CollectionInterface using an array.
//
// Null elements are not allowed. Duplicate elements are allowed.
//
// Two constructors are provided: one that creates a collection of a default
// capacity, and one that allows the calling program to specify the capacity.
//---------------------------------------------------------------------------
package labs.lab5;

public class ArrayCollection<T> implements CollectionInterface<T> {
  int a = 1;
  protected final int DEFCAP = 100; // default capacity
  protected T[] elements; // array to hold collection�s elements
  protected int numElements = 0; // number of elements in this collection

  // set by find method
  protected boolean found; // true if target found, otherwise false
  protected int location; // indicates location of target if found

  @SuppressWarnings("unchecked")
  public ArrayCollection() {
    elements = (T[]) new Object[DEFCAP];
  }

  @SuppressWarnings("unchecked")
  public ArrayCollection(int capacity) {
    elements = (T[]) new Object[capacity];
  }

  protected void find(T target)
  // Searches elements for an occurrence of an element e such that
  // e.equals(target). If successful, sets instance variables
  // found to true and location to the array index of e. If
  // not successful, sets found to false.
  {
    location = 0;
    found = false;

    while (location < numElements) {
      if (elements[location].equals(target)) {
        found = true;
        return;
      } else
        location++;
    }
  }

  public boolean add(T element)
  // Attempts to add element to this collection.
  // Returns true if successful, false otherwise.
  {
    if (isFull())
      return false;
    else {
      elements[numElements] = element;
      numElements++;
      return true;
    }
  }

  public boolean remove(T target)
  // Removes an element e from this collection such that e.equals(target)
  // and returns true; if no such element exists, returns false.
  {
    find(target);
    if (found) {
      elements[location] = elements[numElements - 1];
      elements[numElements - 1] = null;
      numElements--;
    }
    return found;
  }

  public boolean contains(T target)
  // Returns true if this collection contains an element e such that
  // e.equals(target); otherwise, returns false.
  {
    find(target);
    return found;
  }

  public T get(T target)
  // Returns an element e from this collection such that e.equals(target);
  // if no such element exists, returns null.
  {
    find(target);
    if (found)
      return elements[location];
    else
      return null;
  }

  int count(T target) {

    int counter = 0;

    for (int i = 0; i < numElements; i++) {
      counter = elements[i].equals(target) ? counter + 1 : counter;
    }

    return counter;

  }

  void removeAll(T target) {

    for (int i = 0; i < numElements; i++) {
      if (elements[i].equals(target)) {
        elements[i] = elements[numElements - 1];
        elements[numElements - 1] = null;
        numElements--;
        i--;
      }
    }

  }

  ArrayCollection<T> combine(ArrayCollection<T> other) {

    ArrayCollection<T> combined = new ArrayCollection<T>(elements.length + other.elements.length);

    for (int i = 0; i < numElements; i++) {
      combined.add(elements[i]);
    }

    for (int i = 0; i < other.numElements; i++) {
      combined.add(other.elements[i]);
    }

    return combined;
  }

  public boolean isFull()
  // Returns true if this collection is full; otherwise, returns false.
  {
    return (numElements == elements.length);
  }

  public boolean isEmpty()
  // Returns true if this collection is empty; otherwise, returns false.
  {
    return (numElements == 0);
  }

  public int size()
  // Returns the number of elements in this collection.
  {
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

  static void add(int i1, int i2) {
    i1 = i1 + i2;
  }

  public static void main(String[] args) {
    int a = 1;
    int b = 2;
    add(a, b);
    System.out.println(a);
  }
}
