package chapter6;

// All credit to Villanova University who had the resources from the book
// http://www.csc.villanova.edu/~joyce/bookFiles/ch06/lists/LBList.java

// All I did is change some syntax and some terminology.
// Also added a boatload of comments.

//----------------------------------------------------------------------------
// LBList.java                by Dale/Joyce/Weems                    Chapter 6
// Link-Based List
//
// Null elements are not permitted on a list. The list is unbounded.
//----------------------------------------------------------------------------

import java.util.Iterator;
import chapter2.LLNode;

public class LBList<T> implements ListInterface<T> {

    // #region Instance Variables

    protected LLNode<T> front; // reference to the front of this list
    protected LLNode<T> rear; // reference to the rear of this list
    protected int numElements = 0; // number of elements in this list

    // set by find method
    protected boolean found; // true if target found, else false
    protected int targetIndex; // list index of target, if found
    protected LLNode<T> location; // node containing target, if found
    protected LLNode<T> previous; // node preceding location

    // #endregion

    // #region Constructors

    public LBList() {
        numElements = 0;
        front = null;
        rear = null;
    }

    // #endregion

    // #region Basic Methods

    public boolean add(T element) {
        // Adds element to the end of this list.

        LLNode<T> newNode = new LLNode<T>(element);
        // Creating a newNode based of given element.

        if (rear == null) {
            // If the rear is null, the list is empty,
            // make the newNode the beginning of the list.
            front = newNode;
        } else {
            // Otherwise just add to the end of the list.
            rear.setLink(newNode);
        }

        // Update the rear reference to refer to the newly added node.
        // (because it was inserted at the end of the list).
        rear = newNode;
        // Increment numElements since we added one more node.
        numElements++;
        // We only ever return true because there is no limit
        // on capacity for a linked list based implementation.
        return true;

    }

    protected void find(T target) {
        // Searches list for an occurence of an element e such that
        // e.equals(target). If successful, sets instance variables
        // found to true, location to node containing e, previous
        // to the node that links to location, and index to the index of
        // the location. If not successful, sets found to false.

        // Initialize the location to reference the front node
        location = front;
        // Initailize found to false
        found = false;
        // Initialize the targetIndex to -1
        targetIndex = -1;

        // Enter the while loop initially if the front
        // (which is what the location is initially set to)
        // is not null (in other words if the list is not empty).

        // After initially entering the loop, location will change,
        // such that the location points to the next node in the chain.
        // The loop will keep going until hitting the end of the list,
        // The node after the last node will be null, setting location to null.

        while (location != null) {
            // Increment the target index first
            targetIndex++;

            // If the information at the location equals the info
            // we are looking for...
            if (location.getInfo().equals(target)) {
                // set found to true
                found = true;
                // return (leave the method)
                return;
            } else { // Otherwise...
                // Set the previous to the location (the node we just looked at).
                previous = location;
                // Set the location to the node that comes after the location node.
                location = location.getLink();
            }
        }

        // After the loop above, if we still come out here, then
        // found must be false, even if the location and previous node
        // references are set to some nodes in the chain.
    }

    public int size() {
        // Returns the number of elements on this list.

        return numElements;
    }

    public boolean contains(T target) {
        // Returns true if this list contains an element e such that
        // e.equals(target); otherwise, returns false.

        // Recall that found is set inside the find method,
        // and is set to true if we find the target element.
        // otherwise found is false.
        find(target);

        // Return the result of the search, either we found it (found = true)
        // or we did not (found = false).
        return found;
    }

