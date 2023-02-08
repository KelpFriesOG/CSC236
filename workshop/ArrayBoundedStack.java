package workshop;

import chapter2.StackInterface;
import chapter2.StackOverflowException;
import chapter2.StackUnderflowException;

public class ArrayBoundedStack<T> implements StackInterface<T> {

    protected final int CAPACITY = 100;
    protected T[] elements;
    protected int topIndex = -1;

    /*
     * Although we declare our array to be an array of class T, we must
     * instantiate it to be an array of class Object. Then, to ensure that the
     * desired type checking takes place, we cast array elements into
     * class T.
     * 
     * Even though this approach is somewhat awkward and typically generates
     * a compiler warning, it is how we must create generic collections
     * using arrays in Java
     */
    @SuppressWarnings("unchecked")
    public ArrayBoundedStack() {
        elements = (T[]) new Object[CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public ArrayBoundedStack(int maxSize) {
        elements = (T[]) new Object[maxSize];
    }

    @Override
    public void push(T element) throws StackOverflowException {
        if (!isFull()) {
            topIndex++;
            elements[topIndex] = element;
        } else {
            throw new StackOverflowException("The stack is full, cannot push more elements!");
        }

    }

    @Override
    public void pop() throws StackUnderflowException {
        if (!isEmpty()) {
            elements[topIndex] = null;
            topIndex--;
        } else {
            throw new StackUnderflowException("The stack is empty, cannot pop any elements!");
        }

    }

    public void popSome(int count) throws StackUnderflowException {

        if (count > topIndex + 1) {
            throw new StackUnderflowException("Cannot remove that many elements!");
        } else {
            for (int i = 0; i < count; i++) {
                pop();
            }
        }
    }

    public boolean swapStart() {

        if (topIndex + 1 < 2) {
            return false;
        } else {
            T temp = elements[topIndex];
            elements[topIndex] = elements[topIndex - 1];
            elements[topIndex - 1] = temp;
            return true;
        }
    }

    @Override
    public T top() throws StackUnderflowException {
        if (!isEmpty()) {
            T value = elements[topIndex];
            return value;
        } else {
            throw new StackUnderflowException("The stack is empty, cannot look at any elements!");
        }
    }

    @Override
    public boolean isFull() {
        return topIndex == elements.length - 1;
        // Returns true if the top index is pointing to the the n-1th element
        // This would mean our array is filled up!
    }

    @Override
    public boolean isEmpty() {
        return topIndex == -1;
        // Returns true if topIndex == -1 (no elements in stack)
    }

    @Override
    public String toString() {

        String result = "";

        for (int i = 0; i < topIndex + 1; i++) {

            result += elements[i].toString();
            result += " ";

        }

        return result;

    }

}