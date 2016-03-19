import static org.junit.Assert.*;
import org.junit.Test;

public class IntListTest {

    /** Example test that verifies correctness of the IntList.list static 
     *  method. The main point of this is to convince you that 
     *  assertEquals knows how to handle IntLists just fine.
     */

    @Test 
    public void testList() {
        IntList one = new IntList(1, null);
        IntList twoOne = new IntList(2, one);
        IntList threeTwoOne = new IntList(3, twoOne);

        IntList x = IntList.list(3, 2, 1);
        assertEquals(threeTwoOne, x);
    }

    @Test
    public void testdSquareList() {
      IntList L = IntList.list(1, 2, 3);
      IntList.dSquareList(L);
      assertEquals(IntList.list(1, 4, 9), L);
    }

    /** Do not use the new keyword in your tests. You can create
     *  lists using the handy IntList.list method.  
     * 
     *  Make sure to include test cases involving lists of various sizes
     *  on both sides of the operation. That includes the empty list, which
     *  can be instantiated, for example, with 
     *  IntList empty = IntList.list(). 
     *
     *  Keep in mind that dcatenate(A, B) is NOT required to leave A untouched.
     *  Anything can happen to A. 
     */

    //TODO:  Create testSquareListRecursive()

    @Test 
    public void testSquareListRecursive(){
        IntList S = IntList.list(1, 2, 3);
        IntList.squareListRecursive(S);
        IntList X = IntList.list(1, 2, 3);
        assertEquals(S, X);

        
    }

    @Test
    public void testFunctionSLR(){
        IntList S = IntList.list(1, 2, 3);
        IntList G = IntList.squareListRecursive(S);
        assertEquals(IntList.list(1, 4, 9), G);
    }
   



    // TODO:  Create testDcatenate and testCatenate
    @Test
    public void testCatenate(){
        IntList l1 = IntList.list(1, 2, 3);
        IntList s1 = IntList.list(4, 5, 6);
        IntList l2 = IntList.list(1, 2, 3);
        IntList s2 = IntList.list(4, 5, 6);
        IntList.catenate(l1, s1);

        assertEquals(l1, l2);
        assertEquals(s1, s2);

    }


    @Test
    public void testFunctionC(){
        IntList l1 = IntList.list(1, 2, 3);
        IntList s1 = IntList.list(4, 5, 6);
        IntList l1s1 = IntList.catenate(l1, s1);
        assertEquals(IntList.list(1, 2, 3, 4, 5, 6), l1s1);

        IntList a = IntList.list();
        IntList b = IntList.list(1);
        IntList ab = IntList.catenate(a, b);
        assertEquals(IntList.list(1), ab);
    }
        
    @Test
    public void testDcatenate(){
        IntList A = IntList.list(1, 2, 3);
        IntList B = IntList.list(4, 5, 6);
        IntList.dcatenate(A, B);
        assertEquals(IntList.list(1, 2, 3, 4, 5, 6), A);

    }

    


    //TODO:  Create testDcatenate and testCatenate


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(IntListTest.class);
    }       

  }   


