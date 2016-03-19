import java.util.Set; /* java.util.Set needed only for challenge problem. */
import java.util.Iterator; 
/** A data structure that uses a linked list to store pairs of keys and values.
 *  Any key must appear at most once in the dictionary, but values may appear multiple
 *  times. Supports get(key), put(key, value), and contains(key) methods. The value
 *  associated to a key is the value in the last call to put with that key. 
 *
 *  For simplicity, you may assume that nobody ever inserts a null key or value
 *  into your map.
 */ 
public class ULLMap<Key, Value> implements Map61B<Key, Value>, Iterable<Key>{ //FIX ME
    /** Keys and values are stored in a linked list of Entry objects.
      * This variable stores the first pair in this linked list. You may
      * point this at a sentinel node, or use it as a the actual front item
      * of the linked list. 
      */
    private Entry front;
    private int size;

    @Override
    public Value get(Key key) { //FIX ME
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. 
     */
        Entry pointer = front;
        while(pointer != null){
            if(key.equals(pointer.key)){
                return pointer.val;
            }
             pointer = pointer.next;

        }
        return null;
    }


    @Override
    public void put(Key key, Value val) { //FIX ME
    /* Associates the specified value with the specified key in this map. */   
        for (Entry x = front; x != null; x = x.next){
            if (key.equals(x.key)){
                
                x.val = val;
                    return;
                }
            }
            front = new Entry(key, val, front);
            size += 1;               
        }



    @Override
    public boolean containsKey(Key key) { //FIX ME
    /* Returns true if this map contains a mapping for the specified key. */
        if(get(key) == null){
            return false;
        }
        return true;

        // return get(key) != null;

    }

    @Override
    public int size() {
    /* Returns the number of key-value mappings in this map. */
        return size; // FIX ME (you can add extra instance variables if you want)
    }

    @Override
    public void clear() {
    /** Removes all of the mappings from this map. */        
        while(this.size() != 0){
            front.val = null;
            front.key = null;
            front = front.next;
            size -= 1;
        }
    this.size = 0;
    }

    //returns in iterator instance 
    public Iterator<Key> iterator() {
            return new ULLMapIter(front);
        }

    /*** 
     * Returns a valid inverse of a map
     * Keys and values can't be null
     * if two of the same vals exist, only one key can exist*/
    public static <Key, Value> ULLMap<Value, Key> invert(ULLMap<Key, Value> map){
        ULLMap<Value, Key> invertedmap = new ULLMap<Value, Key>(); 
        for(Key key : map){
            // if(!containsKey(map.get(key)))
            invertedmap.put(map.get(key), key);
        }
        return invertedmap;

        // }
    }
    /** Represents one node in the linked list that stores the key-value pairs
     *  in the dictionary. */
    private class Entry {
    
        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        public Entry(Key k, Value v, Entry n) { //FIX ME
            key = k;
            val = v;
            next = n;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        public Entry get(Key k) { //FIX ME
            Entry pNode = front;

            while(pNode != null){
                if(key.equals(pNode.key)){
                    return pNode;
                }
                pNode = pNode.next;

            }
            return null;
        }


        /** Stores the key of the key-value pair of this node in the list. */
        private Key key; 
        /** Stores the value of the key-value pair of this node in the list. */
        private Value val; 
        /** Stores the next Entry in the linked list. */
        private Entry next;
    
    }

    private class ULLMapIter implements Iterator<Key>{ //only iterable
        /* This iterator should iterate over the keys of the
         * ULLMap (so each time next() is called it should 
         * return a key in the ULLMap until all of them have been returned). 
         * 
         * The iterator method of the ULLMap class should return a new instance 
         * of ULLMapIter
         */

        /*@Return true if there are more elements left to return*/
        
        Entry front;

        public ULLMapIter(Entry x){
            front = x;

        }

        public boolean hasNext(){
            if (front != null){

                return true;
            } else {

                return false;
            }
            
        }

        /*Returns the next element (where E is the formal type 
         * parameter of the iterator).*/
        public Key next(){
            if(!hasNext()){
                throw new IllegalArgumentException("Nothing else to iterate over");
            }
            else{
                Entry temp = front;
                front = front.next;
                return temp.key;
            }
        }







        public void remove(){
            throw new UnsupportedOperationException("Unsupported operation.");
        }
    }

// /* Methods below are all challenge problems. Will not be graded in any way. 
        //  * Autograder will not test these. */
        // @Override
        // public void remove(Key key) { //FIX ME SO I COMPILE
        //     throw new UnsupportedOperationException();
        // }

        // @Override
        // public void remove(Key key, Value value) { //FIX ME SO I COMPILE
        //     throw new UnsupportedOperationException();
        // }

        // @Override
        // public Set<Key> keySet() { //FIX ME SO I COMPILE
            // throw new UnsupportedOperationException();
        // }


}