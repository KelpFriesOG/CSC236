package chapter2;

public class Example {

    public static void main(String[] args) {

        LinkedStack<String> letters = new LinkedStack<>();

        for (int i = 65; i <= 90; i++) {
            letters.push("" + (char) i);
        }

        try {
            letters.pop();
        } catch (StackUnderflowException e) {
            System.err.println("Stack was empty");
            e.getMessage();
        } catch (Exception e) {

        }

        letters.toString();

    }

}
