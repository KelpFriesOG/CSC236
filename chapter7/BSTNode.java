package chapter7;

public class BSTNode<T> {

    private T info;
    private BSTNode<T> left;
    private BSTNode<T> right;

    public BSTNode(T info) {
        this.info = info;
        left = right = null;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public T getInfo() {
        return info;
    }

    public void setLeft(BSTNode<T> link) {
        left = link;
    }

    public void setLeft(T link) {
        left = new BSTNode<T>(link);
    }

    public void setRight(BSTNode<T> link) {
        right = link;
    }

    public void setRight(T link) {
        right = new BSTNode<T>(link);
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

}
