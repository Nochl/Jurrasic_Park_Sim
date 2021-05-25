package game.dinosaur;

import game.Counter;
import game.enums.ActorMobilityCapabilities;
import game.enums.DietCapabilities;
import game.enums.DinosaurCapabilities;
import game.enums.DinosaurState;

/**
 * Implements a Stegosaur Dinosaur in game
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 4.0.0
 * @see Dinosaur
 */
public class Stegosaur extends Dinosaur{
    /**
     * Constructor.
     *
     * @param name the name of the Actor
     */
    public Stegosaur(String name, Boolean baby) {
        super(name, 'S', 50, baby);
        maxunconsciousTime = 20;
        maxWater = 100;
        water = 60;
        addCapability(ActorMobilityCapabilities.WALK);
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.STEGOSAUR);
        if (baby) {setBabyAttributes();}
        else {growUp();}
    }

    /**
     * Constructor
     * @param name name of dinosaur
     * @param baby true if initialise as baby, else false
     * @param gender gender of dinosaur ('f' - female and 'm' for male)
     */
    public Stegosaur(String name, Boolean baby, char gender) {
        super(name, 'S', 50, baby, gender);
        maxunconsciousTime = 20;
        maxWater = 100;
        water = 60;
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.STEGOSAUR);
        if (baby) {setBabyAttributes();}
        else {growUp();}
    }

    /**
     * Gets the amount of time for stegosaur to be in timeout
     * @return A Counter object denoting attack timeout
     */
    @Override
    protected Counter getAttackTimeoutCounter() {
        return new Counter(30);
    }

    /**
     * Sets the attributes of the dinosaur object to those of an adult Stegosaur
     */
    @Override
    protected void growUp() {
        maxHitPoints = 100;
        hungryHealth = 90;
        breedingHealth = 50;
        mateTime = 10;
    }

    /**
     * Sets the attributes of the dinosaur object to those of a baby Stegosaur
     */
    @Override
    protected void setBabyAttributes() {
        maxHitPoints = 50;
        hungryHealth = 25;
        breedingHealth = Integer.MAX_VALUE;
        mateTime = Integer.MAX_VALUE;
        matureCounter = new Counter(30);
        addCapability(DinosaurState.BABY);
    }
}
