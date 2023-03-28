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

In order to understand how objects are identified and compared, we need to understand how to determine the equality of two objects.

- **Understanding what it means to compare objects and determine which object is "greater" or "lesser" will allow us to try to store objects in a collection in "increasing" order.**

- **If the elements in a collection are sorted, we can use algorithms such as the binary search to drastically decrease the time we would need to find an element!**

- **But what does	it mean	to store elements “in order”? To	 understand	this we	need to understand object comparison by examining the equals() method and the compareTo() method (from the Comparable interface)!**

--- 

### The equals() method

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

    @Override
    public boolean equals(Object obj){

        if(obj == this){
            // If the two objects are pointing to
            // the same thing in memory, then of cours
            // their contents are the same.
            return true;
        }

        else if(obj == null || obj.getClass() != this.getClass()){
            // If a null reference is passed or
            // the class of the passed obj is not the
            // same as the class of the Circle() object
            // then we don't even need to compare the
            // contents! We know the two objects cannot be equal.
            return false;
        }

        else{
            // We know that both objects (the circle and the passed object)
            // must be of the Circle class, we just need to cast the passed
            // object first.

            // Then if the two objects have the same radius we know their
            // contents are the same so we return true, otherwise we return
            // false.

            Circle c = (Circle) obj
            return (c.radius == this.radius);

        }

    }

In regards to the implementation of the previous equals() method note a few things that are considered good practice.

- @Override notation indiactes to the compiler that we intend to override an ancestor's method. The compiler now double-checks the syntax to re-affirm that the method is actually overriding another method.

- The if clause at the top checks if we are comparing a circle to another alias of itself. If so we just return true, no need to check contents.

- The second if clause (else-if) checks if the argument is null, if it is we return false. **If the argument is null, everything to the right of the || sign is not even evaluated, since as long as one operant is false the whole thing is false**.

- If the obj passed is not a null reference then we further ensure that the two objects being compared (this and the obj) are of the same class. If they are not objects of the same class then they cannot possibly be equal to another (by our definition).

- If the processing comes to the else clause, then we cast the obj argument safely (since we know obj must of type Circle) and then compare the contents (the radii) to determine if the two objects have identical contents.

`c1.equals(c2)` now has a whole new meaning based on the overrided method.

**The key of a class is the set of attributes to determine the identity of a class. For a circle this is the radius!**

Ex. 
**Suppose I tell you to write an equals method for the FamousPerson class. If I say the unique set of attributes that distinguish two famous people is [firstName, lastName], then how would you code up the equals() method?**

Answer:

    public boolean equals(Object obj){

        if(this == obj){
            return true;
        }

        else if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }

        else{
            FamousPerson fp = (FamousPerson) obj;
            return (this.firstName.equals(fp.firstName) 
                    && this.lastName.equals(fp.lastName));
        }

    }

Take a look atthe FamousPerson class and the FamousCS.txt file. We will use these two files to create an application that will store the information from the text file into a collection.

- Debug the program to see how it works. I hope we know how to do that by this point.

