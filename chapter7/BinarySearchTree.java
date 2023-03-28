package chapter7;

import java.util.Comparator;
import java.util.Iterator;

import chapter4.LinkedQueue;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {

    protected BSTNode<T> root;
    protected Comparator<T> comp;
    protected boolean found;

    public BinarySearchTree() {

        root = null;

        comp = new Comparator<T>() {
            public int compare(T element1, T element2) {
                return element1.compareTo(element2);
            }
        };

    }

    public BinarySearchTree(Comparator<T> comp) {

        root = null;
        this.comp = comp;

    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return (root == null);
    }

    @Override
    public T min() {

        if (isEmpty()) {

            return null;

        } else {

            BSTNode<T> node = root;

            while (node.getLeft() != null) {
                node = node.getLeft();
            }

            return node.getInfo();

        }

    }

    @Override
    public T max() {

        if (isEmpty()) {

            return null;

        } else {

            BSTNode<T> node = root;

            while (node.getRight() != null) {
                node = node.getRight();
            }

            return node.getInfo();

        }

    }

    @Override
    public int size() {

        return recSize(root);

    }

    private int recSize(BSTNode<T> node) {

        if (node == null) {
            return 0;
        } else {
            return recSize(node.getLeft()) + recSize(node.getRight()) + 1;
        }

    }

    @Override
    public boolean contains(T target) {

        return recContains(target, root);

    }

    private boolean recContains(T target, BSTNode<T> node) {

        if (node == null) { // BASE case, target node not found

            return false;

        } else if (comp.compare(target, node.getInfo()) < 0) {

            // If the target is lesser than the info of the current node...
            // RECURSIVE CASE, search the left subtree of the current node.
            return recContains(target, node.getLeft());

        } else if (comp.compare(target, node.getInfo()) > 0) {

            // If the target is greater than the info of the current node...
            // RECURSIVE CASE, search the right subtree of the current node.
            return recContains(target, node.getRight());

        } else { // BASE case, target node found!

            return true;

        }

    }

    @Override
    public T get(T target) {

        return recGet(target, root);

    }

    private T recGet(T target, BSTNode<T> node) {

        if (node == null) { // BASE case, target node not found

            return null;

        } else if (comp.compare(target, node.getInfo()) < 0) {

            // If the target is lesser than the info of the current node...
            // RECURSIVE CASE, search the left subtree of the current node.
            return recGet(target, node.getLeft());

        } else if (comp.compare(target, node.getInfo()) > 0) {

            // If the target is greater than the info of the current node...
            // RECURSIVE CASE, search the right subtree of the current node.
            return recGet(target, node.getRight());

        } else { // BASE case, target node found!

            return node.getInfo();

        }

    }

    private void preOrder(BSTNode<T> node, LinkedQueue<T> q) {

        if (node != null) {

            q.enqueue(node.getInfo());
            preOrder(node.getLeft(), q);
            preOrder(node.getRight(), q);

        }

    }

    private void inOrder(BSTNode<T> node, LinkedQueue<T> q) {

        if (node != null) {

            inOrder(node.getLeft(), q);
            q.enqueue(node.getInfo());
            inOrder(node.getRight(), q);

        }

    }

    private void postOrder(BSTNode<T> node, LinkedQueue<T> q) {

        if (node != null) {

            postOrder(node.getLeft(), q);
            postOrder(node.getRight(), q);
            q.enqueue(node.getInfo());

        }

    }

    @Override
    public Iterator<T> getIterator(Traversal orderType) {

        final LinkedQueue<T> infoQueue = new LinkedQueue<T>();

        if (orderType == Traversal.Preorder) {
            preOrder(root, infoQueue);
        } else if (orderType == Traversal.Inorder) {
            inOrder(root, infoQueue);
        } else if (orderType == Traversal.Postorder) {
            postOrder(root, infoQueue);
        }

        return new Iterator<T>() {

            public boolean hasNext() {

                return !infoQueue.isEmpty();
            }

            public T next() {

                if (hasNext()) {

                    return infoQueue.dequeue();

                } else {

                    throw new IndexOutOfBoundsException("illegal invocation of"
                            + "in BinarySearchTree iterator!\n");

                }

            }

            public void remove() {

                throw new UnsupportedOperationException("Unsupported"
                        + " remove attempted on BinarySearchTree iterator.\n");

            }

        };

    }

    @Override
    public boolean add(T element) {

        root = recAdd(element, root);
        return true;

    }

    private BSTNode<T> recAdd(T element, BSTNode<T> node) {

        if (node == null) {
            // BASE case: we found the location to place our newnode
            node = new BSTNode<T>(element);
        } else if (comp.compare(element, node.getInfo()) <= 0) {
            // RECURSIVE case: continue finding a spot to add in the left subtree
            node.setLeft(recAdd(element, node.getLeft()));
        } else {
            // RECURSIVE case: continue finding a spot to add in the right subtree
            node.setRight(recAdd(element, node.getRight()));
        }

        return node;

    }

    @Override
    public boolean remove(T target) {

        root = recRemove(target, root);
        return found;

    }

    private T getPredecessor(BSTNode<T> subtree) {

        BSTNode<T> temp = subtree;
        while (temp.getRight() != null) {
            temp = temp.getRight();
        }

        return temp.getInfo();

    }

    private BSTNode<T> removeNode(BSTNode<T> node) {

        if (node.getLeft() == null && node.getRight() == null) {

            return null;

        } else if (node.getLeft() == null) {

            return node.getRight();

        } else if (node.getRight() == null) {

            return node.getLeft();

        } else {

            T data = getPredecessor(node.getLeft());
            node.setInfo(data);
            node.setLeft(recRemove(data, node.getLeft()));
            return node;

        }

    }

    private BSTNode<T> recRemove(T target, BSTNode<T> node) {

        if (node == null) {

            found = false;

        } else if (comp.compare(target, node.getInfo()) < 0) {
            node.setLeft(recRemove(target, node.getLeft()));
        } else if (comp.compare(target, node.getInfo()) > 0) {
            node.setRight(recRemove(target, node.getRight()));
        } else {
            node = removeNode(node);
            found = true;
        }

        return node;

    }

}
