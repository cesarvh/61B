import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    /* Do not change this to be private. For silly testing reasons it is public. */
    public Calculator tester;

    /**
     * setUp() performs setup work for your Calculator.  In short, we 
     * initialize the appropriate Calculator for you to work with.
     * By default, we have set up the Staff Calculator for you to test your 
     * tests.  To use your unit tests for your own implementation, comment 
     * out the StaffCalculator() line and uncomment the Calculator() line.
     **/
    @Before
    public void setUp() {
        // tester = new StaffCalculator(); // Comment me out to test your Calculator
        tester = new Calculator();   // Un-comment me to test your Calculator
    }

    // TASK 1: WRITE JUNIT TESTS
    // YOUR CODE HERE
    @Test
    public void addTester1(){

        assertEquals(1, tester.add(1, 0));
        assertEquals(4, tester.add(1, 3));
        assertEquals(6, tester.add(-1, 7));
    }

    @Test
    public void addTester2(){
        assertEquals(-24, tester.add(-50, 26));
        assertEquals(0, tester.add(0, 0));
        assertEquals(0, tester.add(-5, 5));
        assertEquals(-7, tester.add(-3, -4));
    }
    @Test
    public void addTester3(){
        assertEquals(6, tester.add(1, 5));
        assertEquals(9, tester.add(3, 6));
        assertEquals(2102, tester.add(2000, 102));
    }

    @Test
    public void mulTester1(){
        assertEquals(1, tester.multiply(1, 1));
        assertEquals(0, tester.multiply(0, 1));
    }
    @Test
    public void mulTester2(){


        assertEquals(25, tester.multiply(5, 5));
        assertEquals(-4, tester.multiply(-2, 2));
    }
    @Test
    public void mulTester3(){
        assertEquals(100, tester.multiply(10, 10));
        assertEquals(125, tester.multiply(-25, -5));
            }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(CalculatorTest.class);
    }       
}
