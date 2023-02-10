package workshop;

public class LinearLinkedList<T> {

    protected Node<T> head;
    protected int size;

    public Node<T> find(T findMe) {

        Node<T> temp = head;

        while (temp != null) {
            if (temp.info.equals(findMe)) {
                return temp;
            } else {
                temp = temp.link;
            }
        }

        return temp;
    }

    public void addAll(T[] items) {

        // Start looking at the head node.
        // Current will keep track of the node
        // we are on.
        Node<T> current = head;

        // Go through a while loop until
        // you reach the node at the end
        // of the current linked list.
        while (current.link != null) {
            current = current.link;
        }

        // Go through a while loop that iterates
        // through all the elements of the
        // items array
        for (int i = 0; i < items.length; i++) {
            // set the link of the current node
            // to a new node which is given a value,
            // based on the value at i in the items array.
            current.link = new Node<T>(items[i], null);

            // Move to the next node (the node we created)
            // then in the next iteration this node's
            // link will change to be a node that has
            // the value of the i+1th element in the items
            // array.
            current = current.link;
        }

    }

}
