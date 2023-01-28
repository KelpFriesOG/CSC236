package chapter4;

public class DLLNode<T> {

    private T info;
    private DLLNode<T> next = null, prev = null;

    public DLLNode(T info) {
        this.info = info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public void setNext(DLLNode<T> next) {
        this.next = next;
    }

    public void setPrev(DLLNode<T> prev) {
        this.prev = prev;
    }

    public T getInfo() {
        return this.info;
    }

    public DLLNode<T> getNext() {
        return this.next;
    }

    public DLLNode<T> getPrev() {
        return this.prev;
    }

}