- The key takeaway is this. As long as the user provides enough information to locate the item in the collection, **in other words as long as the user provides the keys** (based on the object's overriden .equals() implementation), we can pass a partial object to find what we want.

- **In this case we only needed the first and last names and then constructed the partial object from those keys**

`person = new FamousPerson(fname, lname, 0, "");`

- This object is not complete but we can use to to find an "identical" element since an element is considered equal to this one if they share the same firstName and lastName properties!

        if (people.contains(person)) {
            person = people.get(person);
            System.out.println(person + "\n");
        }

Breakdown:


`person = people.get(person);`
- If we find the person object inside the people collection, then we get the actual object that matched and reassign person to point to this retrieved object (retrieved via .get() method).

`System.out.println(person + "\n");`
- This might be confusing if you don't know the intricacies of Java. But basically, printing an object actually calls the toString() method of the object. We have overrided the Object's toString() method in the FamousPerson class, therefore our custom implementation of toString() is called in this line.

---

### The Comparable Interface

The equals method allows us to check whether a particular element is in a collection.

- **The compareTo() method extends the functionality to not only see if two objects are equal but also if one object is less than or more than another.**

- **compareTo() returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object respectively.**

Ex.

    int result = obj1.compareTo(obj2);
    // If obj1 > obj2 returns 1
    // Else if obj1 = obj2 returns 0
    // Else (if obj1 < obj2) returns -1

**If we use the compareTo method of a class to sort the objects of a class, this order is considered the natural order.**

- The compareTo() method's implementation should reflect the real world order of particular strings. In other words, strings should be arranged alphabettically, numbers might be stored in increasing order, golf scores might be sorted from high to low (since low means better in golf), and e.t.c

- **For a Car() object you might want to sort the cars based on increasing mileage, or maybe increasing price. Or some combination of the two, you can define this order via the compareTo() method.**

- We can use the Comparable interface with generics
`public class ClassA<T> implements Comparable<T>`

Using generic types with the compareTo() method ensures that we only compare compatible objects!

- Lets create a compareTo method for the FamousPerson class:

    public int compareTo(FamousPerson other){
        if(!this.lastName.equals(other.lastName)){
            return this.lastName.compareTo(other.lastName);
        }
        else{
            return this.firstName.compareTo(other.firstName);
        }
    }

Lets break down the approach above.

- First we check if the lastName property between the two objects is identical.

- **If the lastName is not equal between them, then we compare the lastName and return an int (which has to be -1 or 1) based on which lastName is greater as a String (which one alphabetically comes first).**
  
- **Else, we know that the lastName property IS EQUAL between the two objects. Then we have to resort comparing the firstName property and return a result based on which object has a firstName that comes first alphabetically. (could return -1, 0, or 1)**

- Note that if the two objects have the same lastName and the same firstName then this else statement is entered and evaluates to 0 (sinc firstName property is equal).

Ex. 
FamousPerson objects with the respective first and last names:

obj1 : Kalpesh Chacha
obj2 : Kalpesh Chavan

obj1.compareTo(obj2);

**Goes inside if statement since the two lastName strings are not equal, then we return based on the fact that obj1's last name (Chacha) comes first alphabetically (compared to Chavan). Therefore we return 1.**

obj1: Kelp Chavan
obj2: Kalpesh Chavan

obj1.compareTo(obj2);

**Goes inside else statement since the two lastName strings are equals, then we return based on the fact that obj2's first name (Kalpesh) comes first alphabetically compared to obj1's first name (Kelp). Therefore we return -1**

obj1: Kalpesh Cha
obj2: Kalpesh Cha

obj1.compareTo(obj2);

**Goes inside else statement since the two lastName strings are equal and then compares the two firstName strings and we realize that the firstName's are also lexographically identical. Therefore we return 0 based on the result of comparing the firstName strings (in the else statement).**

---

## Sorted Array-Based Collection Implementation

The initial ArrayCollection.java class assumed that all elements were just meant to be dumped into the next available slot in the array.

- **We did not care about the order of the elements in the arry (based on how they compare with one another) for the basic ArrayCollection.**

- The add() operation for the ArrayCollection has a constant, O(1), time complexity. But all other operations: get(), contains(), and remove(), operated in O(n) time complexity.

- **If we keep the internal array sorted as we keep adding and removing elements, then the searching mechanism behind the contains() method can be a binary search algorithm! This would also benefit the get() and remove() methods that use the contains() methods for the bulk of their work!**

- **This would drastically simplify the complexity of our contains(), get(), and remove() methods to O(log(n)).**

- The tradeoff would be the complexity of the add() method, since the method now needs to add an element in the right place of the array which is a complicated O(n) process.

- Our new implementation **will also be unbounded to hold a larger amount of elements and to truly utilize the better and more efficent algorithms behind the primary operations.**

- **The enlarge() method will be implemented and invoked internally by the add() method whenever needed!**

---

### Comparable Elements

Can you force applications to use Comparable classes when providing an argument for a generic type? YES

- **A bounded type has a type parameter (like T) but we restrict what T can possibly be based on saying that T should implement or should extend some other interface or class. **

Ex. `ArrayBoundedStack<T implements Comparable<T>>>`

So the T specified as the concrete type for the stack during creation MUST implement the Comparable type.

Following this example we can create a dummy class called Duo.java (check it out in the files or see it below).

    public class Duo<T extends Comparable<T>>{
        
        protected T first;
        protected T second;

        public Duo(T first, T second){
            
            this.first = first;
            this.second = second;

        }

        public T larger(){
            if (first.compareTo(second) > 0){
                return first;
            } else{
                return second;
            }
        }

    }

Note that for the Duo class, the parameter T, specified in the class header, must implement the Comparable<T> interface.

- Wait so why do we say "extends" in the bounds instead of implements???
- Confusing java syntax. Just remember to always say extends within the bounds of bounded type.

`Duo<T implements Comparable<T>>` WILL NOT WORK!

- For generics we just use `implements` for everything!

- If we tried to instantiate a Duo object with a type for T that did not implement the Comparable<T> interface, then the compiler would throw us errors! (our program would not run!)


- According to the textbook:
  "Attempting to declare an array of `<T extends Comparable<T>>`	results	in a syntax	error. So we need to find a	different	approach. Rather than try to enforce the use of	Comparable classes using syntactic means we	will use our “programming by contract”	approach. **We specify as	a precondition of the add method that its	argument is comparable to the previous objects added to the collection.** **As the add method is the only way for an element to	get	into the collection, we	are	guaranteed that	all	elements held in	our	SortedArrayCollection can be compared to each other.**"

  The long and short of it is that:
  `SortedArrayCollection<T> implements CollectionInterface<T>`
 is how the dumb book makes the header look, **this means that we are forced to try and cast any variable of type T whenever we use it to call compareTo() to reinforce that it implements Comparable so that the compiler can assume that it has an implementation of the method.**

 More experienced developers know that to avoid always having to cast, we can enforce that the type T that we use to create the Object should implement Comparable to begin with via the Constructor and the class header!

 To do that we modify the header a bit!
 `SortedArrayCollection<T extends Comparable<T>> implements CollectionInterface<T>`

---

### The Implementation

The internal representation of teh SortedArrayCollection class is an unbounded array that enlarges itself whenever it hits its maximum capacity.

- The array must always be kept sorted. Anytime we add any elements, they must be added to ensure that the order stays unchanged (be it least -> greatest or greatest -> least based on the compareTo() method)!

- **The enlarage() method increases the size of the internal array by the original capacity (the capacity the array initially started with before any enlargement).**

- **The find() method of this class serves the same purpose as the find() method of the unsorted ArrayCollection class. However, the sorted nature of the elements allows us to use a faster, binary search, algorithm to find the element. This find method has a worst case time complexity of O(log(n)) as opposed to O(n), which was the worst case complexity finding the element in an unsorted collection.**

- The original implementation of this class (which you can find online) requires the passed element to be casted into a type of Comparable to utilize the compareTo() method in the find() method. **My implementation does not need this casting because the list can only be declared with a type T that MUST implement Comparable<T>.**

- When we go to add in an element we cannot simply add an element to the end of the list. We must first call find() on the element. Why?

`find(element)`

**The find method has a bonus functionality. If the element is not found, then the found variable is false, but the location variable is set to the index where the element should be inserted.**

- **We make use of the changed location variable to know where to insert the new element! First we shift all the elements at and after the location by 1 position up (to the right if you visualize it as an array). This creates a gap at the index specified by the location variable. Then we can safely insert the new element at the location (because it will not be overwritting any pre-existing value).**

Visual:

![alt link](SortedCollectionInsertion.PNG "Title")

- **The remove method similarly just shifts all the elements after the location down by one. So starting from index: location, we say : elements[i] = elements[i + 1]; This wipes out the value at the location (overwrites it) with the next value and so on and so forth. We simply say that we shifted all the elements after the location down by 1.**

Visual:

![alt link](SortedCollectionDeletion.PNG "Title")

### Implementing ADTs "by Copy" vs "by Reference"

If we wanted to handle elements we could choose to do so either by copy or by reference.

- A **by copy** approach manipulates a copy of the data that is passed into the method. Making a valid copy of an object might be complicated. However, if your object has the clone() method from the Cloneable interface, the process is simplified.

Ex. 

    public void add(T element){
        
        elements[numElements] = element.clone();
        numElements++;
    }


Valid copies of an object can typically be made using a clone method (if the object's class has such a method). **In the example above, we assumed that the object, of type T, implements the Cloneable interface and provides a thorough implementation for the clone() method.**

 **In the typical implementation of collections that store Objects: the reference to the added element would be stored.**

 - The key difference here, is that we are storing **a reference to the copy of the element, and not a reference to the original element.**

- **The "by copy" implementation of the get method would also return a reference to the copy of the element that is found**.

Ex. 

public T get(T target){
    
    find(target);

    if(found){
        return elements[location].clone();
    } else{
        return null;
    }
    
}

- Why would we want to do this approach? After all, creating a copy of the data before storing it, and the returning a reference to the copy of the data instead of the original data takes up valuable time and resources!

- Because this approach provides a layer of separation between the data the user wants to put into the collection and its actual state. **If I have an object and I want to add it to the collection, without actually giving a reference to the original piece of data, i.e. if I want to hide information, this ADT provides a separate repository which only deals with copies of the client data.**

- A "by reference" approach is what we typically utilize in our normal collections.

In other words our traditional implementations of the data manipulation methods.

Ex.

    public void add (T element){
        
        elements[numElements] = element;
        numElements++;

    }

- The client program, which is to say the collection, reatins a reference to the element data within its array. The ADT still hides the internal organization of the collection. But it allows direct access to the collection's data through other references to each element that exist in the client program.

So this means that if I add an element to a collection. And then use the reference to change something about the data in the client program, the reference to the data in the collection will also point to the changed object.

![alt link](ByCopyVSByReference.PNG "Title")

This illustration shows that a collection that deals with data through references **is just a collection of pointers that point to the original data in different parts of the memory. In other words it is simply organizing the references into a more quickly accessible pattern.**

- **In other words, when we add elements to a collection that deals with data through reference, that data is not locked up in the collection. Instead we are simply keeping references to the original data in a more organized structure. The underlying original data can still be manipulated outside of the scope of the collection.**

**The by copy approach takes the data and creates copies which are separate from the original data. Therefore any changes made on the collection's data do not affect the original external data and vice versa.**

Which approach is better? 

- **If processing time and space are issues, then making a copy of each element when manipulating the data is not ideal. Therefore the "by reference approach" would be the best.**

- **If we don't care about space usage but we want meticulous control over the access and integrity of the collection, and want the collection to be detached from the elements of the client application, we should use the "by copy" approach.**

---

### Sample Application

We can modify the sample application for vocabulary density, VocabularyDensity.java, to use a sorted collection instead of the typical collection.

Check the VocabularyDensitySorted.java class to see the that the code works identically.

- The sorted array collection's time complexity benefits really shine when you benchmark the two implementations of the program for the same text files. Finding the number of unique words requires to to use the contains() method each time we read a new word from the collection and check it against the words in the collection.

- The complexity of the contains() method for our sorted collection is O(log(N)) which is much quicker than the O(n) complexity for the unsorted collection's implementation.

- As we process larger and larger text files, we can see what difference this change in complexity means for the speed of processing:

![alt link](SortedVSUnsortedSpeedComparison.PNG "Title")

**Notice that in the example above, the larget file notices an insane efficency boost. Processing the fil takes 7.2 minutes with the sorted collection as opposed to 10 hours with the unsorted collection.**

---

## Link-Based Collection Implementation

We can also implement the collection class using the LLNode class. The link-based representation will be an unsorted.

- This section will discuss, compare and contrast the ArrayCollection class with the LinkedCollection class method by method.

---

### The Internal Representation

The information portion of the node will contain the actual element, whereas the link will reference the next element in the collection.

Check the `LinkedCollectionClass<T>` for further details and this is the class we will be referring to in the following discussion.

- We keep a variable of type LLNode<T> called head to maintain the a reference to the first element in the linked list.

- The constructor sets numElements to 0 and the head to null or a node based on a passed element to setup an empty collection or one that begins with a single specified element as the head.

---

### The Operations

- **The numElements variable is needed to support the size() method.**
- In theory we could use a for loop and keep calling getLink() alongside a counter to get the length of the entire underlying linked list. However, doing this each time we call the size() method would be O(n) operation. On the other hand, simply incrementing the numElements anytime a new element is added, and decrementing it whenever an element is removed, which means that we can just return the variable to get the size which is far less work (O(1) complexity)!
- isEmpty() method just returns true if the numElements variable is 0 (which indicates an empty collection).

- **The isFull() method will always return false, since a linked list based collection has no theoretical limit on the number of elements it can store.**

- The add method creates a new node with the given element and links the new node to the *head* node. Then we assign the head reference to the reference of the new node. We also increase the numElements by 1 to indicate an additional element in the collection. **In other words, when we add to a linked list based collection, a new element is always added to the beginning of the collection (as the head node).**

- The add method always returns true, because we don't have any space constraints.

- The find method is protected because it should only be able to be used by other methods in the same class. The array based implementation used a while loop to iterate through the array and check, one my one, if the value at each location index were equal to the element the user is trying to search for. If a match was found, then the boolean *found* is set to true and location is already set to the index of the found element.

- The implementation of find() for a link based collection is very similar. The only difference is that iteration happens using the .getLink() method on the current *location* which is a reference to the a node. We start by assigning the reference of head to the location variable. Then we enter a while loop that will only terminate if the location node is null. **If the location node is ever null, we will know that we have reached the end of the array. Once again, if the node's information matches with the target element we set found to true and return. Otherwise we change the value of previous to be the current location. And we change the location node by saying `location = location.getLink;`.**

- **The variable *previous* is used in the remove() method in order to remove an element from the list. If we find an element in the collection for removal, the variable previous points to the node directly before the found element.** We can change the link of the previous node to the node after the location to cut of the node at the location variable.

![alt link](LinkedCollectionRemoval.PNG "Title")

- **In order to remove an element, we can simply unlink the found node by saying: `previous.setLink(location.getLink())`. This effectively says: set the link of the previous node to node that comes after the location node. If the node to remove is the head node, then we can just say `head = head.getLink()` to dereference and remove it.**

- We can return the found variable, because the removal is successful if found was true and the removal did not occur if found was false.

---

### Comparing Collection Implementations

Regardless of the implementation, the numElements variable persistently stores and updates the current number of elements in the collection. **This ensures that the size(), isEmpty(), and isFull() methods have a O(1) complexity**, because all they need to do is check against numElements.

- The constructors for an ArrayCollection or SortedArrayCollection must create an internal array based on the capacity defined during the client's instantiation of the object. **The JVM must allot n slots in memory, where n is the capacity. Therefore the constructors of the ArrayCollection or SortedArrayCollection work in O(n) complexty.**

- For the ArrayCollection, **the add() operation is trivial because it is a simple insertion into the next available slot in the array which is always at the index defined by numElements.** **The complexity of add() for an unsorted ArrayCollection is O(1).**

- On the other hand the complexity of the add() operation is more complicated for a sorted collection. **To add an element to a sorted collection we first call the find() method which has a O(log(n)) complexity. However, we also have to insert at a non-predetermined location via a for loop which takes O(n) time.** **Therefore the overall complexity of the add operation for a SortedArrayCollextion is O(n).**

- **As mentioned prior for a SortedArrayCollection, finding an element is done via a binary search algorithm. As a result the complexity of the find operation for a SortedArrayCollection is O(log(n)).** 

- **However, the unsorted ArrayCollection class's implementation involves a for loop to look through n elements for a match. Therefore the complexity of the find() operation for a ArrayCollection is O(n).**

- The complexity of the contains() and get() methods follow based on the complexity of the find() method that they both use. **Therefore for an unsorted ArrayCollection, contains() and get() operations have a O(n) time complexity. For a SortedArrayCollection they have a O(log(n)) complexity.**

I did not touch on the LinkedCollection but its individual methods are very similar to the unsorted ArrayCollection class. They share the same complexities, with the exception of the class constructor.

- **Unlike ArrayCollection and SortedArrayCollection which have an internal array of elements, a LinkedCollection only needs to initialize space for at most 1 node via its constructors. Therefore the constructor for LinkedCollection had a O(1) complexity.**

In summary:

![alt link](ComplexityAnalysis.PNG "Title")

---

## Collection Variations

The functionality of a Collection seems so unneccesary and and simple.

- Why should not we just use a normal array or even an ArrayList to store, remove and retrieve data?

- **Well we can think of the Collection ADT as an additional abstraction between a typical array and more complicated data structures such as lists, search trees, maps, hash tables, and priority queues, amongst many more.**

- These more complicated structures can be thought of as different forms of collections (because they share the core underlying functionality of a typical collection).

- **This is why we call the Java library that has built in data structures the Collections framework.**

- First we look at the role of collections in the JDK, and the then we will take a look at two commonly used collections: bags and sets.

---

### The Java Collection Framework

**The "Collections Framework" is a unified architecture (library) of classes and interfaces that provides versatile tools for storing and retrieving information.**

- The `Collection` interface is the core of the framework. It is found in the java.util package.

- **The Collection interface has 11 subinterfaces including Deque, List, and Set and 33 implementing classes such as ArrayList, Stack, LinkedList and more!**

- The Collection interface has 15 methods including familiar methods such as isEmpty, size, add, remove, and contains. 

- The get() method is not present because the interface simply serves as the root of an inheritance tree, not all collections should have a get() method or some equivalent. The subinterfaces may have methods that have the same functionality as get() albeit with different names. You have to ask yourself, **should you be able to call get() on a Stack? No. So it does not make sense having the get method be at the root of the Collections framework.**

Here are some additional methods in the Collection interface and their intended purpose:

| **Method** | **Purpose** |
| --- | ----------- |
| toArray | Returns an array containing all of the elements of the collection. |
| clear | Removes all elements. |
| equals| Takes an Object argument, returning true if it is equal to the current collection and false otherwise. |
| addAll | Takes a Collection argument and adds its contents to the current collection; returns a boolean indicating success or failure. |
| retainAll | Takes a Collection argument and removes any elements from the current collection that are not in the argument collection; returns a boolean indicating success or failure. |
| removeAll | Takes a Collection argument and removes any elements from the current collection that are also in the argument collection. |

For people who know their set theory: removeAll is the difference of two sets, whereas the retainAll method gets the intersection of the two sets.

- removeAll returns false if the changed collection is identical to the original collection (the two collections had no elements in common).

- retainAll returns false if the changed collection is identical to the original collection (the two collections were identical in the unique elements they contained).

---

### The Bag ADT

- Suppose we want to make an ADT that fits our notion of a bag. The ADT should allow duplicate elements. 

- We can put in an object, search for an object based on a trait, and remove that specific object if needed.

- We may also want to know how many objects are in the bag at a given time and whether or not the bag is empty or full.

- Maybe we can make an operation to pull out a random object from the bag.

- We may want to peek into the Bag and count the elements with a specific trait. We could also remove these particular elements.

- To accomplish this functionality we have added the grab(), count(), removeAll(), and clear() methods to the BagInterface file.

- **The BagInterface<T> extends from the CollectionInterface and therefore any class that implements the BagInterface has to fullfill the contract of both the CollectionInterface and BagInterface.**

Check the files to see it in action.

- The Bag ADT can be used by applications that need to count specific elements in a collection or remove all of a specific element from a collection. Additionally, the grab() operation allows the Bag ADT to be used for applications that require randomness.

- Implementation of the Bag ADT is left as an interface.

---

### The Set ADT

**The Set ADT will not allow duplicate elements.**

- Mathematically, a set is a collection of distinct objects.

**To make a Set class we can just copy and change the code from any other Collection class and make a slight modification to the add() method.**

- In the add() method **we would check if the element we want to insert already exists in the collection, if it does not we would then add it and return true. Otherwise we would return false!**

- Other than copying the code, we could simply extend another Collection and override its add method! We are introducing inheritance to our implementations!

Check the BasicSet1() file to see how easy this is to implement.

- The biggest drawback to this approach is that future changes to the underlying LinkedCollection implementation could invalidate the unique element constraint of the BasicSet subclass.

- We also need to override the addAll method to ensure that duplicate elements are not mistakenly added via the addAll implementation in the parent Linked Collection class.

Rather than extending the Linked Collection, we could use a LinkedCollection object internally inside another Set class. Sometimes this technique is referred to as wrapping or composition.

Check the BasicSet2 class to see how that is implemented.

- The only instance variable in this class is the LinkedCollection, which is the variable we use in every method of the BasicSet class.

- Although BasicSet2 is more code to write than the BasicSet1 class, the BasicSet class avoids the maintainence issues of the BasicSet1 class.

- You can define a more complex Set ADT that supports set operations such as union, difference, and intersection. 

- **The BasicSet2 class is an example of hierarchical implementation. The BasicSet2 class is built using a LinkedCollection, which itself is composed of LLNode objects.**

---