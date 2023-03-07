//---------------------------------------------------------------------------
// ArrayBoundedQueue.java        by Dale/Joyce/Weems                Chapter 4
//
// Implements QueueInterface with an array to hold the queue elements.
//
// Two constructors are provided: one that creates a queue of a default
// capacity and one that allows the calling program to specify the capacity.
//---------------------------------------------------------------------------
package labs.lab4;

import chapter4.QueueInterface;

public class ArrayBoundedQueue<T> implements QueueInterface<T> {

    protected final int DEFCAP = 100; // default capacity
    protected T[] elements; // array that holds queue elements
    protected int numElements = 0; // number of elements in this queue
    protected int front = 0; // index of front of queue
    protected int rear; // index of rear of queue

    @SuppressWarnings("unchecked")
    public ArrayBoundedQueue() {
        elements = (T[]) new Object[DEFCAP];
        rear = DEFCAP - 1;
    }

    @SuppressWarnings("unchecked")
    public ArrayBoundedQueue(int maxSize) {
        elements = (T[]) new Object[maxSize];
        rear = maxSize - 1;
    }

    public void enqueue(T element)
    // Throws QueueOverflowException if this queue is full;
    // otherwise, adds element to the rear of this queue.
    {
        if (isFull())
            throw new QueueOverflowException("Enqueue attempted on a full queue.");
        else {
            rear = (rear + 1) % elements.length;
            elements[rear] = element;
            numElements = numElements + 1;
        }
    }

    public T dequeue()
    // Throws QueueUnderflowException if this queue is empty;
    // otherwise, removes front element from this queue and returns it.
    {
        if (isEmpty())
            throw new QueueUnderflowException("Dequeue attempted on empty queue.");
        else {
            T toReturn = elements[front];
            elements[front] = null;
            front = (front + 1) % elements.length;
            numElements = numElements - 1;
            return toReturn;
        }
    }

    public boolean isEmpty()
    // Returns true if this queue is empty; otherwise, returns false.
    {
        return (numElements == 0);
    }

    public boolean isFull()
    // Returns true if this queue is full; otherwise, returns false.
    {
        return (numElements == elements.length);
    }

    public int size()
    // Returns the number of elements in this queue.
    {
        return numElements;
    }

    void remove(int count) {

        if (numElements < count) {
            throw new QueueUnderflowException();
        } else {
            for (int i = 0; i < count; i++) {
                // T toReturn = elements[front];
                elements[front] = null;
                front = (front + 1) % elements.length;
                numElements = numElements - 1;
            }
        }
    }

    int space() {
        return elements.length - numElements;
    }

    boolean swapStart() {

        if (numElements < 2) {
            return false;
        } else {
            T temp = elements[(front + 1) % elements.length];
            elements[(front + 1) % elements.length] = elements[front];
            elements[front] = temp;
            return true;
        }

    }

    boolean swapEnds() {

        if (numElements < 2) {
            return false;
        } else {
            T temp = elements[front];
            elements[front] = elements[rear];
            elements[rear] = temp;
            return true;
        }

    }

    @Override
    public String toString() {

        String ret = "[ ";
        int index = front;

        for (int i = 0; i < numElements; i++) {

            ret += " <-- " + elements[index].toString();
            index = (index + 1) % elements.length;
        }

        ret = ret + "]";

        return ret;

    }

}
