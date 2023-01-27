package chapter4;

public interface GlassQueueInterface<T> extends QueueInterface<T> {

    public T peekFront();

    public T peekRear();

}