    public boolean remove(T target) {
        // Removes an element e from this list such that e.equals(target)
        // and returns true; if no such element exists, returns false.

        // The find method sets the value of the found variable
        // based on if the target element was found in some node in the list.
        find(target);

        // If we found a match
        if (found) {
            // If the location points to the same address as the front reference
            // In other words if the location is the front node...
            if (front == location) {
                // remove first node
                front = front.getLink();
            } else { // Otherwise...

                // Set the link of the node that comes before the location
                // node (previous node) to the node that comes after the location
                // node (location.getLink()).
                previous.setLink(location.getLink());
                // Since the node that location points to is no longer important
                // and is delinked from the chain, we can set location to null.
                // This fully dereferences the underlying node since no reference
                // variables point to it anymore!

                // I added this line myself because it made sense to me,
                // as far as I can tell it does not break the code and
                // frees up space for the compiler ahead of time.
                location = null;
            }

            // If after the removal, the front references no node,
            // well then we know the list as a whole is empty,
            // and that we just removed the only remaining node.
            if (front == null) {
                // Set the reference of rear to null to reinforce that the
                // list is empty. (If we forget this line then rear would still
                // point to the node we partially dereferenced).
                rear = null;
            }

            // Decrement numElements to indicate the
            // removal of a single element.
            numElements--;
        }

        // If found was false then no removal happened,
        // so we return the truth value of found
        // to indicate that removal was unsuccessful.
        // Otherwise if found was true then the removal could happen
        // and was successful, therefore we can return the truth value
        // of found to indicate success.
        // In either case we return the value of found!
        return found;
    }

    public T get(T target) {
        // Returns an element e from this list such that e.equals(target);
        // if no such element exists, returns null.

        // The find method sets the value of the found variable
        // based on if the target element was found in some node in the list.
        find(target);

        // If we found a match, then found is true, and...
        if (found) {
            // The variable location is a reference to the matching node.
            // We just return the information of that node.
            return location.getInfo();
        } else { // Otherwise...
            // We return null to indicate no match
            // and no information to return.
            return null;
        }

    }

    public boolean isEmpty() {
        // Returns true if this list is empty; otherwise, returns false.

        // If numElements is 0, we return true because this indicates the
        // list has no elements. Otherwise return false.
        return (numElements == 0);
    }

    public boolean isFull() {
        // Returns false.

        // A link based implementation is unbounded,
        // it can never be full so isFull() always returns false.
        return false;
    }

    // #endregion

    // #region Indexed Methods

    public void add(int index, T element) {
        // Throws IndexOutOfBoundsException if passed an index argument
        // such that index < 0 or index > size().
        // Otherwise, adds element to this list at position index; all current
        // elements at that index or higher have 1 added to their index.

        // If the provided index is out of bounds (is either less than 0,
        // or greater than the current size of the list
        // (which is the highest index + 1) )...
        if ((index < 0) || (index > size())) {
            // Throw an exception to indicate the invalid index!
            throw new IndexOutOfBoundsException("Illegal index of " + index +
                    " passed to LBList add method.\n");
        }

        // Creating a new node that has the information specified by the element.
        LLNode<T> newNode = new LLNode<T>(element);

        // If the index parameter is 0
        // then we know we are added to the front of
        // the list...
        if (index == 0) {

            // If the front reference is null
            // then we know we are adding to an empty list...
            if (front == null) {

                // Since we know the list is empty
                // we set the front and read reference equal
                // to the newNode.
                front = newNode;
                rear = newNode;

            } else { // Otherwise (if the front != null)...

                // We link the newNode to the current front
                // of the list.
                newNode.setLink(front);
                // Since the position of the newNode is
                // before that of the current front,
                // the front must be updated such that
                // the newNode is the new front of the list.
                front = newNode;
            }
        }

        // If the index is equal to the size of the list,
        // (which is one more than the last valid index)
        // we know that the user seeks to add to the
        // end of the list...
        else if (index == size()) {

            // To add an element at the end of the list,
            // we set link of the rear to the new node.
            rear.setLink(newNode);
            // Since the newNode comes after the current rear
            // we must update the reference of the rear,
            // such that the new node is the new rear.
            rear = newNode;
        }

        // Otherwise
        // (if we are adding in to the interior of the list)...

        else {

            // Creating a new node and initialize it to the front.
            // This node will iterate through the linked list.
            LLNode<T> current = front;

            // This for loop will go from 0 upto the index of the node,
            // the comes right before the node indicated by the index.
            for (int i = 0; i < (index - 1); i++) {

                // Set the current reference to point to the node after current.
                current = current.getLink();
            }

            // After the for loop, current will refer to the node
            // right before the index we want to insert at.
            // Then we say that the newNode will link to the node
            // that comes after the current node.

            // This means that the newNode is the nth
            // (where n is the specified index) node in the chain,
            // and the node that used to be at the nth index is now at
            // the n+1th index and the node that used to be at the n+1th
            // index is now at the n+2th index and so on...
            newNode.setLink(current.getLink());

            // We set the link of the node that used to be the n-1th node
            // to the new Node to finalize the newNode at the nth position.
            current.setLink(newNode);
        }

        // We increment numElements to indicate one more element was added.
        numElements++;
    }

