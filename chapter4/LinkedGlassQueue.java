
package chapter4;

public class LinkedGlassQueue<T> extends LinkedQueue<T> implements GlassQueueInterface<T> {

    public LinkedGlassQueue() {
        super();
    }

    public T peekFront() {
        if (isEmpty()) {
            return null;
        } else {
            return front.getInfo();
        }
    }

    public T peekRear() {
        if (isEmpty()) {
            return null;
        } else {
            return rear.getInfo();
        }
    }
}
