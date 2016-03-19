import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
        public EquationList history;
        public int counter = 0;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        // YOUR CODE HERE
        int c; 
        if ((x == 1) && (y == 0)){
            return x;
        }
        else if ((x == 0) && (y == 1)){
            return y;
        }

        while (y != 0){
            c = x & y;
            x = x ^ y;
            y = c << 1;
        }
        return x;
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {
        // YOUR CODE HERE
        int result = 0;
        if ((x < 0) && (y < 0)){
            y = ~y;
            y = add(y, 1);
            x = ~x;
            x = add(x, 1);
            while (y != 0){
                if ((y & 1) != 0){
                    result = add(result, x);
                }
                x = x << 1;
                y = y >> 1;
            }
        }
        else{
            while (y != 0){
                if ((y & 1) != 0){
                    result = add(result, x);
                }
            x = x << 1;
            y = y >> 1;
            }
        }
        return result;
    }

    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/
    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        if (history == null){
            counter += 1;
            history = new EquationList(equation, result, null);
        }
        else{
            counter += 1;
            history = new EquationList(equation, result, history);
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        // YOUR CODE HERE
        EquationList pointer = history;

        if (history == null){
        }
        while (pointer != null){
            System.out.print(pointer.equation + " = ");
            System.out.println( pointer.result);
            pointer = pointer.next;

        }
    }
 
    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        // YOUR CODE HERE
        EquationList pointer = history;

        if (history == null){
            return;
        }
        while ((n > 0) && (pointer != null)) {
            System.out.print(pointer.equation + " = ");
            System.out.println( pointer.result);
            pointer = pointer.next;
            n -= 1;
            }
        }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (history == null){
            return;
        }
        history = history.next;


    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        // EquationList clearList = history;
        int clearCount = counter;
        if (history == null){
            return;}
        while (clearCount > 1 ){
            undoEquation();
            clearCount -= 1;
            }      

    }
    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int result = 0;
        EquationList pointer = history;

        while (pointer != null){
            result += pointer.result;
            pointer = pointer.next;
        }
        return result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int result = 1;
        if (history == null){
            result = 0;
        }
        // int result = 1;
        EquationList pointer = history;
        while (pointer != null){
            result = result * pointer.result;
            pointer = pointer.next;
        }
        return result;
    }


}