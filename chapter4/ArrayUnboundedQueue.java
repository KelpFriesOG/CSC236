package chapter4;

public class ArrayUnboundedQueue<T> implements QueueInterface<T> {

    protected final int CAPACITY = 100;
    protected T[] elements;
    protected int origCap; // This is new for the unbounded queue!
    protected int numElements;
    protected int front = 0;
    protected int rear;

    @SuppressWarnings("unchecked")
    public ArrayUnboundedQueue() {
        elements = (T[]) new Object[CAPACITY];
        rear = CAPACITY - 1;
        origCap = CAPACITY;
    }

    @SuppressWarnings("unchecked")
    public ArrayUnboundedQueue(int origCap) {
        elements = (T[]) new Object[origCap];
        rear = origCap - 1;
        this.origCap = origCap;
    }

    // New method for the unbounded queue which is the
    // cornerstone that enlarges the queue and is called
    // whenever the elements array hits max capacity.
    @SuppressWarnings("unchecked")
    private void enlarge() {

        // Creating the larger array
        T[] newArray = (T[]) new Object[elements.length + origCap];

        // Copying the contents from the smaller array into the larger array
        // smaller = Orig, larger = New
        int currentOrig = front;
        for (int currentNew = 0; currentNew < numElements; currentNew++) {
            newArray[currentNew] = elements[currentOrig];
            currentOrig = (currentOrig + 1) % elements.length;
        }

        // Updating the instance variables
        elements = newArray;
        front = 0;
        rear = numElements - 1;

    }

    @Override
    public void enqueue(T element) throws QueueOverflowException {

        if (numElements == elements.length) {
            enlarge();
        }

        rear = (rear + 1) % elements.length;
        elements[rear] = element;
        numElements = numElements + 1;

    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        // UNCHANGED from ArrayBoundedQueue class implementation!
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
        // CHANGED: Unbounded queue can never be full!
        return false;
    }

    @Override
    public boolean isEmpty() {
        // UNCHANGED from ArrayBoudnedQueue class implementation!
        return numElements == 0;
    }

    @Override
    public int size() {
        // UNCHANGE from ArrayBoundedQueue class implementation!
        return numElements;
    }

    @Override
    public String toString(){

        String response = "[";
        for(int i = 0; i < numElements-1; i++){

            response += elements[(front + i) % elements.length] + ", ";

        }

        response += elements[rear] + "]";

        return response;

    }

}
