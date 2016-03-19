/* Radix.java */

package radix;
 
/**
 * Sorts is a class that contains an implementation of radix sort.
 * @author
 */
public class Sorts {


    /**
     *  Sorts an array of int keys according to the values of <b>one</b>
     *  of the base-16 digits of each key. Returns a <b>NEW</b> array and
     *  does not modify the input array.
     *  
     *  @param keys is an array of ints.  Assume no key is negative.
     *  @param whichDigit is a number in 0...7 specifying which base-16 digit
     *    is the sort key. 0 indicates the least significant digit which
     *    7 indicates the most significant digit
     *  @return an array of type int, having the same length as "keys"
     *    and containing the same keys sorted according to the chosen digit.
     **/
    public static int[] countingSort(int[] keys, int whichDigit) {
        int[] sortedKeys = new int[keys.length];

        //create an array the length of the max number
        int maxNumber = findMax(keys);
        int[] helper = new int[maxNumber];

        //count the occurrences of each digit in the keys array.
        for (int i = 0; i < keys.length - 1; i ++) {
            int current = keys[i];
            helper[current] += 1;
        }

        /* Okay so i REALLY didnt understand what whichDigit was. AT ALL
         * So i kinda didnt know what else to do. I tried looking at other
         * resources for help, but I couldnt find any, and so i kinda just gave up.
         * :( I got this far but didnt really know what do do next :c 
         */


        return sortedKeys; 
    }


    private static int findMax(int[] arr) {
        int maxFinder = 0;
        for (int i = 0; i < arr.length; i ++) {
            if (arr[i] > maxFinder) {
                maxFinder = arr[i];
            }
        }
        return maxFinder;

    }

    /**
     *  radixSort() sorts an array of int keys (using all 32 bits
     *  of each key to determine the ordering). Returns a <b>NEW</b> array
     *  and does not modify the input array
     *  @param key is an array of ints.  Assume no key is negative.
     *  @return an array of type int, having the same length as "keys"
     *    and containing the same keys in sorted order.
     **/
    public static int[] radixSort(int[] keys) {
        //YOUR CODE HERE
        int[] sortedKeys = new int[keys.length];
        //create an array the length of the max number
        int maxNumber = findMax(keys);
        int[] helper = new int[maxNumber + 1];


        //count the occurrences of each digit in the keys array.
        for (int i = 0; i < keys.length - 1; i ++) {
            int current = keys[i];
            helper[current] += 1;
        }

        for (int k = 0; k < keys.length - 1; k++) {
            if (helper[k] != 0) {
                int j = 0;
                while (helper[k] != 0) {
                    sortedKeys[j] = k;
                    j++;
                    helper[k] -= 1;
                }
            }
        }



        return sortedKeys; 
    }
}

