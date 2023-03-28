package chapter7;

import java.util.Iterator;

import chapter5.CollectionInterface;

public interface BSTInterface<T> extends CollectionInterface<T> /* ,Iterable<T> */ {

    // enum type to specify type of traversal for iterator
    public enum Traversal {
        Inorder, Preorder, Postorder
    }

    T min();
    // If the BST is empty, the method will return null.
    // Otherwise the smallest element of the tree is returned.

    T max();
    // If the BST is empty, the method will return null.
    // Otherwise the largest element of the tree is returned.

    public Iterator<T> getIterator(Traversal orderType);
    // Creates and returns an Iterator that implements a specified
    // traversal of the tree in the particular order given.
    // Note that the iterator offers a snapshot of the tree
    // at the time of the iterator's creation.
    // If elements in the tree are manipulated after the iterator's
    // creation, those changes won't be reflected in the iterator!

}
