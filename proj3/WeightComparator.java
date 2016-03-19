import java.util.Comparator;

/**
 * WeightComparator.java
 */

public class WeightComparator implements Comparator<TST.TSTNode> {

	public WeightComparator(){
	}

	@Override 
	public int compare(TST.TSTNode nodeA, TST.TSTNode nodeB) {
		if (nodeA.maxWeight > nodeB.maxWeight) {
			return -1;
		} else if (nodeA.maxWeight == nodeB.maxWeight) {
			return 0;
		}
		else { // a < b
			return 1;
		}
	}	

}