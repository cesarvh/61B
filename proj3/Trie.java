import java.util.HashMap;
/**
 * Prefix-Trie. Supports linear time find() and insert(). 
 * Should support determining whether a word is a full word in the 
 * Trie or a prefix.
 * @author Cesar
 */
public class Trie {
    TrieNode root = new TrieNode();
    int size = 0;
    
    /**
     * TrieNode class. Allows the creation of nodes,
     * each containing a letter and a flag of whether
     * they are complete ("blue") words or incomplete ("white") words.
     */
    protected class TrieNode {
        char presentLetter;
        boolean complete;
        HashMap<Character, TrieNode> links;
        String word;
        
        /**
         * Constructs a node
         * @param letter to be added to the node.
         * @param isFullWord flags whether the word is complete or not
        */
        public TrieNode(char letter, boolean isFullWord) {
            presentLetter = letter;
            links = new HashMap<Character, TrieNode>();
            complete = isFullWord;
            word = null;
        }

        /**
         * Constructs the root node
         */
        public TrieNode() { 
            links = new HashMap<Character, TrieNode>();
            complete = false;
        }
    }

    /**
     * @param isFullWord tells you wether the word is full or not
     * @param s is the word you want to find. IllegalArgument if empty
     * @return a boolean indicating whether the word is in the trie or not
     * Finds a word and stuff
     */
    public boolean find(String s, boolean isFullWord) {
        if (s.length() == 0 || s == null) {
            throw new IllegalArgumentException("Null and empty strings by "  
               + "definition are never in the Trie.");
        } else {
            char[] nemoArray = s.toCharArray();
            int nemoLength = nemoArray.length;
            TrieNode poppaMerlon = root;        

            int n;
            for (n = 0; n < nemoLength; n++) {
                char currentNemo = nemoArray[n];
                if (poppaMerlon == null) {
                    return false;
                }
                poppaMerlon = poppaMerlon.links.get(currentNemo);
            }

            if (n == nemoLength && poppaMerlon == null) {
                return false;
            } 
            boolean wordIsFull = (poppaMerlon != null && !poppaMerlon.complete && isFullWord);
            return (!wordIsFull);

        }
    }

    /**
     * @return s the size of the Trie
     * 
     */
    public int size() {
        return size;
    }

    /**
     * @param s is the word you want to find. IllegalArgument if empty
     * inserts a new word into the trie
     * credit to help to http://bit.ly/1EBzYKw for help on inserts
     */
    public void insert(String s) {
        if (s.length() == 0 || s == null) {
            throw new IllegalArgumentException("Null and empty strings by "  
               + "definition are never in the Trie.");
        } else {
            char[] sCharacters = s.toCharArray();
            int charsLength = sCharacters.length;
            TrieNode rootPointer = root;

            for (int i = 0; i < charsLength; i++) {
                char currentCharacter = sCharacters[i];
                if (!rootPointer.links.containsKey(currentCharacter)) {
                    rootPointer.links.put(currentCharacter, new TrieNode(currentCharacter, false));
                }
                rootPointer = rootPointer.links.get(currentCharacter);
            }
            rootPointer.complete = true;
            rootPointer.word = s;
            size++;

        }
    }



    /**
     * Test client. Reads the data from the file, 
     * then repeatedly reads autocomplete queries from standard 
     * input and prints out the top k matching terms.
     * @param args takes the name of an input file and an integer k as command-line arguments
     */
    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("hello");
        t.insert("hey");
        t.insert("goodbye");
        System.out.println(t.find("hell", false)); // t
        System.out.println(t.find("hello", true)); // t 
        System.out.println(t.find("good", false)); // t
        System.out.println(t.find("bye", false));  // f
        System.out.println(t.find("heyy", false)); // f
        System.out.println(t.find("hell", true));  // f
        System.out.println(t.find("hello", false)); //t

        System.out.println("======");

        t.insert("josh");
        t.insert("joshhugs");
        t.insert("joshhubs");

        System.out.println(t.find("josh", true)); // true 
        System.out.println(t.find("josh", false)); //true 
        System.out.println(t.find("joshshrugs", false)); //false
        System.out.println(t.find("jos", false)); //true
        System.out.println(t.find("joshhugs", true)); // true
        System.out.println(t.find("joshhubs", true)); // true

        System.out.println("======");
        t.insert("garçon");
        t.insert("élève");
        t.insert("hôpital");
        t.insert("здравствуйте");
        t.insert("año");
        System.out.println(t.find("garçon", true)); //t
        System.out.println(t.find("élève", true)); //t
        System.out.println(t.find("hôpital", true)); //t
        System.out.println(t.find("здравствуйте", true)); //t
        System.out.println(t.find("здра", true)); //f
        System.out.println(t.find("año", true)); //t
        System.out.println(t.find("ñi", true)); //f
        System.out.println(t.find("añ", false)); //t



    }
}
