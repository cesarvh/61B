import java.util.Comparator;

/**
 * RadiusComparator.java
 */

public class RadiusComparator implements Comparator<Planet> {

	public RadiusComparator(){
	}
		public int rCompare(Planet planet1, Planet planet2){
		int castedDifR = 0;
		double diffR = planet1.radReturn - planet2.radReturn;
		castedDifR = (int) diffR;
		return castedDifR;
	}

    /** Returns the difference in radius as an int.
     *  Round after calculating the difference. */
    public int compare(Planet planet1, Planet planet2) {
        return (int)(planet1.radReturn() - planet2.radReturn());
    }
}