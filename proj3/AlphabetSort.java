import java.util.HashSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Implements alpabetSort on prefixes for a given dictionary of terms and weights.
 * @author Cesar
 */
public class AlphabetSort {
    static Trie t;

    /**
     * Makes sure no Error Cases are present in the given args array.
     * @param alphabet is the alphabet we're checking
     * @param x is the trie given
     * duplicacy and emptiness
     */
    public static void checkIllegals(String alphabet, Trie x) {
        if (alphabet == null) {
            throw new IllegalArgumentException("There are no words to sort!");
        }
        if (x.size() == 0) {
            throw new IllegalArgumentException("There are no words to sort");
        } else if (repeats(alphabet)) {
            throw new IllegalArgumentException("There are repeat letters in the alphabet!");
        }

    }
    
    /**
     * Makes sure there are no repeats in the given alphabet
     * @param alphabet is the alphabet that is to be checked for repeats
     * @return whether there are repeats or not.
     */
    public static boolean repeats(String alphabet) {
        HashSet<Character> alph = new HashSet<Character>();
        for (int i = 0; i < alphabet.length(); i++) {
            char currentChar = alphabet.charAt(i);
            if (alph.contains(currentChar)) {
                return true;
            }
            alph.add(currentChar);
        }
        return false;
    }


    /**
     * Inserts words into the Trie
     * @param triee is the trie where our words are stored
     * @param alf is the alphabet to sort by
     * @param count is a counter to know when to stop if only words on one side of alphabet
     */
    public static void trieInsert(Trie triee, String alf, int count) {
        checkIllegals(alf, triee);
        char[] alphabetArray = alf.toCharArray();
        Trie.TrieNode trieRoot = triee.root;
        sorter(alphabetArray, trieRoot, count);

    }

    /**
     * Sorts the words inserted
     * @param alphabet is the letters in the alphabet
     * @param trieRoot is the root of the trie.
     * @param count is a counter to know when to stop if only words on one side of alphabet
     */
    public static void sorter(char[] alphabet, Trie.TrieNode trieRoot, int count) {
        Trie.TrieNode pointer = trieRoot;

        for (int i = 0; i < alphabet.length; i++) {
            if (count == 1) {
                return;
            }
            char c = alphabet[i];
            pointer = trieRoot;
            if (pointer.links.containsKey(c)) {
                pointer = pointer.links.get(c);
                if (pointer.complete) {
                    System.out.println(pointer.word);
                    count -= 1;
                }
                sorter(alphabet, pointer, count);
            }
        }
    }

    /**
     * Test client. Reads the data from the file, 
     * then repeatedly reads autocomplete queries from standard 
     * input and prints out the top k matching terms.
     * @param args takes the name of an input file and an integer k as command-line arguments
     * Scanning help from http://www.mkyong.com/java/how-to-get-the-standard-input-in-java/
     */
    public static void main(String[] args) {
        t = new Trie();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input;
            int count = 0;
            String alf = null;

            while ((input = br.readLine()) != null) {
                if (count == 0) {
                    alf = input;
                } else {
                    t.insert(input);
                }  
                count++;
            }
            trieInsert(t, alf, count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
