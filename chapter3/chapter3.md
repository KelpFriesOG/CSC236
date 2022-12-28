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
        
    void recPrintList(LLNode<String> listRef){    
        
        current = listRef;

        while(current != null){
            System.out.println(current.getInfo());
            current = current.next();
        }
    }

    OR

    void recPrintList(LLNode<String> listRef){

        if(listRef != null){
            System.out.println(listRef.getInfo());
            recPrintList(listRef.getLink());
        }
    }
