import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;

/** ULLMapTest. You should write additional tests.
 *  @author Josh Hug
 */

public class ULLMapTest {
    @Test
    public void testBasic() {
        ULLMap<String, String> um = new ULLMap<String, String>();
        um.put("Gracias", "Dios Basado");
        assertEquals(um.get("Gracias"), "Dios Basado");
        assertEquals(1, um.size());
        um.put("pika", "chu");
        assertEquals(2, um.size());
        String shouldbechu = um.get("pika");
        assertEquals(shouldbechu, "chu");
        um.clear();
        assertEquals(0, um.size());
    }

    @Test
    public void testSize(){
        ULLMap<String, String> umx = new ULLMap<String, String>();
        umx.put("Gracias", "Dios Basado");
        umx.put("pika", "chu");
        assertEquals(2, umx.size());

    }

    @Test
    public void testClearandGet(){
        ULLMap<String, String> umc = new ULLMap<String, String>();
        umc.put("Hola", "Adios");
        umc.put("Salut", "Au Revoir");
        umc.put("Ciao", "Ciao");
        umc.put("Hello", "Goodbye");
        assertEquals(true, umc.containsKey("Ciao"));
        assertEquals(4, umc.size());
        umc.clear();
        assertEquals(0, umc.size());
        umc.put("Ei go ga", "wakarimaska");

        assertEquals(false, umc.containsKey("Ciao"));
        assertEquals(1, umc.size());
    }

    
    @Test
    public void testIterator() {
        ULLMap<Integer, String> um = new ULLMap<Integer, String>();
        um.put(0, "zero");
        um.put(1, "one");
        um.put(2, "two");
        Iterator<Integer> umi = um.iterator();
        System.out.println(umi.next());
        System.out.println(umi.next());
        System.out.println(umi.next());
    }

    @Test
    public void testIterator2(){
        ULLMap<Integer, String> xx = new ULLMap<Integer, String>();
        xx.put(121, "Hello");
        xx.put(24, "Legggoooo");
        xx.put(3444, "Okay");
        Iterator<Integer> xxi = xx.iterator();
        Iterator<Integer> xxi2 = xx.iterator();

        System.out.println(xxi.next());
        System.out.println(xxi.next());
        System.out.println(xxi.next());

        System.out.println(xxi2.next());
        System.out.println(xxi2.next());
        System.out.println(xxi2.next());

    }

    @Test
    public void testIterator3(){
        ULLMap<String, String> xx = new ULLMap<String, String>();
        xx.put("Lana", "Del Rey");
        xx.put("Marina", "And the Diamonds");
        xx.put("Taylor", "Swift");
        Iterator<String> xxi = xx.iterator();
     
         System.out.println(xxi.next());
        System.out.println(xxi.next());
        System.out.println(xxi.next());

    }

    @Test
    public void testInvert() {
        ULLMap<Integer, String> um = new ULLMap<Integer, String>();
        um.put(0, "zero");
        um.put(1, "one");
        um.put(2, "two");
        ULLMap<String, Integer> umi = ULLMap.invert(um);
        assertEquals(1, (int) umi.get("one"));
        assertEquals(0, (int) umi.get("zero"));
        assertEquals(2, (int) umi.get("two"));
    }


    @Test
    public void testInvert2(){
        ULLMap<Integer, Integer> um = new ULLMap<Integer, Integer>();
        um.put(0, 100);
        um.put(1, 200);
        um.put(2, 300);
        ULLMap<Integer, Integer> umi = ULLMap.invert(um);
        assertEquals(0, (int) umi.get(100));
        assertEquals(1, (int) umi.get(200));
        assertEquals(2, (int) umi.get(300));        
    }
    

    /** Runs tests. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(ULLMapTest.class);
    }
} 