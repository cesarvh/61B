package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

/** An implementation of a motile pacifist photosynthesizer.
 *  @author Josh Hug :D
 */
public class Plip extends Creature {
    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;
    /*Energy kept my replicating*/
    private static final double rEnergy = 0.5;
    /*Energy gained when staying still*/
    private static final double pGain = 0.2;
    /*Max plip Energy*/
    private static final int pMax = 2;
    /*Energy lost while moving*/
    private static final double mLoss = 0.15;

    /** creates plip with energy equal to E. */
    public Plip(double e) {
        super("plip");
        r = 99;
        g = 255;
        b = 76;
        energy = e;
    }

    /** creates a plip with energy equal to 1. */
    public Plip() {
        this(1);
    }

    /** Should return a color with red = 99, blue = 76, and green that varies
     *  linearly based on the energy of the Plip. If the plip has zero energy,
     *  it should have a green value of 63. If it has max energy, it should
     *  have a green value of 255. The green value should vary with energy
     *  linearly in between these two extremes. It's not absolutely vital
     *  that you get this exactly correct.
     */
    public Color color() {
        if(this.energy >= 1){
            g = 255;
        }
        else if(this.energy == 0){
            g = 63;
        }
        else{
            g = (int) energy * 255;
            if (g < 63){
                g = 63;

            }
            else if (g > 255){
                g = 255;
            }
            g = (int) 127.5;
        }
        return color(r, (int) g, b);
    }

    /** Do nothing with C, Plips are pacifists. */
    public void attack(Creature c) {
    }

    /** Plips should lose 0.15 units of energy when moving. If you want to
     *  to avoid the magic number warning, you'll need to make a
     *  private static final variable. This is not required for this lab.
     */
    public void move() {
        this.energy -= mLoss;
    }


    /** Plips gain 0.2 energy when staying due to photosynthesis. */
    public void stay() {
        this.energy += pGain;
        if(this.energy > pMax){
            this.energy = pMax;
        }
    }

    /** Plips and their offspring each get 50% of the energy, with none
     *  lost to the process. Now that's efficiency! Returns a baby
     *  Plip.
     */
    public Plip replicate() {
        this.energy = this.energy * rEnergy;
        double babyEnergy = energy * rEnergy;
        return new Plip(babyEnergy);
    }

    /** Plipsw take exactly the following actions based on NEIGHBORS:
     *  1. If no empty adjacent spaces, STAY.
     *  2. Otherwise, if energy >= 1, REPLICATE.
     *  3. Otherwise, if any Cloruses, MOVE with 50% probability.
     *  4. Otherwise, if nothing else, STAY
     *
     *  Returns an object of type Action. See Action.java for the
     *  scoop on how Actions work. See SampleCreature.chooseAction()
     *  for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> cloruses = getNeighborsOfType(neighbors, "clorus");


        if(empties.size() == 0){ //there are no empty spaces, stay
            return new Action(Action.ActionType.STAY);
        }                                                                                                                
        else if(this.energy >= 1){//if plip e > 1, it should replicate to an avail space
            Direction repDir = empties.get(HugLifeUtils.randomInt(empties.size()-1));
            return new Action(Action.ActionType.REPLICATE, repDir);
        }    
        else if(cloruses.size() > 0){ //if it sees a neighbor with name() == to "clorus", 
            //then it will move to any avail square with probability 50%, randomly
            int rNum = HugLifeUtils.randomInt(0, 1);
            if(rNum == 1){
                Direction movDir = empties.get(HugLifeUtils.randomInt(empties.size()-1));
                return new Action(Action.ActionType.MOVE, movDir);
            }
            return new Action(Action.ActionType.STAY);
        }
        else{
            return new Action(Action.ActionType.STAY);

        }   
    }


    private int randomWithRange(int min, int max){
        int range = (max - min) + 1;     
        return (int)(Math.random() * range) + min;

    }

}

