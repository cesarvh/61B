import java.util.HashMap; // Import Java's HashMap so we can use it

public class FibonacciMemo {

    public static HashMap memoizer = new HashMap();

    // static HashMap memoizer = new HashMap();
    /**  
     * The classic recursive implementation with no memoization. Don't care
     * about graceful error catching, we're only interested in performance.
     * 
     * @param n
     * @return The nth fibonacci number
     */
    public static int fibNoMemo(int n) {
        if (n <= 1) {
            return n;
        }
        return fibNoMemo(n - 2) + fibNoMemo(n - 1);
    }

    /**
     * Your optimized recursive implementation with memoization. 
     * You may assume that n is non-negative.
     * 
     * @param n
     * @return The nth fibonacci number
     */
    public static int fibMemo(int n) {
        if (n == 0 || n == 1) {
            return n;
        } return fibHelper(n);
    }

    public static int fibHelper(int index) {
        if (index == 0 || index == 1) { 
            return index;
        } if (memoizer.containsKey(index)) {
            return (int) memoizer.get(index);
        } else {
            // int res = fibMemo(index);
            memoizer.put(index, fibHelper(index - 1) + fibHelper(index - 2));
            // return fibHelper(index - 1) + fibHelper(index - 2);
            return fibHelper(index - 1) + fibHelper(index - 2);

        }
    }
/* fibonacci method */
// Set up an initialized map named fibValues.
// If the index argument to fibonacci is 0 or 1,
// return index.
// Otherwise return fibhelper(index).

/* fibhelper method */
// First check for index = 0 or index = 1 as in fibonacci.
// If fibValues contains the key index,
// return the corresponding value.
// Otherwise we have to compute the answer rather than retrieve it.
// After computing the answer, place it in fibValues and return the result.

    public static String why47() {
        String answer = "Because large ints would overflow and return incorrect values";
        answer += ", " + answer + " and tapioca";
        return answer;
    }

    public static void main(String[] args) {
        // Optional testing here        
        String m = "Fibonacci's real name was Leonardo Pisano Bigollo.";
        m += "\n" + "He was the son of a wealthy merchant.\n";
        System.out.println(m);
        System.out.println("0: " + FibonacciMemo.fibMemo(0));
        System.out.println("1: " + FibonacciMemo.fibNoMemo(1));
        System.out.println("2: " + FibonacciMemo.fibNoMemo(2));
        System.out.println("3: " + FibonacciMemo.fibNoMemo(3));
        System.out.println("4: " + FibonacciMemo.fibNoMemo(4));

        System.out.println("0: " + FibonacciMemo.fibMemo(30));
        System.out.println("1: " + FibonacciMemo.fibNoMemo(35));
        System.out.println("2: " + FibonacciMemo.fibNoMemo(45));
        System.out.println("3: " + FibonacciMemo.fibNoMemo(40));
        System.out.println("4: " + FibonacciMemo.fibNoMemo(15));

        // 46th Fibonacci = 1,836,311,903
        // 47th Fibonacci = 2,971,215,073
    }
}
