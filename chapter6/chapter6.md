# The List ADT

Lists are one of the most widely used ADTs in computer science becuase similar to queues, they translate directly into many real-life use case scenarios.

- **They are similar to stacks and queues in the sense that there is some sort of order imposed on the internal elements.**
- **However, unlike stacks and queues, there are no restrictions on adding, accessing, and removing elements from any location.**

## The List Interface

A list a linear collection of elements which share some linear relationship. 

- Each element in the list has a unique predeccesor and a unique succesor (with the exception of the first and last elements respectively).

- Each element has a position or index.

**In addition to supporting the familiar get(), contains(), add(), remove(), isFull(), and isEmpty() methods, a List will support more index related operations**.

- **An list cannot sustain any "holes", in other words, if we remove an element from the middle of the List, we must shift down the preceding elemnts to fill in the gap. The elements must be contiguous.**

- 

