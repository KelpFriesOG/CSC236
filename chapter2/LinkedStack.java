
public class LinkedStack<T> implements StackInterface<T> {

    protected LLNode<T> top;

    public LinkedStack() {
        top = null;
    }

    public LinkedStack(T top) {
        this.top = new LLNode<T>(top);
    }

    @Override
    public void push(T element) throws StackOverflowException {
        LLNode<T> newNode = new LLNode<T>(element);
        newNode.setLink(top);
        top = newNode;
    }

    @Override
    public void pop() throws StackUnderflowException {
        if (!isEmpty()) {
            top = top.getLink();
        } else {
            throw new StackUnderflowException("The stack is empty, cannot pop any elements!");
        }

    }

    @Override
    public T top() throws StackUnderflowException {
        if (!isEmpty()) {
            return top.getInfo();
        } else {
            throw new StackUnderflowException("The stack is empty, cannot look at any elements!");
        }

    }

    @Override
    public boolean isFull() {
        return false;
        // A linked list is never filled up, it can always keep growing!
    }

    @Override
    public boolean isEmpty() {
        return top == null;
        // If the top of the linked list is null, then the list itself is null
        // as a whole!
    }

}
