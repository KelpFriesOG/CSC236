# The Queue ADT

If stacks were LIFO (Last in, First out) structures then you can think of 
- **queues as FIFO (First in, first out) structures.**
- **Arrays or linked lists can be used to implement queues.**
- We will explore how to use arrays to created **unbounded queues** as well.
- **Queues may also be applied to safely take advantage of parallel execution within Java to improve performance!**

## The Queue

A queue is an access-controlled group of elements in which new **elements are added from the "rear" and elements are removed from the other end, the "front".**

- Use case: simulate cars passing through various stages of a car wash. Cars go in one end and come out the other end. **The first car to go in will be the first car to come out!**

### Operations of Queues

- **Enqueue**: adding element to rear of the queue.
- **Dequeue**: removing element from front of queue.

The enqueue operation is sometimes simplified to *enq* or called **enque, add, or insert**. Whereas the dequeue operation may be simplified to *deq* or called **deque, remove, or serve**.

---

### Using Queues

- An operating system often maintains a queue of process that are ready to execute or are waiting for a particular event to trigger them. 
- A **buffer** is a holding area which stores message that are being transferred between two processes, programs, or systems. **It is often implemented as a queue, where the first message that enters the buffer is the first message that is processed and delivered to the receieving entity.**
- Other applications store requests before processing such as ticket vendors which use queues to manage requests.
- Software queues often model real world queues such as queues to get into a carnival, a theatre, or any other queue.

---

## The Queue Interface

Aside from the enqueue and dequeue operations, we also provide additional methods such as size(), isEmpty(), and isFull().

- **The size of a queue may be important because it can indicate how long an element will remain in the queue.**
- isEmpty() and isFull() methods can be used to avoid removing from an empty queue or inserting into a full queue.
- **The enqueue and dequeue operations may throw the QueueOverflow and QueueUnderflow exceptions respectively based on the state of the array.**
- We create a QueueInterface interface that will define only the signatures (not the bodies) of these methods!


### Example Use

---

## Array-Based Queue Implementation

### The ArrayBoundedQueue Class

---

### The ArrayUnboundedQueue Class

---

## Interactive Test Driver

### The General Approach

---

### A Test Driver for the ArrayBoundedQueue Class

---

## Link-Based Queue Implementations

### The Enqueue Operation

---

### The Dequeue Operation

---

### A Circular Linked Queue Design

---

### Comparing Queue Implementations

---

## Application: Palindromes

### The Palindrome Class

---

### The Applications

---

## Queue Variations

### Exceptional Situations

---

### The GlassQueue

---

### The Double-Ended Queue

---

### Doubly Linked Lists

---

### Java's Collection Framework Queue/Deque

---

## Application: Average Waiting Time

### Discussion and Examples

---

### The Customer Class

---

### The Simulation

---

## Concurrency, Interference, and Synchronization

### The Counter Class

---

### Java Threads

---

### Interference

---

### Synchronization

---

### A Synchronized Queue

---

### Concurrency and the Java Collection Classes