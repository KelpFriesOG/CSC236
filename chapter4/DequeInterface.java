package chapter4;

public interface DequeInterface<T> extends QueueInterface<T> {

    void enqueueFront(T element);

    void dequeueRear(T element);

}
