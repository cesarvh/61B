import java.util.LinkedList;
import java.util.TreeSet;
import java.util.PriorityQueue;
//2:06

/**
 * Implements autocomplete on prefixes for a given dictionary of terms and weights.
 * @author Cesar
 */
public class Autocomplete {
    TST tst;
    final int INITIAL_SIZE = 11;
    final int HUNNY = -100;
    /**
     * Initializes required data structures from parallel arrays.
     * @param terms Array of terms.
     * @param weights Array of weights.
     */
    public Autocomplete(String[] terms, double[] weights) {
        TreeSet<String> duplicateChecker = new TreeSet<String>();

        tst = new TST();
        if (terms.length != weights.length) {
            throw new IllegalArgumentException("Terms and weights length differred!");
        }

        for (int i = 0; i < terms.length; i++) {
            if (duplicateChecker.contains(terms[i])) {
                throw new IllegalArgumentException("There are duplicate items in the file!");
            }
            duplicateChecker.add(terms[i]);
            checkWeight(weights[i]);
            tst.put(terms[i], weights[i]);
        }
    }



    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param term yay
     * @return a double yay
     */
    public double weightOf(String term) {
        return tst.get(term);
    }

    /**
     * Return the top match for given prefix, or null if there is no matching term.
     * @param prefix Input prefix to match against.
     * @return Best (highest weight) matching string in the dictionary.
     */
    public String topMatch(String prefix) {
        TST.TSTNode r = tst.getRoot();
        
        if (prefix.length() ==  0) {
            LinkedList<String> hi = tst.getList(1);
            return hi.get(0);
        } else if (tst.getMax(prefix) == HUNNY) {
            return null;
        } else {
            TST.TSTNode f = tst.get(r, prefix, 0); // = tst.getRoot();
            Iterable<String> ans = topMatches(prefix, 1);
            String ret = "null";
            for (String s : ans) {
                ret = s;
                break;
            }
            return ret;
        }

    }

    /**
     * Returns the top k matching terms (in descending order of weight) as an iterable.
     * If there are less than k matches, return all the matching terms.
     * @param prefix which is a prefix 
     * @param k which is an integer
     * @return an iterable
     */
    public Iterable<String> topMatches(String prefix, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("K must be positive");
        }
        WeightComparator x = new WeightComparator();
        MinComparator y = new MinComparator();

        PriorityQueue<TST.TSTNode> maxPQ = new PriorityQueue<TST.TSTNode>(INITIAL_SIZE, x);
        PriorityQueue<TST.TSTNode> bestKSoFar = new PriorityQueue<TST.TSTNode>(INITIAL_SIZE, y);

        LinkedList<String> returnedWords = new LinkedList<String>();
        TST.TSTNode rootP = tst.getRoot();
        if (prefix.length() ==  0) {
            return tst.getList(k);
        }

        if (tst.getMax(prefix) == HUNNY) {
            return returnedWords;
        }
        
        if (prefix.length() > 0) {
            rootP = tst.get(rootP, prefix, 0); // = tst.getRoot();

        }

        if (rootP.myWeight > -1) {
            bestKSoFar.add(rootP);
        }
        maxPQ.add(rootP);
 

        while (!maxPQ.isEmpty()) {
            TST.TSTNode top = maxPQ.poll(); 
            if (top.myWeight >= 0) { 
                if (!bestKSoFar.contains(top)) { 
                    bestKSoFar.add(top); 
                } 
            }
            for (TST.TSTNode c : tst.getChildren(top)) {
                maxPQ.add(c);
            }
        }

        while (returnedWords.size() != k) {
            if (bestKSoFar.size() == 0) {
                break;
            }
            returnedWords.add(bestKSoFar.poll().fullWord);
        }
        return returnedWords;
    }

    /**
     * Returns the highest weighted matches within k edit distance of the word.
     * If the word is in the dictionary, then return an empty list.
     * @param word The word to spell-check
     * @param dist Maximum edit distance to search
     * @param k    Number of results to return 
     * @return Iterable in descending weight order of the matches
     */
    public Iterable<String> spellCheck(String word, int dist, int k) {
        LinkedList<String> results = new LinkedList<String>();  
        /* YOUR CODE HERE; LEAVE BLANK IF NOT PURSUING BONUS */
        return results;
    }

    /**
     * Makes sure weight of Y isnt less than 0
     * @param y is the weight to be checked
     */
    public static void checkWeight(double y) {
        if (y < 0) {
            throw new IllegalArgumentException("A weight can't be negative!");
        }
    }

    /**
     * Test client. Reads the data from the file, 
     * then repeatedly reads autocomplete queries from standard 
     * input and prints out the top k matching terms.
     * @param args takes the name of an input file and an integer k as command-line arguments
     */
    public static void main(String[] args) {
        // initialize autocomplete data structure
        // TreeSet<String> duplicateChecker = new TreeSet<String>();
        In in = new In(args[0]);
        int N = in.readInt();
        String[] terms = new String[N];
        double[] weights = new double[N];
        for (int i = 0; i < N; i++) {
            double doubleRead = in.readDouble(); 
            weights[i] = doubleRead;  // read the next weight
            in.readChar();                  // scan past the tab
            String inLine = in.readLine();
            terms[i] = inLine;      // read the next term
        }

        Autocomplete autocomplete = new Autocomplete(terms, weights);

        // process queries from standard input
        int k = Integer.parseInt(args[1]);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            for (String term : autocomplete.topMatches(prefix, k)) {
                StdOut.printf("%14.1f  %s\n", autocomplete.weightOf(term), term);
            }   
        }
    }
}
