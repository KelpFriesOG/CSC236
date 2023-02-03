# The Collection ADT

The Collection ADT provides the ability to store and retrieve information.

- Typical collection ADTs such as the Stack and the Queue allow restricted access and retrieval of data by allowing us to put and take out data from one of two ends of the structure.

- **The Collection ADT eases up on these restrictions!**

- **If we can store our objects in a sorted fashion, then this opens up possibilities of using algorithms such as the binary search which significantly decrease the time it takes to retrieve objects.**

- **In order to store data in a structured and sorted manner, we also need to ask our self how to compare two objects.** What does it mean for two objects to be equal, or for one to be greater or less than another object?

## The Collection Interface

Stacks and queues are useful in particular situations.
- **Stacks are most useful for accessing most recent data first!**
- **Queues are most useful for processing data on a first come, first serve basis!**

Restrictions on how we can insert and remove data into and from these structures ensures that we use them for properly.

But how about situations where you need to look up data? In other words, **what if you need to search for data based on some other information such as an ID?**

Ex. **Obtaining a bank account number based on some SSN number.**

**The external information that you search based on is often called a "key" across most computer science disciplines.**

- We need a data structure that stores information and allows us to retrieve it all based on some "key" (ex. ID) part of this information.

- The order in which we inserted an object has no effect on when we can retrieve it, we can always retrieve any object in the collection based on its contents and more specifically its key.

- **The equals() method from the Object class considers two objects to be equal if they are aliases of each other.**

Ex.

    Car a = new Car("Ferrari", "488 Italia", 450000);
    Car b = new Car("Ferrari", "488 Italia", 450000);

    // Are cars a and b equal to one another? In other words are they the same car?

    // If you are using the .equals() method from the Object class, NO!

    boolean isEquals = a.equals(b) // isEquals is false!

    // a and b are two distinct objects, so the Java compiler knows they are
    // not the same object, therefore they are not equal.

Ex.

    Car a = new Car("Lamborghini", "Huracan", 300000);
    Car b = a;

    // Are cars a and b equal to one another? In other words are they the same car?

    // If you use the .equals() method from the Object class, YES!

    boolean isEquals = a.equals(b) // isEquals is true!

    // Car a is a new car, but Car b is not a new car, it just points to Car a. In other words, both reference variables point to the same underlying object, i.e. they share the same memory location. Because of this, you can say that they do in fact refer to the same car object in memory!


- Hopefully with the examples you can see that the Object class only considers two objects to be equal if they are aliases of the same object, or aliases of each other.

- Usually **we want equality to be defined by the contents of the two objects, rather than being based on whether the two objects are aliases of the same memory location.**

In other words we want this:

Ex. 

    Car a = new Car("Ferrari", "488 Italia", 450000);
    Car b = new Car("Ferrari", "488 Italia", 450000);

    // Are cars a and b equal to one another? In other words are they the same type of car with the same attributes?

    // We want the answer to be YES

    boolean isEquals = a.equals(b) // isEquals should be TRUE!

**- We can accomplish this by overriding the equals method of the Object class with our own definition of equals() in the Car class!**

- **For now we will be using the String class for the collection. The built-in String class already has a equals() method that overrides the Object class's method. The String class's override method compares the contents of two strings!**

### Assumptions for Our Collections

We will implement a basic collection that allows for the addition, retrieval, and removal of elements.

- **Our collections will allow duplicate elements.**
- **Two elements are considered to be duplicates if**
`element1.equals(element2)` **returns true (remember that the .equals() method should be overriden and not the same one from the Object class!)**

- When we try to find an element that has a duplicate, any of the copies of the element may be returned (typically the first duplicate we find is returned).

- **Null elements cannot be used as arguments, this will not be enforced via a try-catch but will be a precondition for anyone trying to use the collection's methods. This removes the need to test for null pointers every time we try to use a method's arguments.**

- Other than that there are very minimal preconditions...

- We DO NOT require the remove operation to remove an item that is in the collection. Rather, if the element we are trying to remove is not found, we just return false to indicate that the operation failed!

**We follow an identical approach with all operations. If the operation is successful we return true, otherwise we return false.**

Ex. Trying to `add()` into a full collection will return false!

---

### The Interface

**Just like stacks and queues, our collections will be generic and therefore the interface will be generic as well.**

Take a look at the CollectionInterface.java class and read the comments to get a better understanding of each method and the functionality of a collection!

---

## Array-Based Collection Implementation

If a collection has N elements, we hold the element in the first N locations of the array.

- We maintain a constant **numElements to keep track of the number of elements in the array.**

Ex.

