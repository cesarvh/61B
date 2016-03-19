package ngordnet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Collection;
import java.util.ArrayList;


public class YearlyRecord {

    private int size = 0;
    private HashMap<String, Integer> wordToCount;
    // private TreeMap<Integer, String> mapArray;
    private HashMap<String, Integer> rankings = new HashMap<String, Integer>();
    private TreeMap<Integer, ArrayList<String>> mapArray;
    private boolean frozen = false;
    /** Creates a new empty YearlyRecord. */
    public YearlyRecord() {
        wordToCount = new HashMap<String, Integer>();
        mapArray = new TreeMap<Integer, ArrayList<String>>();
    }

    /** Creates a YearlyRecord using the given data. */
    public YearlyRecord(HashMap<String, Integer> otherCountMap) {
        wordToCount = new HashMap<String, Integer>();
        wordToCount.putAll(otherCountMap); 

        mapArray = new TreeMap<Integer, ArrayList<String>>();
        for (String s : otherCountMap.keySet()) {
            Integer val = otherCountMap.get(s);
            if (!mapArray.containsKey(val)) {
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(s);
                mapArray.put(val, temp);
                size += 1;
            }
            if (!mapArray.get(val).contains(s)) {
                mapArray.get(val).add(s);
                size += 1;
            }
        }


    }

    /** Returns the number of times WORD appeared in this year. */
    public int count(String word) {
        return wordToCount.get(word);
    }

    /** Records that WORD occurred COUNT times in this year. */
    public void put(String word, int count) {
        // mapArray.put(count, word);

        wordToCount.put(word, count);
        size += 1;
        if (!mapArray.containsKey(count)) {
            ArrayList<String> wds = new ArrayList<String>();
            wds.add(word);
            mapArray.put(count, wds);
        } else { 
            mapArray.get(count).add(word);
        }
        frozen = false;


    }

    /** Returns the number of words recorded this year. */
    public int size() { 
        return size;
    }

    /** Returns all words in ascending order of count. */
    public Collection<String> words() { // broken
        ArrayList<String> returnWords = new ArrayList<String>();
        
        for (Integer c : mapArray.keySet()) {
            for (int i = 0; i < mapArray.get(c).size(); i += 1) {
                // System.out.println(mapArray.get(c).get(i));
                returnWords.add(mapArray.get(c).get(i));
            }
        }
        return returnWords;
    }

    /** Returns all counts in ascending order of count. */
    public Collection<Number> counts() {
        ArrayList<Number> countsList = new ArrayList<Number>(); //
        // list.addAll(Arrays.asList(array));
        Integer[] returnCounts = new Integer[this.size()];
        ArrayList<Number> rc = new ArrayList<Number>();
        int i = 0;
        for (String s : wordToCount.keySet()) {
            returnCounts[i] = wordToCount.get(s);
            i += 1;
        }
        Arrays.sort(returnCounts);

        countsList.addAll(Arrays.asList(returnCounts));

        return countsList;
    }

    /** Returns rank of WORD. Most common word is rank 1. 
      * If two words have the same rank, break ties arbitrarily. 
    * No two words should have the same rank.*/
    public int rank(String word) { //BROKEN :(
        if (!wordToCount.containsKey(word)) { 
            System.out.print("This word could not be found in this corpus :( " 
                + "therefore its rank is: ");
            return -1;
        }
        if (!frozen) { 
            int rank = mapArray.size();
            for (String w : words()) { 
                rankings.put(w, rank);
                rank -= 1;
            }
            frozen = true;
        }
        return rankings.get(word);
    }
}