    public T set(int index, T newElement) {
        // Throws IndexOutOfBoundsException if passed an index argument
        // such that index < 0 or index >= size().
        // Otherwise, replaces element on this list at position index with
        // newElement and returns the replaced element.

        // If the provided index is out of bounds (is either less than 0,
        // or greater than or equal to the current size of the list
        // (which is the highest index + 1) )...
        if ((index < 0) || (index >= size())) {
            // Throw an exception to indicate the invalid index!
            throw new IndexOutOfBoundsException("Illegal index of " + index +
                    " passed to LBList set method.\n");
        }

        // Creating a new node and initialize it to the front.
        // This node will iterate through the linked list.
        LLNode<T> current = front;

        // This for loop will go from 0 upto the node at the nth position,
        // where n is the index the client passed.
        for (int i = 0; i < index; i++) {

            // Set the current reference to point to the node after current.
            current = current.getLink();
        }

        // After the for loop, current will refer to the node at the
        // nth position (where n is the index passed by the client).
        // This is the node that we have to set!

        // Store the information of the current node in a temp variable.
        T temp = current.getInfo();
        // Overwrite the info in the current node based on the element
        // passed in by the client.
        current.setInfo(newElement);
        // Return the original information that was in the nth node,
        // before we overwrited the information.
        return temp;
    }

    public T get(int index) {
        // Throws IndexOutOfBoundsException if passed an index argument
        // such that index < 0 or index >= size().
        // Otherwise, returns the element on this list at position index.

        // If the provided index is out of bounds (is either less than 0,
        // or greater than or equal to the current size of the list
        // (which is the highest index + 1) )...
        if ((index < 0) || (index >= size())) {
            // Throw an exception to indicate the invalid index!
            throw new IndexOutOfBoundsException("Illegal index of " + index +
                    " passed to LBList set method.\n");
        }

        // Creating a new node and initialize it to the front.
        // This node will iterate through the linked list.
        LLNode<T> current = front;

        // This for loop will go from 0 upto the node at the nth position,
        // where n is the index the client passed.
        for (int i = 0; i < index; i++) {

            // Set the current reference to point to the node after current.
            current = current.getLink();
        }

        // After the for loop, current will refer to the node at the
        // nth position (where n is the index passed by the client).
        // This is the node that we have to get the info from.

        // We will return the information of the nth node,
        // where n is the index the client passed.
        return current.getInfo();
    }

    public int indexOf(T target) {
        // If this list contains an element e such that e.equals(target),
        // then returns the index of the first such element.
        // Otherwise, returns -1.

        // The find method sets the value of the found variable
        // based on if the target element was found in some node in the list.
        // More importantly for this method, the find method also
        // sets the value of targetIndex which is the index at which
        // the match is located.
        find(target);

        // If we found a match...
        if (found) {
            // Return the targetIndex, which is the position
            // of the matching node in the chain of nodes.
            return targetIndex;
        } else { // Otherwise...

            // Return -1, (which is an invalid index) to indicate
            // that the target value was not found.
            return -1;
        }

    }

