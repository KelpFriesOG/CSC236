# Recursion

## Definitions, Algorithms, and Programs

  Recursion is the process of approaching a problem by creating **smaller and smaller versions of the original problem through self-calls (recursive cases) which eventually build back up to the solution of the original problem** when well defined base cases are hit.

- A **recursive definition** is a definition in which something is defined in terms of smaller versions of itself.

- The **base case is a case where the answer is directly known without further recursion**

- The **recursive or general case is a case for which the answer is expressed in terms of a smaller version(s) of itself**

- It is **crucial that the recursive call's argument is different from the original call. Otherwise the recursion would continue indefinitely.**

### Ex. Factorials

n! = 
- **1 IF n = 0**
- **n * (n - 1)! IF n > 0**

4! = 4 * 3! (recursive case)

3! = 3 * 2! (recursive case)

2! = 2 * 1! (recursive case)

1! = 1 * 0! (recursive case)

0! = 1 (base case)

After we perform recursion upto the base case, **we can build up our solution by discarding each call!**

- If you imagine each call as cards put onto the stack, here we are going to evaluate each card and pop the card at top of the stack each time.

- **At each step we pop the top of the stack after using it.**

1! = 1 * 0! = 1 * 1 based on the top card.

2! = 2 * 1! = 2 * 1 * 1 based on 1! card (top card after previous step)

3! = 3 * 2! = 3 * 2 * 1 * 1 based on 2! card (top card after previous step)

4! = 4 * 3! = 4 * 3 * 2 * 1 * 1 based on 3! card (top card after previous step)

Now the stack only has one card, which is our final result.

Psuedocode:

    def factorial(n):
        if(n == 0): # base case
            return 1
        else: # recursive case
            return (n * factorial(n-1))
            # recursive call -> factorial(n-1)

- The recursive call's argument, in this case n-1, is different from the original call. Otherwise the recursion would continue indefinitely.

---
### Recursive Programs

- In other words, the **base case must be hit at some point in the recursive calls to prevent infinite recursion!**

- **Direct recursion is recursion in which a method directly calls itself.**

- Indirect recursion is recursion in which a chain of two or more method calls return to the method that originated the chain.

- **Indirect recursion involves more than one method.** Method A may call method B which may call method A, as long as the call return back to method A, this is indirect recursion.

---

### Iterative Solution for Factorial

- Often times, although recursion can be an elegant solution, it is not particularly efficent.

- **Iterative solutions are often much faster. We utilize the recursive algorithm for factorials because it is easy to visualize.**

- In practice **we would never want to use the recursive factorial algorithm as a more straightforward and quicker iterative solution exists.**

Pseudocode:

    def factorial2(n):
        
        int result = 1;

        while(n != 0):
            result = result * n
            n = n-1

        return result

- The iterative solution has two local variables ( result and n). The iterative solution has none.

- Recursive solutions tend to have less local variables then their iterative counterpart.

- **Starting a new iteration of a loop is faster than calling a method and allocating memory on the stack.** Iterative is faster!

- Sometimes the apparant time complexity of the iterative solution is faster than the recursive solution. Although this is not the case here, it is important to consider.

---

## The 3 Questions

### Verifying Recursive Algorithms
1. *The Base-Case Question*
- Is there a nonrecursive way out of the algorithm, and does the algorithm work correctly and actually hit the base case?
2. *The Smaller-Caller Question*
- Does each recursive call to the algorithm involve a smaller case of the original problem which always leads to the base case eventually?
3. *The General-Case Question*
- Assuming the recursive calls to the smaller cases work correctly, does the algorithm work correctly for the general case?

  Lets evaluate these questions for the recursive factorial algorithm.

1. The base case occurs when n = 0, and the algorithm returns the value of 1, which is the correct value of 0! by definition. **YES**
2. The parameter is n and the recursive call passes the argument n-1. Therefore each subsequent recursive call sends a smaller value until we hit the base case of n = 0. **YES**
3. Assuming the recursive call factorial (n-1) will return the correct value of (n-1)!, then the return statement computes n(n-1)!. This is the definition of a factorial, so we know that the algorithm works in the general case assuming it works in the smaller case. **YES**
---

### Determining Input Constraints

Keep in mind that the **preconditions of this algorithm is that the initial input must be greater than or equal to 0.**

- We can typically use the 3 question analysis to determine our input constraints. **The smaller calls should produce arguments that close in the base case and do not move away from it.**

