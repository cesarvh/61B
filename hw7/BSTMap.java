import java.util.Set;
public class BSTMap<K, V> implements Map61B<K, V> {
	private int size;
	private Leaf root;

    /** Removes all of the mappings from this map. */
    public void clear() {
    	// Leaf this = new Leaf();
    	size = 0;

    }

    /* Returns true if this map contains a mapping for the specified k. */
    public boolean containsKey(K k) {
    	if (this == null) {
    		return false;
    	} else { 
    		return this.get(k) != null;
    	}

    }

    /* Returns the v to which the specified k is mapped, or null if this
     * map contains no mapping for the k. 
     */
    public V get(K k) {
    	return null;

    }

   /* Returns the number of k-v mappings in this map. */
    public int size() {
    	return size;

    }

    /* Associates the specified v with the specified k in this map. */
    public void put(K k, V v) {
    	size += 1;

    }
    public void printInOrder() { 

    }

    private class Leaf {
    
        /** Stores k as the k in this k-v pair, VAL as the v, and
         *  NEXT as the next node in the linked list. */
        public Leaf(K k, V v, int n) { 
            k = k;
            val = v;
            numNodes = n;
        }

        /** Returns the Leaf in this linked list of k-v pairs whose k
         *  is equal to k, or null if no such Leaf exists. */
        public Leaf get(K k) {
            // Leaf pNode = front;

            // while(pNode != null){
            //     if(k.equals(pNode.k)){
            //         return pNode;
            //     }
            //     pNode = pNode.next;

            // }
            return null;
        }


        /** Stores the k of the k-v pair of this node in the list. */
        private K k; 
        /** Stores the v of the k-v pair of this node in the list. */
        private V val; 
        /** Stores the next Leaf in the linked list. */
        private Leaf left; 

        private Leaf right;

        private int numNodes;
    
    }




    /* Removes the mapping for the specified k from this map if present.
     * Not required for HW6. */
    public V remove(K k) {
    	throw new UnsupportedOperationException("This is Extra");
    }

    /* Removes the entry for the specified k only if it is currently mapped to
     * the specified v. Not required for HW6a. */
    public V remove(K k, V v) {
    	throw new UnsupportedOperationException("This is Extra");

    }

    /* Returns a Set view of the keys contained in this map. Not required for HW6. */
    public Set<K> keySet() {
    	throw new UnsupportedOperationException("This is Extra");

    }

}
