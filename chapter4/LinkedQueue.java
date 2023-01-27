package chapter4;

import chapter2.LLNode;

public class LinkedQueue<T> implements QueueInterface<T> {

    LLNode<T> front;
    LLNode<T> rear;
    protected int numElements = 0;

    public LinkedQueue() {
        front = rear = null;
    }

    @Override
    public void enqueue(T element) throws QueueOverflowException {

        LLNode<T> newNode = new LLNode<T>(element);
        if (rear == null) {
            front = newNode;
        } else {
            rear.setLink(newNode);
        }

        rear = newNode;
        numElements++;

    }

    @Override
    public T dequeue() throws QueueUnderflowException {

        if (!isEmpty()) {

            T element = front.getInfo();

            front = front.getLink();
            if (front == null) {
                rear = null;
            }

            numElements--;
            return element;

        } else {
            throw new QueueUnderflowException(
                    "Dequeue attempted on empty queue");
        }

    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return numElements == 0 ? true : false;
    }

    @Override
    public int size() {
        return numElements;
    }

}