import java.util.HashSet;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.Set;
import java.util.NavigableSet;

/**
 * Implements the Tertiary Search Trie data structure
 * @author Cesar, with the help of the PSL 
 * Credit to Princeton Standard Library, after which I modeled this TST
 */
public class TST {
    int size;
    TSTNode root;
    TreeMap<Double, String> largestK = new TreeMap<Double, String>();
    // HashSet<String> mappings = new HashSet<String>(); 

    /**
     * Subclass TSTNode!
     * @author Cesar, with the help of the PSL 
     * Credit to Princeton Standard Library, after which I modeled this TST
     */
    public class TSTNode {


    }

    public int size() {
    }

    public boolean contains(String key) {
    }

    public double get(String key) {


    public double getMax(String key) {

    }

    public TSTNode get(TSTNode x, String key, int d) {
    }


    public TSTNode getRoot() {
    }

    public void put(String key, double myWeight) {
    }

    public TSTNode put(TSTNode x, String key, double myWeight, int d, TSTNode parental) {

    }

    public LinkedList<String> getList(int count) {

    }


    public Iterable<String> keysWithPrefix(String prefix) {

    }

    public HashSet<TSTNode> getChildren(TSTNode node) {

    }

    public void collect(TSTNode x, StringBuilder prefix, Queue<String> q) {
    
    }

    public Iterable<String> keys() {

    }

    public static void main(String[] args) {

}
