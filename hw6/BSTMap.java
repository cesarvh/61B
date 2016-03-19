
public class BSTMap<K, V> implements Map61B<K, V>, Comparable<K> {
	private int size;
	private Leaf root;

    /** Removes all of the mappings from this map. */
    public void clear() {
    	this = new Leaf();
    	size = 0;

    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
    	if (this == null) {
    		return false;
    	} else { 
    		this.get(key) != null;
    	}

    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. 
     */
    public V get(K key) {
    	

    }

   /* Returns the number of key-value mappings in this map. */
    public int size() {
    	return size;

    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
    	size += 1;

    }
    public void printInOrder() { 

    }

    private class Leaf {
    
        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        public Leaf(Key k, Value v, int n) { 
            key = k;
            val = v;
            numNodes = n;
        }

        /** Returns the Leaf in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Leaf exists. */
        public Leaf get(Key k) {
            Leaf pNode = front;

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
        /** Stores the next Leaf in the linked list. */
        private Leaf left; 

        private Leaf right;

        private int numNodes;
    
    }




    /* Removes the mapping for the specified key from this map if present.
     * Not required for HW6. */
    public V remove(K key) {
    	throw new UnsupportedOperationException("This is Extra");
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for HW6a. */
    public V remove(K key, V value) {
    	throw new UnsupportedOperationException("This is Extra");

    }

    /* Returns a Set view of the keys contained in this map. Not required for HW6. */
    public Set<K> keySet() {
    	throw new UnsupportedOperationException("This is Extra");

    }

}
