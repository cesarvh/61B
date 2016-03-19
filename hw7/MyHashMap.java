import java.util.Set;
/* Your implementation MyHashMap should implement this interface. To do so, 
 * append "implements Map61B<Key,Val>" to the end of your "public class..."
 * declaration, though you can use other formal type parameters if you'd like.
 * For example, you can also say "... implements Map61B<Ben, Jerry>".
 */ 
public class MyHashMap<Key, Val> implements Map61B<Key, Val> {
    private int size;

    public MyHashMap() {
      // size = 0;
    }
    public MyHashMap(int initialSize) {
        // size = initialSize;

    }
    public MyHashMap(int initialSize, float loadFactor) {
        // size = initialSize;
    }


    /** Removes all of the mappings from this map. */
    public void clear() {

    }

    /* Returns true if this map contains a mapping for the specified key. 
     * Should run on average constant time when called on a HashMap. 
     */
    public boolean containsKey(Key key) {
        return true;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. Should run on average constant time
     * when called on a HashMap. 
     */
    public Val get(Key key) {
        return null;
    }  

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;

    }

    /* Associates the specified value with the specified key in this map. 
     * Should run on average constant time when called on a HashMap. */
    public void put(Key key, Val value) {

    }  

    /* Removes the mapping for the specified key from this map if present. 
     * Should run on average constant time when called on a HashMap. */
    public Val remove(Key key) {
      return null;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Should run on average constant time when called on 
     * a HashMap. */
    public Val remove(Key key, Val value) {
      return null;
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<Key> keySet() {
      return null;
    }
    }
