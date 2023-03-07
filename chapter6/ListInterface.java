package chapter6;

import chapter5.CollectionInterface;

public interface ListInterface<T> extends CollectionInterface<T>, Iterable<T> {

    void add(int index, T element);
    // Will throw IndexOutOfBoundsException if it is passed an
    // index argument that is not in the range of [0, size()-1].
    // Otherwise, we add the element to this list at the specified
    // index. All current elements at that position or higher have
    // 1 added to their index.

    // This method is OPTIONAL. Throws UnsupportedOperationException
    // if not supported.

    T set(int index, T newElement);
    // Will throw IndexOutOfBoundsException if it is passed an
    // index argument that is not in the range of [0, size()-1].
    // Otherwise, we replace the element on this list at the
    // position defined by the passed index with newElement.

    // This method is OPTIONAL. Throws UnsupportedOperationException
    // if not supported.

    T get(int index);
    // Throws an IndexOutOfBoundsException if it is passed an
    // index argument that is not in the range of [0, size()-1].
    // Otherwise, we return the element of this list at the
    // position index.

    int indexOf(T target);
    // If this list contains an element e such that e.equals(target),
    // then we return the index of this element. Note that
    // if there are duplicates we return
    // the index of the first element we encounter.
    // If the element is not found, we return -1.

    T remove(int index);
    // Will throw IndexOutOfBoundsException if it is passed an
    // index argument that is not in the range of [0, size()-1].
    // Otherwise, we remove the element in the list at the given
    // index. We return the removed element, and all elements
    // at positions higher than the index have 1 subtracted from
    // their position.

}
