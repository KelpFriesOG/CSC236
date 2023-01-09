package chapter4;

public class ArrayBoundedQueue<T> implements QueueInterface<T> {

    protected final int CAPACITY = 100;
    protected T[] elements;
    protected int numElements;
    protected int front = 0;
    protected int rear;

    @SuppressWarnings("unchecked")
    public ArrayBoundedQueue() {
        elements = (T[]) new Object[CAPACITY];
        rear = CAPACITY - 1;
    }

    @SuppressWarnings("unchecked")
    public ArrayBoundedQueue(int maxSize) {
        elements = (T[]) new Object[maxSize];
        rear = maxSize - 1;
    }

    @Override
    public void enqueue(T element) throws QueueOverflowException {

        if (!isFull()) {
            rear = (rear + 1) % elements.length;
            elements[rear] = element;
            numElements = numElements + 1;
        } else {
            throw new QueueOverflowException("Enqueue attempted on a full queue");
        }

    }

    @Override
    public T dequeue() throws QueueUnderflowException {

        if (!isEmpty()) {
            T result = elements[front];
            elements[front] = null;
            front = (front + 1) % elements.length;
            numElements = numElements - 1;
            return result;
        } else {
            throw new QueueUnderflowException("Dequeue attempted on empty queue");
        }

    }

    @Override
    public boolean isFull() {
        return numElements == elements.length;
    }

    @Override
    public boolean isEmpty() {
        return numElements == 0;
    }

    @Override
    public int size() {
        return numElements;
    }

}
