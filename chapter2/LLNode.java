package chapter2;

public class LLNode<T> {

    protected T info;
    protected LLNode<T> link;

    public LLNode(T info) {
        this.info = info;
        link = null;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public LLNode<T> getLink() {
        return link;
    }

    public void setLink(LLNode<T> link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return this.info.toString();
    }

}