- For the factorial algorithm the initial input of -1 would execute factorial(-2) which would execute factorial(-3) and onwards without ever hitting a base case.

- **Therefore the preconditions of our factorial argument are that n > 0. We should enforce these constraints either in the method itself or any other method that uses the recursive method.**
---

### Writing Recursive Methods

1. Get an exact definition of the problem to be solved.
2. Determine the size of the problem. On the intial call to the method, the size of the whole problem is expressed in the value(s) of the argument(s).
3. Identify and solve the base cases in which the problem can be expressed non-recursively.
4. Identify and solve the recursive cases correctly in terms of a smaller version of the same call.

- For the factorial problem, the size of the problem is the number of values to be multiplied, n. And the base case occurs when n = 0, and the recursive case occurs when n > 0. The base case should return 1, the recursive case should be defined and return as n*factorial(n-1)
---

### Debugging Recursive Methods

- Recursive methods can often be confusing to debug due to the nested calls to themselves.

- **If recursion occurs indefinitly, your system will throw an error message that indicates that the system has run out of space.**(i.e. StackOverflowException)

- Recursive methods require plenty of memory (space) overhead. Just because a recursive method works for smaller value, does not guarantee it works for all values.
  
- Why? : **The space consumed by the recursive calls, especially on massive values may be larger than what your system can handle.**
---

## Recursive Processing of Arrays

- Many array related problems can be solved via recursion **because a subsection of an array (a subarray) is a array in itself.** We may be able to use a recursive approach by dividing up or focusing on different subarrays at each recursive call.

### Binary Search

The general approach used for binary search is to examine the midpoint of our sorted array and to determine if the element we are looking for is greater or less than that value.

- We can then eliminate half of the array from consideration depending on if the element is less than or greater than our midpoint.

- We operate on either the left or right subarray after this comparison, and determine the midpoint of our new array and do the same comparison.

- This is **recursion**. And this approach is an example of **"decrease and conquer"** algorithm design. We solve the problem by decreasing the size of the array subsection at each stage or call.

Pseudocode:

        int binarySearch(int target, int first, int last){

            // Precondition: first and last are legal indices of values array and start as 0 and values.length-1 respectively to search through entire array. The original values array must also be sorted in ascending order.

            midpoint = (first + last) / 2

            if(first > last){ 
                // Base case if element was not found!
                return -1;
            }

            if(target < values[midpoint]){
                // Recursive case if element is smaller than midpoint.
                return binarySearch(target,first,mid-1);
            } else if(target > values[midpoint]){
                // Recursive case if element is greater than midpoint.
                return binarySearch(target, mid+1, last);
            } else{
                // Base case if midpoint == target. return 
                return midpoint index (where the element was found).
            }

        }

The complexity for the binarySearch algorithm is O(log(N)) (log has a base of 2).
- The iterative version of this algorithm is not much different than the recursive version conceptually.

---
## Recursive Processing of Linked Lists

- In this section we discuss the recursive processing of linked lists as opposed to traditional arrays.

### Recursive Nature of Linked Lists

A linked list is a recursive structure, even more so than a traditional array. 

- A linked list node is **self-referential**, a linked list's node contains a reference to another linked list node.

- Self-referential classes are closely related to recursion. In a sense, these clases are recursive because **we can derive a next node from one node, and then get the next node of that node, and so forth.**

`current = current.getLink().getLink();`

The code above gets replaces the reference of the current node with a reference of the node two spots ahead in the llinked list. We have to *recursively* call the getLink() method in a sense.

Suppose we wanted to print the information in each node of a LinkedList until we hit the end.
- We know we hit the end of the linked list structure when the node we are on is null.
- We can use a simple while loop to go through all the elements.
- OR we could use an equivalent recursive solution.

Pseudocode:
        
    void iterPrintList(LLNode<String> listRef){    
        
        current = listRef;

        while(current != null){
            System.out.println(current.getInfo());
            current = current.getLink();
        }
    }

    OR

    void recPrintList(LLNode<String> listRef){

        if(listRef != null){
            System.out.println(listRef.getInfo());
            recPrintList(listRef.getLink());
        }
    }

**The same patterns of moving through a linked list, either via a while loop or via recursion,** can be used for other applications of traversing a linked list.

