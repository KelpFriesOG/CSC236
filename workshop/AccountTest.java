package workshop;

import chapter2.LLNode;

public class AccountTest {

    public static void main(String[] args) {

        LLNode<Integer> head = null;
        LLNode<Integer> current = head;

        for (int i = 5; i > 0; i--) {

            if (head == null) {
                head = new LLNode<Integer>(i);
                current = head;
            } else {
                current.setLink(new LLNode<Integer>(i));
                current = current.getLink();
            }

        }

        iterPrintList(head);

    }

    static void iterPrintList(LLNode<Integer> listRef) {

        LLNode<Integer> current = listRef;

        while (current != null) {
            System.out.print(current.getInfo() + " ");
            current = current.getLink();
        }
    }

}
