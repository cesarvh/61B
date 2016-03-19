import java.util.Comparator;

/**
 * MinComparator.java
 */

public class MinComparator implements Comparator<TST.TSTNode> {

	public MinComparator(){
	}

	@Override 
	public int compare(TST.TSTNode nodeA, TST.TSTNode nodeB) {
		if (nodeA.myWeight > nodeB.myWeight) {
			return -1;
		} else if (nodeA.myWeight == nodeB.myWeight) {
			return 0;
		}
		else { // a < b
			return 1;
		}
	}	
}