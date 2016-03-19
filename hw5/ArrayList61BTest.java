import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

/** ArrayList61BTest. You should write additional tests.
 *  @author Josh Hug
 */

public class ArrayList61BTest {
    @Test
    public void basicTest() {
        List<Integer> L = new ArrayList61B<Integer>();
        L.add(5);
        L.add(10);
        assertTrue(L.contains(5));        
        assertFalse(L.contains(0));

    }
    
    @Test
    public void moreTest(){
        List<Integer> X = new ArrayList61B<Integer>();
        X.add(1);
        X.add(2);
        X.add(3);
        X.add(4);
        X.add(5);
        assertTrue(X.contains(1));
        assertTrue(X.contains(2));
        assertTrue(X.contains(3));

        
        assertFalse(X.contains(1000));
        assertEquals(5, X.size());
    }

    /** Runs tests. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(ArrayList61BTest.class);
    }
}   