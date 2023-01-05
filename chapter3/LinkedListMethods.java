public class LinkedListMethods {

    // #region List Size methods

    int recListSize(LLNode<String> listRef) {

        if (listRef == null) {
            return 0;
        } else {
            return recListSize(listRef.getLink()) + 1;
        }
    }

    int iterListSize(LLNode<String> listRef) {

        int size = 0;

        while (listRef != null) {
            size = size + 1;
            listRef.getLink();
        }

        return size;

    }

    // #endregion

    // #region Print List methods

    void recPrintList(LLNode<String> listRef) {

        if (listRef != null) {
            System.out.println(listRef.getInfo());
            recPrintList(listRef.getLink());
        }
    }

    void iterPrintList(LLNode<String> listRef) {

        LLNode<String> current = listRef;

        while (current != null) {
            System.out.println(current.getInfo());
            current = current.getLink();
        }
    }

    // #endregion

    // #region Reverse Print List methods

    void recRevPrintList(LLNode<String> listRef) {

        if (listRef != null) {

            recRevPrintList(listRef.getLink());
            System.out.println(listRef.getInfo());

        }

    }

    void iterRevPrintList(LLNode<String> listRef) {

        StackInterface<String> stack = new LinkedStack<String>();

        // Push each element into the stack
        while (listRef != null) {

            try {
                stack.push(listRef.getInfo());
            } catch (StackOverflowException e) {
                e.printStackTrace();
            }

            listRef = listRef.getLink();
        }

        // Read and pop elements from the top of the stack
        // until stack is empty.
        while (!stack.isEmpty()) {
            System.out.println(stack.top());
            stack.pop();
        }

    }

    // #endregion

    // #region Recursive Insert at end

    void incorrectRecInsertEnd(String newInfo, LLNode<String> listRef) {

        if (listRef.getLink() == null) {
            listRef.setLink(new LLNode<String>(newInfo));
        } else {
            recInsertEnd(newInfo, listRef.getLink());
        }
    }

    LLNode<String> recInsertEnd(String newInfo, LLNode<String> listRef) {

        if (listRef != null) {
            listRef.setLink(recInsertEnd(newInfo, listRef.getLink()));
        } else {
            listRef = new LLNode<String>(newInfo);
        }

        return listRef;
    }

    // #endregion

}
