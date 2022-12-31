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

## When to use a Recursive Solution