Suppose you wanted to get the size of a linked list:

    int iterListSize(LLNode<String> listRef){

        int size = 0;

        while (listRef != null){
            size = size+1;
            listRef.getLink();
        }

        return size;

    }

    OR

    int recListSize(LLNode<String> listRef){

        if (listRef == null){
            return 0;
        } else{
            return recListSize(listRef.getLink())+1;
        }

    }
    

This example illustrates how an iterative version often needs extra instance variables such as "size" to keep track of and change a value before returning it.

- **The recursive implementation's intermeadiate values are generated by the recursive calls of the return statements, so a local variable is unneeded.**

What if we wanted to print the contents of the linked list from the end to the beginning (in reverse order), how would we approach that?

- Here recursion is quicker and simpler to implement than iteration.
- If the list is empty then we do nothing.
- Otherwise we say: reverse the information in everything after the element that we are on (recursive call).
- The recursive call for our code will happen one line before the printing.
- In other words **the recursion will go all the way through before printing anything. Then the deepest call will start by printing the end of the list, then the call prior to that will print the second last element and so on and so forth!**

Pseudocode:

    void recRevPrintList(LLNode<String> listRef){

        if (listRef != null){

            recRevPrintList(listRef.getLink());
            System.out.println(listRef.getInfo());
        
        }

    }

**The common iterative version of this method is not pretty.**

- One way to do so would be to traverse the list and count the elements. Then traverse to the last (n th) element and print it, then traverse to the second to last (n-1 th) element and print that, and then to the third to last element (n - 2 th) element and print that and so forth.
- Complexity of this approach: O(n^2)

- **A more easy solution would be to traverse the list only once and then to push each element onto a stack.** Now to print everything in reverse you would just have to peek and pop elements from the top of the stack until its empty.
- Complexity of this approach: O(n).

Stacks are some of the best tools to replace recursion!

---

### Transforming a Linked List

- What if we wanted to do more than just read information from a linked list?
- **How would we approach adding, manipulating, or removing elements via recursion?**

If we wanted to insert an element to the end of the linked list, we could simply use a while loop to get to the end of the list, or we could use recursion.
- With recursion our base case would occur when we reach the end of the linked list, in other words it would happen when the getLink() method returns a null value.
- Our recursive case would happen in any other situation.

Based on that:

    void recInsertEnd(String newInfo, LLNode<String> listRef){
        
        if (listRef.getLink() == null){
            listRef.setLink(new LLNode<String>(newInfo));
        } else{
            recInsertEnd(newInfo, listRef.getLink());
        }
    }

But while this code will work for most cases...
- There is an edge case where it fails!

When dealing with nulls it is important to know that a method cannot be called on a null reference.

-**Suppose I feed this method with a listRef parameter that points to an empty linked list.** What could happen?
- Well if the listRef is null, then the if condition does not work!

`if(listRef.getLink() == null) ...`

- **You cannot invoke the getLink method on a null object and a null pointer exception will be thrown.**
- Although we can use methods to access what listRef refers to, **we cannot change the underlying list in itself** by saying listRef = ...
- **This is because the LLNode<String> listRef parameter can and is only passed by value.**
- listRef is an alias to the underlying list that exists external to the method. 

![alt text](PassByValueIMG.png "Title")

- **The	 parameter	listRef	is	an	
alias	of	 the	argument	myList	and	as	an	alias	it	lets	us	access	whatever	myList	is	referencing	(the	list),	but	it	does	not	give	us	access	to	myList	itself.**

- **The solution is to reconstruct  the method so that it checks if the passed reference is null and returns a changed reference based on the insertion after all the recursion is finished!**

In other words :

    LLNode<String> recInsertEnd(String newInfo, LLNode<String> listRef){
        
        if (listRef != null){
            listRef.setLink(recInsertEnd(newInfo, listRef.getLink()));
        } else{
           listRef = new LLNode<String>(newInfo);
        }

        return listRef;
    }

- If we run this code by passing an intially null reference, then the if statement (base case) triggers, sets up the new element, and then we return the reference after it has been changed.

- If we run this code for a linked list of 2 elements, here is what the trace would look like! **THIS IS CRUCIAL TO UNDERSTANDING RECURSION**

![alt text](RecInsertionTRACE_1.png "Title")


The initial call happens on a reference variable pointing to the first element of the linked list. Since this element is not null, we go through the if statement and set the link of this node to whatever changed linked list will be returned via the recursive call.
![alt text](RecInsertionTRACE_2.png "Title")


