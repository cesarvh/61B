// Make sure to make this class a part of the synthesizer package
package synthesizer;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
      * the values cannot be changed at runtime. We'll discuss this and other topics
      * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor
    /* Buffer for storing sound data. */
    private BoundedQueue buffer;
    public double r;
    public double temp;
    public double aver;
    public double peeker;


    public GuitarString(double frequency) {
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this divsion operation into an int. For better
        //       accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros. <-- Wot?!
        int capacity = (int) (Math.round(SR/frequency));
        int capacityHolder = capacity;
        buffer = new ArrayRingBuffer(capacity);

        while(buffer.isFull() == false){
            buffer.enqueue(0.0);

        }
        
    }
    
    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {

        int i = 0;
        while(!buffer.isEmpty()){
            buffer.dequeue();
        }

        while(!buffer.isFull()){
            double r = Math.random() - 0.5;
                System.out.println(r);
                buffer.enqueue(r);

        }

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {

        temp = buffer.dequeue();
        peeker = buffer.peek();
        aver = ((temp+peeker ) / 2) * DECAY;
        buffer.enqueue(aver);

    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
    
}
