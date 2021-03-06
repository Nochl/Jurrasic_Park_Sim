package game.dinosaur;

import game.Counter;
import game.enums.ActorMobilityCapabilities;
import game.enums.DietCapabilities;
import game.enums.DinosaurCapabilities;
import game.enums.DinosaurState;

/**
 * Implements Allosaur that extends from Dinosaur Class, that is capable of attacking stegosaurs,
 * hunting meat items mating
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 4.1.0
 * @see Dinosaur
 * @see Counter
 */
public class Brachiosaur extends Dinosaur{
    /**
     * Constructor method for Brachiosaur
     *
     * @param name the name of the Actor
     * @param baby a boolean value that denotes if the brachiosaur being created starts as a baby
     */
    public Brachiosaur(String name, Boolean baby) {
        super(name, 'B', 100, baby);
        maxunconsciousTime = 20;
        maxWater = 200;
        water = 60;
        addCapability(ActorMobilityCapabilities.WALK);
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.BRACHIOSAUR);
        if (baby) {setBabyAttributes();}
        else {growUp();}
    }

    /**
     * Constructor method for Brachiosaur
     * @param name a string name of the dinosaur
     * @param baby a boolean if the dinosaur is a baby
     * @param gender a char to denote that it is 'm' male or 'f' female
     */
    public Brachiosaur(String name, Boolean baby, char gender) {
        super(name, 'B', 100, baby, gender);
        maxunconsciousTime = 20;
        maxWater = 200;
        water = 60;
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.BRACHIOSAUR);
        if (baby) {setBabyAttributes();}
        else {growUp();}
    }

    /**
     * Changes the attributes of the dinosaur into those of a baby brachiosaur
     */
    @Override
    protected void setBabyAttributes() {
        maxHitPoints = 80;
        hungryHealth = 40;
        breedingHealth = Integer.MAX_VALUE;
        mateTime = Integer.MAX_VALUE;
        matureCounter = new Counter(50);
        addCapability(DinosaurState.BABY);
    }

    /**
     * Gets the amount of time it take for dinosaur to attack again
     * @return A Counter denoting attack timeout
     */
    @Override
    protected Counter getAttackTimeoutCounter() {
        return new Counter(50);
    }

    /**
     * Sets the attributes of the dinosaur object to those of a Adult brachiosaur
     */
    @Override
    protected void growUp() {
        maxHitPoints = 160;
        hungryHealth = 140;
        breedingHealth = 70;
        mateTime = 30;
    }
}