The next call happens on a reference variable pointing to the second element of the linked list. Since this element is not null, we go through the if statement and set the link of the second element to whatever will be returned by the recursive call on the "third" element.
![alt text](RecInsertionTRACE_3.png "Title")


This last recursive call happens on a reference variable pointing to a NULL value (since there is no third element). Now that we have a null value, we skip the if statement and go directly to the else statement. Here we create a new node in place of and reassign listRef such that it now has a value of this new node. It also returns this singleton Node of CAT back up to its calling method. In other words, the single Cat node is passed:

`Cat`

![alt text](RecInsertionTRACE_4.png "Title")


Now the recursion is over, but everything is now chained back up. So in the calling method from before takes this single Cat value and sets the link of its element (the second element: Bat) to whatever was returned: Cat.
Therefore Bat becomes linked to Cat, and the reference variable which points to this partial linked list is returned up to the calling method. The returned Bat node in actuality is a reference to the linked list:

`Bat -> Cat`

![alt text](RecInsertionTRACE_5.png "Title")


This calling method is our first call, at this point the listRef variable referred to the Ant element, so the if statement here will chain the Ant element with what was returned form the previous step. Creating a final linked list of:

`Ant -> Bat -> Cat`

![alt text](RecInsertionTRACE_6.png "Title")

This method then finally returns to the main method or wherever it originated from and returns a whole new linked list. In other words:

    recInsertEnd("Cat", myList);

**does nothing unless you reassign the myList to the result of this method.**

    myList = recInsertEnd("Cat", myList);

That makes the difference!

---
## Towers

Beware: Towers of Hanoi...(personally hate this one)

Imagine three pegs that hold stackable rings of differing diameters. When the game begins, all rings are on the first peg in order by size, with the smallest on top.

- **Goal:** Move the rings, one at a time, to the third peg.
  
Rules:

1) **A ring cannot be placed on top of one another ring that has a smaller diameter than itself.**
2) **The middle peg can be used as an auxilliary peg but must be empty at the beginning and end of the game.**
3) **The rings can only be moved 1 at a time.**

Is there a systematic algorithm to solve this to minimize the number of moves you take?

### Devising the Algorithm

The optimal solution is described below:
![alt text](TowersOfHanoiSOLUTION.png "Title")


- Since the goal is to move the entirety of the tower to the right peg, **our first sub-goal is to move the largest ring to the right peg.**

Lets define the rings as A, B, C, D where A is the largest ring, B is the second largest and so on.

1) Move ring D to the middle peg.
2) Move ring C to the right peg.
3) Move ring D from the middle peg to the right peg such that D is on top of C.
4) Move ring B to the middle peg.
5) Move ring D from the right peg to the left peg such that D is on top of A.
6) Move ring C from the right peg to the middle peg such that C is on top of B. Right peg is empty now.
7) Move ring D from left peg to middle peg such that the middle peg had D on C which is on B.
8) Move ring A from the left peg to the right peg.

Getting us to:
![alt text](TowersOfHanoi_8.png "Title")

- **Notice that it takes 7 total moves to move the three smallest rings from the left peg to the middle peg.**
- **Then it takes one more move to move the largest block to the right peg.** 

What remains is to move the three smallest rings from the middle peg to the right peg using the left peg as the auxillary peg.

- **The next seven moves are essentially the same process as the first ones we described, just with different beginning, ending, and auxillary pegs.**


- **We solve by first moving n-1 rings from the starting to the auxillary.**
- **Then we move the nth ring from the starting to the destination.**
- **Then we move the n-1 rings from the auxillary to the destination.**

---

### The Method

Based on the previously outlined steps there is a pretty straightforward solution to this problem.

Pseudocode:

    static void doTowers(int n, int startPeg, int auxPeg, int endPeg){

        if(n == 1){
            // Base case, we simply move one ring
            System.out.println("Move ring" + n + "from peg" + startPeg + " to peg " + endPeg);
        } else{
            // Recursive case

            // Move n-1 rings from starting peg to aux peg
            doTowers(n-1, startPeg, endPeg, auxPeg)

            // Move the nth ring from starting peg to ending peg
            System.out.println("Move ring " + n + " from peg " + startPeg + " to peg " + endPeg);

            // Move n-1 rings from the auxillary peg to ending peg.
            doTowers(n - 1, auxPeg, startPeg, endPeg);

        }

    }

