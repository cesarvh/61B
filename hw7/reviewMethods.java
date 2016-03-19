import java.util.HashMap;
public class reviewMethods {
	
   public static String rotate(String s1) {
		int index = s1.length();
		StringBuilder newS = new StringBuilder();
		for (int i = s1.length() -1 ; i > -1; i--) {
			newS.append(s1.charAt(i));
		}
		String retString = new String(newS);
		return retString;
	}

	public static boolean contains(String s1, String s2) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < s1.length(); i += 1) {
			char c = s1.charAt(i);
			int x = 1;
			if (!map.containsKey(c)) {
				map.put(c, x);
			} else if (map.containsKey(c)) {
				int x2 = map.get(c);
				x2 += 1;
				map.put(c, x2);
			}
		}

		for (int j = 0; j < s2.length(); j++) {
			char c2 = s2.charAt(j);
			if (map.get(c2) < 0) {
				return false;
			}
			if (map.containsKey(c2)) {
				int replacement = map.get(c2);
				replacement -= 1;
				map.put(c2, replacement);
			}

		}
	return true;
	}



	public static void main(String[] args) {
		String x = "Y-our fa-ce";
		// String y = "is ha poop";
		// System.out.println(contains(x, y));
		System.out.println(rotate(x));
		// System.out.println(rotate(y));
	}
}