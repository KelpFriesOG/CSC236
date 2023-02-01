package chapter4;

public class SyncArrayBoundedQueue<T> implements QueueInterface<T> {

    protected final int DEFCAP = 100;
    protected T[] elements;
    protected int numElements = 0;
    protected int front = 0;
    protected int rear;

    @SuppressWarnings("unchecked")
    public SyncArrayBoundedQueue() {

        elements = (T[]) new Object[DEFCAP];
        rear = DEFCAP - 1;

    }

    @SuppressWarnings("unchecked")
    public SyncArrayBoundedQueue(int maxSize) {

        elements = (T[]) new Object[maxSize];
        rear = maxSize - 1;

    }

    public synchronized void enqueue(T element) throws QueueOverflowException {

        if (!isFull()) {
            rear = (rear + 1) % elements.length;
            elements[rear] = element;
            numElements += 1;
        } else {
            throw new QueueOverflowException("Enqueue attempted on full queue!");
        }

    }

    @Override
    public synchronized T dequeue() throws QueueUnderflowException {

        if (!isEmpty()) {
            T ret = elements[front];
            elements[front] = null;
            front = (front + 1) % elements.length;
            numElements = numElements - 1;
            return ret;
        } else {
            throw new QueueUnderflowException("Dequeue attempted on empty queue!");
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
