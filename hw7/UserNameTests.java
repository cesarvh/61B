import static org.junit.Assert.*;
import java.util.TreeMap;
import org.junit.Test;
import java.util.ArrayList;

import java.util.Collections;

public class UserNameTests {

	@Test
	public void test1() {
		TreeMap<Integer, String> tester = new TreeMap<Integer, String>();
		TreeMap<String, Integer> tester2 = new TreeMap<String, Integer>();
		int INSERTS = 12335;
		for (int i = 0; i < INSERTS; i += 1) {			
			Username u1 = new Username();
			tester.put(u1.hashCode(), u1.myUserName);
			tester2.put(u1.myUserName, u1.hashCode());

		}
		System.out.println("SIZE OF TOTAL INSERTS, UNREPEATED: " + INSERTS);
		int size1 = tester.keySet().size();
		int size2 = tester2.keySet().size();
		System.out.println("SIZE OF HashCodes (PERFECT     CODES? " + tester.keySet().size());
		System.out.println("SIZE OF Usernames (PERFECT USERNAMES? " + tester2.keySet().size());
		assertEquals(size1, size2);

	}

	public static void main(String[] args) {
		jh61b.junit.textui.runClasses(UserNameTests.class);
	}
}