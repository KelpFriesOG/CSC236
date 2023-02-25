//---------------------------------------------------------------------------
// LinkedQueue.java            by Dale/Joyce/Weems                  Chapter 4
//
// Implements QueueInterface using a linked list.
//---------------------------------------------------------------------------
package labs.lab4;

import chapter2.LLNode;
import chapter4.QueueInterface;

public class LinkedQueue<T> implements QueueInterface<T> {

    protected LLNode<T> front; // reference to the front of this queue
    protected LLNode<T> rear; // reference to the rear of this queue
    protected int numElements = 0; // number of elements in this queue

    public LinkedQueue() {
        front = null;
        rear = null;
    }

    public void enqueue(T element)
    // Adds element to the rear of this queue.
    {
        LLNode<T> newNode = new LLNode<T>(element);
        if (rear == null)
            front = newNode;
        else
            rear.setLink(newNode);
        rear = newNode;
        numElements++;
    }

    public T dequeue()
    // Throws QueueUnderflowException if this queue is empty;
    // otherwise, removes front element from this queue and returns it.
    {
        if (isEmpty())
            throw new QueueUnderflowException("Dequeue attempted on empty queue.");
        else {
            T element;
            element = front.getInfo();
            front = front.getLink();
            if (front == null)
                rear = null;
            numElements--;
            return element;
        }
    }

    void remove(int count) {

        if (numElements < count) {
            throw new QueueUnderflowException();
        } else {
            for (int i = 0; i < count; i++) {
                front = front.getLink();
                if (front == null)
                    rear = null;
                numElements--;
            }
        }

    }

    boolean swapStart() {

        if (numElements < 2) {
            return false;
        } else {
            T tempInfo = front.getInfo();
            front.setInfo(front.getLink().getInfo());
            front.getLink().setInfo(tempInfo);
            return true;
        }

    }

    boolean swapEnds() {

        if (numElements < 2) {
            return false;
        } else {
            T tempInfo = front.getInfo();
            front.setInfo(rear.getInfo());
            rear.setInfo(tempInfo);
            return true;
        }

    }

    public boolean isEmpty()
    // Returns true if this queue is empty; otherwise, returns false.
    {
        return (front == null);
    }

    public boolean isFull()
    // Returns false - a linked queue is never full.
    {
        return false;
    }

    public int size()
    // Returns the number of elements in this queue.
    {
        return numElements;
    }

    @Override
    public String toString() {

        String ret = "FRONT: ";
        LLNode<T> current = front;

        while (current != null) {
            ret += current.toString() + "--> ";
            if (current.getLink() == null) {
                ret += " : REAR";
            }
            current = current.getLink();
        }

        return ret;

    }

}