![alt link](ArrayBasedCollection_1.PNG "Title")

**When we add an element to the collection, we simply increment numElements and place the new element in the next empty slot.**

Ex.

![alt link](ArrayBasedCollection_2.PNG "Title")

What is a bit more difficult to understand is what happens when we remove an element.
- When we remove an element, we do not want to leave any gaps or holes in the array.**In other words if we remove the second element we do not want there to be "hole" in the array where there are still elements before and after the index where the element used to be.**

- To remedy this, **we could shift down the positions of all the elements after the index of the removed element.**  But this would be very expensive in terms of time. (worst case: n-1 shifts where n is the size of the array).

- **However, since order does not matter, we can simply take the last element in the array and use it to fill the hole in the array.**

Ex.

![alt link](ArrayBasedCollection_3.PNG "Title")

Check out the ArrayCollection class that implements the CollectionInterface to see how this is implemented. The next notes refer to this class.

The implementation should be pretty straightforward if you have already seen and implemented the array-based implementations the Stack and Queue ADTs.

- Similar to what occured with the Stack and Queue array-based implementations. We have to make an awkward cast to cast an array of objects into type T which generates a compiler warning.
- We cannot avoid this.

Two constructors can be used, one which creates a collection with a default capacity of 100, and another which creates a collection with a specified capacity.

- The **find() method is a protected helper method which is used by other methods that need to search the array for elements**

- **Since most other methods (contains(), remove(), and get()) need to search the array to some extent, they will all use the find method!**

- - **The existence of the find() method removes the need to create similar code over and over again in the removes, contains, and get methods!**

- The find() method changes the values of instance variables *found* and *location* to indicate if the element was found and where it was found.

---

## Application: Vocabulary Density

The vocabulary density of a text is:

`total # of words / total # of unique words`

We will develop an application that reads a text file and calculates it vocabulary density by reading the number of words it has and the number of unique words it has.

- **A word will be defined as a sequence of characters (A-z, a-z) that are potentially followed up by an apostrophe somewhere in between.**

Ex: "The", "Molly's", "Qu'hito", "parley"

- We also ignore case when determining if a word is unique.

- **In other words "The", "the", and "tHe" will be counted as the same word for the purposes of counting up the number of unique words.**

To count the number of unique words we can use a collection to keep track of the number of words encountered thus far.

- **In essence the algorithm will read through the text word by word, checking if each normalized (lowercased) word is already in the collection. If the word is not in the collection, we add an all normalized (lowercased) version of the word to the collection. If the word is in the collection we skip adding it to the collection. Throughout the algorithm we keep track od the number of words we read. And by the end, the size of the collection is the number of unique words. We can then simply say:**

Check the VocabularyDenisty class to see the algoithm in action on a given file name.

`density = # of words read / size of collection`

Some notes regarding the actual application:

- The code is super simple because so much of the complexity is handled by the Collection class. Hooray for abstraction!

- This program **utilizes a try-catch to deal mainly with an IO exception that may be thrown by the FileReader if an invalid file is passed (the file could not be found). Another way to deal with might be to just say that the main method throws the IOException, instead of using a try-catch (this is how the book does things).**

- We use the Scanner class to break down the input file into words. This is possible because we define a specific set of delimiters that are what split up the words in the file. **To understand why the delimiters were specified the way they were, read the comments in the file.**

- **The toLowercase() method normalizes the words before we check them against and/or add them to the array. Recall that we DO NOT want to count "Hello" and "hello" as distinct words and end up adding both.**

- The constant capacity is used to hard-cap the number of words our collection can hold. If the file contains more than this capacity we could further add a statement that says it contains at least x words, and then we could not predict the density! Check exercise 5.12a to in the book to further apply this.

---

## Comparing Objects Revisited

When comparing objects with the comparison operator (==) the comparison is actually made between the two reference variables that point to the object and NOT between the contents of the two objects.

- The equals() method that comes from the Object class behaves exactly like the comparison operator.

Ex.

    Circle c1 = new Circle();

    Circle c2 = new Circle();

    boolean a = c1.equals(c2) 
    // Will be false if the Circle class inherits the Object class's equals() method

    boolean b = (c1 == c2)
    // Will always be false.

When we write the equals() method of the Circle object, we can write the method we want!

Ex.

    public boolean equals(Object obj){

        if(obj == this){
            // If the two objects are pointing to
            // the same thing in memory, then of cours
            // their contents are the same.
            return true.
        }

        if(obj == null || obj.getClass() )

    }


---

## Sorted Array-Based Collection Implementation



---

## Link-Based Collection Implementation



---

## Collection Variations



---