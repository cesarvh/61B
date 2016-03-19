public class GuardGame {
	/*https://www.google.com/foobar/?eid=KEBAVcCJPNbSoAS1u4D4Dw&usg=AG3vBD1b2FP8i192J97uI5GfMXXpIrpIBw*/
	


	public static int game(long x) {
		if (x % 9 == 0 && x != 0) {
			return 9;
		}
		else if (x >= 10) {
			return (int) (x % 9);
		}
		return (int) x;
		
	}

	public static void main(String[] args) {
		System.out.println(game(1)); // 4
		System.out.println(game(2)); // 2
		System.out.println(game(3)); // 3
		System.out.println(game(0)); //1
		// System.out.println(game);

	}

}


