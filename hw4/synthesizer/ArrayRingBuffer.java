// Make sure to make this class a part of the synthesizer package
package synthesizer;

public class ArrayRingBuffer extends AbstractBoundedQueue {
  /* Index for the next dequeue or peek. */
  private int first;           
  /* Index for the next enqueue. */
  private int last;             
  /* Array for storing the buffer data. */
  private double[] rb;

  /** Create a new ArrayRingBuffer with the given capacity. */
  public ArrayRingBuffer(int capacity) {
    // TODO: Create new array with capacity elements.
    //       first, last, and fillCount should all be set to 0. 
    //       this.capacity should be set appropriately. Note that the local variable
    //       here shadows the field we inherit from AbstractBoundedQueue.
    this.capacity = capacity;
    this.first = 0;
    this.last = 0;
    this.fillCount = 0;
    this.rb = new double[capacity];

  }
  /** Adds x to the end of the ring buffer. If there is no room, then
    * throw new RuntimeException("Ring buffer overflow") 
    */
  
  public void enqueue(double x) {
    // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
    // is there room?
    if (last != this.capacity()){
      rb[last] = x;
      last ++;
      fillCount++;

      if (last == this.capacity){
        last = 0;
      }
    }
    else{
      throw new RuntimeException("Ring buffer overflow");
    }

  }

  /** Dequeue oldest item in the ring buffer. If the buffer is empty, then
    * throw new RuntimeException("Ring buffer underflow");
    */
  public double dequeue() {
    // TODO: Dequeue the first item. Don't forget to decrease fillCount and update first.
   //to dequeue take it from index first and increment first.
  // To remove an item, take it from index first and increment first. 
  // When either index equals capacity, make it wrap-around by changing the index to 0
    if(this.isEmpty()){
      throw new RuntimeException("Ring buffer underflow");
    }
    else{
      double pointer = rb[first];
      rb[first] = 0.0;
      fillCount--;
      first++;
      if (first == capacity){
        first = 0;
      }
      return pointer;
    }
  }

  /** Return oldest item, but don't remove it. */
  public double peek() {
    // TODO: Return the first item. None of your instance variables should change.
    if(this.isEmpty()){
     throw new RuntimeException("Ring buffer underflow");
    }
    else{

      return rb[first];
    }
  }

}
