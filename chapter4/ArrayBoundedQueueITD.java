<<<<<<< HEAD
package chapter4;

import java.util.Scanner;

public class ArrayBoundedQueueITD {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the capacity you would like the queue to have: ");
        int capacity = input.nextInt();

        QueueInterface<String> myQueue= new ArrayBoundedQueue<>(capacity);

        System.out.println("Now entering the interactive environment," 
        + "type X if you would like to exit!");

        String resp = "_";
        
        while(!resp.equals("X")){

            System.out.println("Type 0 to enqueue an element.");
            System.out.println("Type 1 to dequeue an element.");

            resp = input.next();
            input.nextLine();

            if(resp.equals("X")){
                continue;
            }

            if(!resp.equals("0") && !resp.equals("1")){
                System.out.println("Invalid option, try again!");
                continue;
            }

            if(resp.equals("0")){
                
                System.out.println("Enter the element you would like to enqueue (can be any String): ");
                String value = input.nextLine();

                try{

                    myQueue.enqueue(value);
                    System.out.println("Successfully enqueued the element to the end of the queue!");

                } catch(QueueOverflowException e){
                    
                    System.out.println("Could not add element, queue is full and holds: "+myQueue.size()+" elements!");
                
                }

            } else {

                try {
                    
                    System.out.println("Attempting to dequeue an element from the queue...");
                    String value = myQueue.dequeue();
                    System.out.println("Success! The removed value was: '"+value+"' ");
                    
                } catch (QueueUnderflowException e) {

                    System.out.println("Could not remove an element, queue was empty!");
                
                }
                


            }

        }

        System.out.println("Exited the interactive environment!");
        System.out.println("The final state of the queue: ");
        System.out.println(myQueue.toString());


        input.close();


    }
}
=======
package chapter4;

import java.util.Scanner;

public class ArrayBoundedQueueITD {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the capacity you would like the queue to have: ");
        int capacity = input.nextInt();

        QueueInterface<String> myQueue= new ArrayBoundedQueue<>(capacity);

        System.out.println("Now entering the interactive environment," 
        + "type X if you would like to exit!");

        String resp = "_";
        
        while(!resp.equals("X")){

            System.out.println("Type 0 to enqueue an element.");
            System.out.println("Type 1 to dequeue an element.");

            resp = input.next();
            input.nextLine();

            if(resp.equals("X")){
                continue;
            }

            if(!resp.equals("0") && !resp.equals("1")){
                System.out.println("Invalid option, try again!");
                continue;
            }

            if(resp.equals("0")){
                
                System.out.println("Enter the element you would like to enqueue (can be any String): ");
                String value = input.nextLine();

                try{

                    myQueue.enqueue(value);
                    System.out.println("Successfully enqueued the element to the end of the queue!");

                } catch(QueueOverflowException e){
                    
                    System.out.println("Could not add element, queue is full and holds: "+myQueue.size()+" elements!");
                
                }

            } else {

                try {
                    
                    System.out.println("Attempting to dequeue an element from the queue...");
                    String value = myQueue.dequeue();
                    System.out.println("Success! The removed value was: '"+value+"' ");
                    
                } catch (QueueUnderflowException e) {

                    System.out.println("Could not remove an element, queue was empty!");
                
                }
                


            }

        }

        System.out.println("Exited the interactive environment!");
        System.out.println("The final state of the queue: ");
        System.out.println(myQueue.toString());


        input.close();


    }
}
>>>>>>> bdcc0af49118df229a27ac0358b590542ba35152