This seems to good to be true. Which is why I personally hate this problem. But it works!

Lets examine the three questions:

1. Is there a non recursive way out of the method, and does the method work correctly for this base case?

If doTowers is passed an argument equal to 1 for n, it prints the single move, correctly recording the movement of a single ring from startPeg to endPeg and skips the else statement. No recursion is mad in this base call and this response is appropriate because there are no smaller rings to worry about and only one ring to move!

2. Does each recursive call to the method involve a smaller case of the original problem, inescapeably leasing to the base case?

Yes, because the method receives a ring count argument n and in its recursive calls passes the ring count argument n-1. The subseqent calls also pass a decremented value causing n to shrink until n = 1.

3. Assuming the recursive calls work correctly, does the method work in the general case?

Yes. The goal is to move n rings from the starting peg to the ending peg. The first recursive call within the method moves n-1 rings from the starting peg to the auxillary peg. Assuming the operation works correctly, we now have one ring left on the starting peg and the ending peg is empty. If the ending peg is empty we can move the ring on the starting peg to the ending peg (this ring will always be the largest ring initially). Then the second recursive call now moves the n-1 rings that are on the auxillary peg to the ending peg, placing them on top of the largest ring the was move in the first call.  If this transfer works correctly as assumed then we have all n rings on the ending peg.

---
### The Program

Sample Program I/O:

    Input the number of rings: 4
    Towers of Hanoi with 4 rings
    Move ring 1 from peg 1 to peg 2
    Move ring 2 from peg 1 to peg 3
    Move ring 1 from peg 2 to peg 3
    Move ring 3 from peg 1 to peg 2
    Move ring 1 from peg 3 to peg 1
    Move ring 2 from peg 3 to peg 2
    Move ring 1 from peg 1 to peg 2
    Move ring 4 from peg 1 to peg 3
    Move ring 1 from peg 2 to peg 3
    Move ring 2 from peg 2 to peg 1
    Move ring 1 from peg 3 to peg 1
    Move ring 3 from peg 2 to peg 3
    Move ring 1 from peg 1 to peg 2
    Move ring 2 from peg 1 to peg 3
    Move ring 1 from peg 2 to peg 3

WARNING: Adding 1 ring to the program doubles the number of steps and consequently the number of outputs. Meaning that having 16 rings would generate....a 10 MB output file.

---

