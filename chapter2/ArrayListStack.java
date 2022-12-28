
import java.util.ArrayList;

public class ArrayListStack<T> implements StackInterface<T> {

    protected ArrayList<T> elements;

    public ArrayListStack() {
        elements = new ArrayList<T>();
    }

    @Override
    public void push(T element) {
        elements.add(element);
    }

    @Override
    public void pop() throws StackUnderflowException {
        if (!isEmpty()) {
            elements.remove(elements.size() - 1);
        } else {
            throw new StackUnderflowException("The stack is empty, cannot pop any elements!");
        }
    }

    @Override
    public T top() throws StackUnderflowException {
        if (!isEmpty()) {
            T value = elements.get(elements.size() - 1);
            return value;
        } else {
            throw new StackUnderflowException("The stack is empty, cannot look at any elements!");
        }
    }

    @Override
    public boolean isFull() {
        return false;
        // An arraylist expands in theory forever, i.e. it can never be full!
    }

    @Override
    public boolean isEmpty() {
        return elements.size() == 0;
        // The arraylist is empty if its size is 0, i.e. it has no elements!
    }

}
