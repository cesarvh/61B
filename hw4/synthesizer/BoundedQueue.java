/* 
 * @author Cesarvh 
 */
package synthesizer;

public interface BoundedQueue{
	public int capacity(); //returns the size of the buffer
	public int fillCount(); //returns the number of items curently in the buffer
	public boolean isEmpty(); //Is the buffer empty? (fill Count == 0);
	public boolean isFull(); //Is the buffer full (fillCount is same as capacity);
	public void enqueue (double x); //adds a thing to the end
	public double dequeue(); //removes item form the front, and returns it
	public double peek(); // returns but doesnt delete item from the front
}

/*Examples of things that should happen: 

capacity is four
isEmpty --> returns true
enqueue(9.3) --> [9.3]
enqueue(13.5) --> [9.3], [13.5]
enqueue(31.1) --> [9.3], [13.5], [31.1]
isFull --> returns false
enqueue(13.1) --> [9.3], [13.5], [31.1], [13.1]
isFull () --> returns true
dequeue() --> returns [9.3] and is deleted
peek() --> [13.5] is returned

*/ 
   // /Users/Cesarvh/Documents/CS/61B/ayf/hw4/BoundedQueue.java
  // /Users/Cesarvh/Documents/CS/61B/ayf/hw4/AbstractBoundedQueue.java