## Fractals
(don't worry this will be short)

- Fractals are shapes that are recursively drawn and are informally defined as infinetly complex, never ending patterns.
- This means that zooming into a certain position on the fractal looks as if nothing has changed from the original picture. Because the pattern is identical on any scale.
- Recursion is the process that drives the creation of these fractals.

Fractals are used in computer graphics to generate realistic imagery, in engineering to build strong antenna, and by meteorologists to model weather, fluid dynamics, and coastline formations, and e.t.c.

### T-Square Fractal

- We have white square centered on a black canvas which is 1/4th the size of the canvas.
- We then draw four more white squares each centered at one of the corners of the original white square. Each of these new squares are 1/4th the size of the original square or 1/16th the size of the original canvas.
- We then draw 4 * 3, 12 more squares on each of the new square edges, each 1/4 of the size of the last drawn squares or 1/64th of the size of the original canvas.
- This can keep going for an indefinite amount of time and can be modeled via recursion.

![alt text](TSquare.png "Title")

The base case here is when the square's drawn size becomes indistinguishable from 0 for the computer.

I have included the TSquare class if you would like to try drawing this shape (main method is included in the class).

---

**Everything beyond this point was not on the syllabus I looked at, but if it seems intriguing, be my guest!**

## Removing Recursion

- This section considers two general techniques that are often use dto repolace recursion: eliminating tail recursion and direct use of a stack. 
- Understanding how recursion works allows us to better understand their non-recursive counterparts.

### How Recursion Works

A compiler turns a HLL (high level language) program into machine code and has two primary functions.
1. Reserve space for the program variables.
2. Translate the high-level executable statements into equivalent machine language statements.

- The compiler performs these tasks modularly for separate program subunits.

Lets use an example and analyze it:

    public class Kids{

        private static int countKids(int girlCount, int boyCount){
            
            int totalKids;
            totalKids = girlCount + boyCount;
            return(totalKids);

        }

        public static void main(String[] args){

            int numGirls;
            int numBoys;
            int numChildren;

            numGirls = 12;
            numBoys = 13;
            numChildren = countKids(numGirls, numBoys);

            System.out.println("Number of children is " + numChildren);

        }

    }

A compiler could create two separate machine code units
- One for the countKids method and one for the main method.
- Each unit includes space for its variables plus the sequence of machine language statement that implement its high level code.

Flow of our program:
- main --> countKids --> main

Machine code might be arranged like this:

    1. space for the main method variables
    2. main method code that initializes variables
    3. jump to the countKids method
    4. main method code that prints information
    5. exit
    6. space for the countKids method parameters and local variables
    7. the countKids method code
    8. return to the main program

**Static allocation like this is the simplest approach possible. But it does not support recursion.**

- The space for the countKids method is assigned to it at compile time. This simplification works if we assume that the countKids method is called once and then returns, **but it does not account for recursive calls that would require more memory to be allocated after the first call.**
- Where would the subsequent calls find space for their parameters and local variables? **You cannot allocate this space statically at compile time, because we DO NOT KNOW the NUMBER OF CALLS that will be made at compile time.**
- **A language that only supports static storage allocation cannot support recursion.**

**Dynamic storage allocation provides memory space for a method after its called.** **Local variables are not asccoiated with actual memory addresses until runtime.**

- The **activation record or stack frame** is the space in which a method's parameters, local variables and return address is stored when it is called.

- Each call to a method, including recursive calls, causes the Java run-time system to allocate additional memory space for a new activation record.
- Within the method, references to parameters and local variables use values in the record.
- When the method ends, the activation record space is released.

You can think of activation records as a stack of cards.

Suppose the main method calls proc1, and within proc1 another method, proc2, is called.

- The **system or run-time stack** is the structure that keeps track of activation records at run-time.

Illustration:

![alt text](ActivationRecordStack.png "Title")

As proc2 finishes, its activation record is released. Then proc1's record becomes the active one. The active record follows the LIFO rule. **The last method executed is the first method to finish its complete activation.**

When a method is invoked, its activation record is pushed onto the run-time stack.
- **Each nested recursive call adds one more record to the stack. If a method calls itself n times, then n+1 of that method's activation records appear on the runtime stack.**
- The number of these calls is known as the **depth of recursion**.

---

### Tail Call Elimination

What if a recursive call is the last action executed in a recursive method?
- The recursive call enables the activation record to be put on the runtime stack. When the call finishes executing, the run-time stack is popped and the previous values of the variables are restored.
- However since the recursive method was the last statement in the method, there is nothing more to execute and the method terminates without using the restored local values.
- We do not need recursion in this situation and we can eliminate this last "tail call"
- **Cases in which the recursive call is the last statement executed are called tail recursion.**
- Tail-recursion can always be replaced with iteration.

Suppose the factorial method:

    public static int factorial(int n){
        if (n == 0){
            return 1;
        } else{
            return n * factorial(n-1);
        }
    }

How do we tranform this tail recursive implementation to an iterative implementation?

- The first hint is the base case. This is what the value of some instance variable should start as.
- Intermeadiary values would be added to this variable via a for loop. 
- **retValue will be initialized to 1 based on the base case.**
- This would ensure that the iterative solution works correctly even if the loop is not initially entered.
- Each iteration of the while loop should correspond with the actions of one recursive call.
- Here we will multiply our immediate value, retValue, by the current value of n.
- **The value of n was decremented by 1 for each recursive call, which means we need to do the same decrementing for each iteration of the while loop.**
- The while loop represents our only recursive case, meaning that the program should remain in the loop until the base case is met. **Therefore the condition for the loop is the inverse of the base case, n != 0. With this case the loop continues executing until we hit the base case n == 0.**

private static int factorial(int n){

    int retValue = 1;

    while(n != 0){
        retValue = retValue * n;
        n = n - 1;
    }

    return retValue;

}

--- 

### Direct Use of a Stack

If the recursive call is not the last action executed in a recursive method, utilzing a simple loop substitute is not enough!

Ex:

    void recRevPrintList(LLNode<String> listRef){
        // Prints the contents of the listRef to standard output in reverse order.

        if(listRef != null){
            recRevPrintList(listRef.getLink());
            System.out.println(listRef.getInfo());
        }

    }

In situations such as this, the recursive call is not the last action which executes. **Here we can utilize a stack to obtain intermeadiate values that replace the stacking activation records that build up due to recursion.**

- We can traverse the list in a forward direction and put each element on the stack. After pushing all elements onto the stack, we can pop elements from the stack to print them out in reverse order!

Ex:

    void iterRevPrintList(LLNode<String> listRef){

        StackInterface<String> stack = new LinkedStack<String>();

        // Push each element into the stack
        while(listRef != null){
            stack.push(listRef.getInfo());
            listRef = listRef.getLink();
        }

        // Read and pop elements from the top of the stack
        // until stack is empty.
        while(!stack.isEmpty){
            System.out.println(stack.top());
            stack.pop();
        }

    }

    Note that the iterative implementation uses a programmer provided stack and requires significantly more code than its recursive counterpart.

- **The elegance of recursion is that it uses a stack implicitly and invisibly supplied by the system in the form of the activation record stack.**

---

## When to use a Recursive Solution

### Recursion Overhead

- A recursive solution to a problem is often more time and space costly than its iterative counterpart. (This is not always true but it is a rule of thumb).

A recursive solution requires **more processing time** to create and destroy activation records and requires **additional space to allocate to the runtime stack** which holds the activation records from prior calls. **All of this is considered time and space overhead.**

- The recursive implementation of the factorial method requires n + 1 method calls and pushes n+1 activation records onto the runtime stack for a value n.
- The **depth of recursion is O(n)**.
- **Creating and removing these records requires more time than a typical while loop and a single iterative call.**
- **Furthermore, the system may run into stack overflow (run out of memory) if too many nested calls are made due to a large initial value of n.**

---

### Inefficent Algorithms

A particular recursive solution may just be inherently inefficent. This may not be due to our implementation but rather reflects the poor quality of the algorithm itself.

Ex: Combinations

- Consider the problem of determining how many combinations of a certain size can be made out of a group of items. For instance, if we have a group of 20 students and want to get a subgroup of 5 members how many different, unique, subgroups are possible.

A mathematical recursive definition exists:

![alt text](RecCombinations.png "Title")

- If you only want a **subgroup of a single person then you can have n possible groups where n is the number of people in the group.**

- If you want to make **a subgroup which has the same size as the total number of people** in the original group, then well you only have **one possible subgroup**!

- **If none of these base cases are met then we use a recursive case.**

**C(g, m) = C(g-1, m-1)+C(g-1, m)**

Pseudocode:

    public static int combinations(int group, int members){

        if(members == 1){
            return group;
        }
        else if(members == group){
            return 1;
        }
        else{
            return combinations(group-1, members-1) + combinations(group-1, members);
        }

    }


Here is how the call for combinations(4, 3) would break down:

**combinations(4,3) = combinations(3, 2) + combinations(3, 3)**

We can further break down combinations(3,2)

- **combinations(3,2) = combinations(2,1) + combinations(2,2)**

Therefore:

**combinations(4,3) = combinations(2, 1) + combinations(2, 2) + combinations(3, 3)**

- **Each of these components is a base case so we can fully simply the final answer.**

combinations(4, 3) = 2 + 1 + 1 = **4**

Although this solution seems elegant for smaller problems, recursive solutions are often very inefficent because the number of recursive calls grows very quickly (not optimal time complexity).

Suppose the breakdown of combinations(6, 4)

![alt text](RecCombinations2.png "Title")

**There are a total of 19 recursive calls here as opposed to the 4 recursive calls requires to calculate combinations(4, 3).**

- If you look at the image you can see that a lot of values such as combinations(3,2) are calculated multiple times.
- combinations(4, 3) is calculated twice, and combinations(3, 2) is calculated three times as are combinations(2, 1) and combinations(2, 2).
  
- Later on we will learn about **dynamic programming**. This is a programming approach which **saves solutions to subproblems inside a separate data-stucture to avoid reculating the same subproblem multiple times.**
- **Of course the best scenario would be an iterative solution.**

For combinations there is an iterative solution!

C(g, m) = g!/(m! * (group - members)!)

A properly implemented iterative solution based on this formula is much more efficent than the recursive alternatives.

---

### Clarity

- For many problems a recursive solution is the most elegant and natural solution. Recursive programming limits the view of the programmer to the *tip of the iceberg* or just the surface level of the process.
- The activation records and internal stack take care of most of the underlying work!

**As computers get faster, have more memory, and become cheaper it is worthwhile to consider recursion to hide the true complexity of the program and the details of implementation.**

# The End!!