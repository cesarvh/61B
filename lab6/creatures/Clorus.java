package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

/*
 * @author Cesarvh
*/
public class Clorus extends Creature {
    /** red color. */
    private int r = 34;
    /** green color. */
    private int g = 0;
    /** blue color. */
    private int b = 231;
    /*Energy lost while moving*/
    private static final double mLoss = 0.03;
    /*Energy lost while staying still*/
    private static final double sLoss = 0.01;


    /** Creates a sample creature with energy E. This
     *  value isn't relevant to the life of a Clorus , since
     *  its energy should never decrease.
     */
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    /** Default constructor: Creatures creature with energy 1. */
    public Clorus () {
        this(1);
    }

    /** Uses method from Occupant to return a color based on personal.
      * r, g, b values */
    public Color color() {
        return color(r, g, b);
    }

    /*Cloruses attack and absorb their enemies' energy */
    public void attack(Creature c) {
    	this.energy += c.energy;
    }

    /*Cloruses lose mLoss energy when they stand still */
    public void move() {
    	this.energy -= mLoss;

    }

    /*Cloruses loose sLoss energy when they stand still*/
    public void stay() {
    	this.energy -= sLoss;
    }

    public Clorus replicate() {
    	//here, Cloruses receiv ehalf of their energy and offspring get other half.
    	energy = energy / 2;
    	double babyEnergy = energy;
    	return new Clorus(babyEnergy);
    }

    /** Sample Creatures take actions according to the following rules about
     *  NEIGHBORS:
     *  1. If surrounded on three sides, move into the empty space.
     *  2. Otherwise, if there are any empty spaces, move into one of them with
     *     probabiltiy given by moveProbability.
     *  3. Otherwise, stay.
     *
     *  Returns the action selected.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");

    	if(empties.size() == 0){ //No empty squares, then stay. Even if other squares are plips
            return new Action(Action.ActionType.STAY);
    	}	
    	else if(plips.size() >= 1){//If any plips are seen the clorus will ATTACK one of them randomly
    		Direction rNum = plips.get(HugLifeUtils.randomInt(plips.size()-1));

    		return new Action(Action.ActionType.ATTACK, rNum);
    	}
    	



    	else if(this.energy >= 1){ //If clorus has energy >= 1, it will replicate to a random square
            Direction repDirC = empties.get(HugLifeUtils.randomInt(empties.size()-1));
            return new Action(Action.ActionType.REPLICATE, repDirC);
    	}
    	




    	else{
    		Direction movDir = empties.get(HugLifeUtils.randomInt(empties.size()-1));
    		return new Action(Action.ActionType.MOVE, movDir);


    	}
    }
}
