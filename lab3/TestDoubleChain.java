import static org.junit.Assert.*;

import org.junit.Test;

/** Perform tests of the DoubleChain class
 */

public class TestDoubleChain {

    /** Tests the constructor of DoubleChain */
    @Test
    public void testConstructor() {
        DoubleChain d = new DoubleChain(5);
        assertEquals(5,d.getFront().val, 1e-6);
        assertEquals(null, d.getFront().prev);
        assertEquals(null, d.getFront().next);
    }
    @Test
    public void testConstructor2(){
        DoubleChain x = new DoubleChain(20);
        assertEquals(20, x.getFront().val, 1e-6);
        assertEquals(null, x.getFront().prev);
        assertEquals(null, x.getFront().prev);

    }

    @Test
    public void getBack1(){
        DoubleChain x = new DoubleChain(20);
        DoubleChain y = new DoubleChain(80);
        assertEquals(20, x.getBack().val, 1e-6);
        assertEquals(80, y.getBack().val, 1e-6);
    }


    @Test
    public void insertBack2(){
        DoubleChain d = new DoubleChain(5);
        d.insertBack(75);
        d.insertFront(64);
        d.insertFront(892);
        d.insertFront(1);
        d.insertBack(89);
        assertEquals(89, d.getBack().val, 1e-11);
        assertEquals(1, d.getFront().val, 1e-11);

    }

    @Test
    public void stringTest1(){
        DoubleChain x = new DoubleChain(2);
        assertEquals(2, x.toString());
    }

    @Test
    public void stringTest2(){
        DoubleChain f = new DoubleChain(3);
        f.insertFront(2);
        f.insertFront(1);
        assertEquals("1, 2, 3", f.toString());


    }

    @Test 
    public void stringTest3(){
        DoubleChain d = new DoubleChain(5);
        d.insertBack(75);
        d.insertFront(64);
        d.insertFront(892);
        d.insertFront(1);
        d.insertBack(89);
        assertEquals("892, 64, 5, 75, 89", d.toString());


    }

    @Test
    public void nullTest(){
    DoubleChain d = new DoubleChain(5);
    d.insertBack(6.0);
    assertEquals(6.0, d.getFront().next.val, 1e-6);
    d.insertFront(4.1);
    assertEquals(4.1, d.getBack().prev.prev.val, 1e-6);
}

    /** Tests some basic DoubleChain operations. */
    @Test
    public void testBasicOperations() {
        DoubleChain d = new DoubleChain(5);
        assertEquals(5, d.getFront().val, 1e-11);
        assertEquals(5, d.getBack().val, 1e-11);

        d.insertFront(2);
        assertEquals(2, d.getFront().val, 1e-11);


        d.insertFront(1);
        d.insertBack(7);
        d.insertBack(8);
        assertEquals(1, d.getFront().val, 1e-11);
        assertEquals(8, d.getBack().val, 1e-11);
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestDoubleChain.class);
    }
}