    public T remove(int index) {
        // Throws IndexOutOfBoundsException if passed an index argument
        // such that index < 0 or index >= size().
        // Otherwise, removes element on this list at position index and
        // returns the removed element; all current elements at positions
        // higher than that index have 1 subtracted from their position.

        // If the provided index is out of bounds (is either less than 0,
        // or greater than or equal to the current size of the list
        // which is the highest index + 1) )...
        if ((index < 0) || (index >= size())) {
            throw new IndexOutOfBoundsException("Illegal index of " + index +
                    " passed to LBList remove method.\n");
        }

        // Creating a reference variable of type T that will
        // hold the information that will be returned.
        T temp;

        // If the index parameter is 0
        // then we know we are removing the front node...
        if (index == 0) {

            // Set temp to the information of the node
            // we are removing (the current front node).
            temp = front.getInfo();

            // Dereference (even if partially so) the front by redefining
            // the front to be the node after
            // the current front node.
            front = front.getLink();

            // If the numElements is 1
            // then we know that we are
            // trying to remove the only
            // node in the list...
            if (numElements == 1) {

                // If we removed the only node
                // in the list, that means front is
                // null and the rear must also be set to
                // null. (without this step rear would still be referencing
                // the old front node).
                // This step totally dereferences the only node
                // that was in the list.
                rear = null;
            }

        } else { // Otherwise (if the index != 0 but is valid)...

            // Creating a new node and initialize it to the front.
            // This node will iterate through the linked list.
            LLNode<T> current = front;

            // This for loop will go from 0 upto the node at the n-1th position,
            // where n is the index the client passed.
            for (int i = 0; i < (index - 1); i++) {

                // Set the current reference to point to the node after current.
                current = current.getLink();
            }

            // After the for loop, current will refer to the node at the
            // nth position (where n is the index passed by the client).
            // This is the node the comes before the node we have to remove.

            // We set up a new tempInfo variable to store the information
            // of the node we plan on removing (the node at the nth index).
            temp = current.getLink().getInfo();

            // If the node after the current node is the rear
            // (if the node we want to remove is the last node)...
            if (current.getLink() == rear) {

                // We just update the reference of the rear to point to
                // the current node, which effectively dereferences the
                // old rear node (which was the node we wanted to remove).
                rear = current;

            }

            // We set the link of the current node, which comes before the node
            // we want to dereference, to the node that comes 2 links after the
            // current node (this is the node that comes AFTER the node we want
            // to remove). This dereferences the node that current.getLink()
            // pointed to, effectively removing the node at the nth index.
            current.setLink(current.getLink().getLink());
        }

        // We decrement numElements to indicate removing a single node.
        numElements--;

        // We return the temp variable, which contains
        // the information of the node we removed.
        return temp;
    }

    // #endregion

    // #region iterator() method

    public Iterator<T> iterator()
    // Returns an Iterator over this list.
    {
        return new Iterator<T>() {
            private LLNode<T> prevPos = null; // node before node just returned
            private LLNode<T> currPos = null; // node just returned
            private LLNode<T> nextPos = front; // next node to return

            public boolean hasNext()
            // Returns true if the iteration has more elements; otherwise returns false.
            {
                return (nextPos != null);
            }

            public T next()
            // Returns the next element in the iteration.
            // Throws NoSuchElementException - if the iteration has no more elements
            {
                if (!hasNext())
                    throw new IndexOutOfBoundsException("Illegal invocation of next " +
                            " in LBList iterator.\n");

                T hold = nextPos.getInfo(); // holds info for return
                if (currPos != null)
                    prevPos = currPos; // in case element was removed
                currPos = nextPos;
                nextPos = nextPos.getLink();
                return hold;
            }

            public void remove()
            // Removes from the underlying representation the last element returned
            // by this iterator. This method should be called only once per call to
            // next(). The behavior of an iterator is unspecified if the underlying
            // representation is modified while the iteration is in progress in any
            // way other than by calling this method.
            {
                if (currPos == null) // there is no last element returned by the iterator
                    return;
                else {
                    if (prevPos == null) // removing front element
                    {
                        front = nextPos;
                        currPos = null;
                        if (front == null) // removed only element
                            rear = null;
                    } else {
                        prevPos.setLink(nextPos);
                        currPos = null;
                    }
                    numElements--;
                }
            }
        };
    }


    // #endregion

}
