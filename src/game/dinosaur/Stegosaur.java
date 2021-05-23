package game.dinosaur;

import game.Counter;
import game.enums.DietCapabilities;
import game.enums.DinosaurCapabilities;
import game.enums.DinosaurState;

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
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.STEGOSAUR);
        if (baby) {setBabyAttributes();}
        else {growUp();}
    }


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


    @Override
    Counter getAttackTimeoutCounter() {
        return new Counter(30);
    }

    /**
     * Sets the attributes of the dinosaur object to those of an adult Stegosaur
     */
    @Override
    void growUp() {
        maxHitPoints = 100;
        hungryHealth = 90;
        breedingHealth = 50;
        mateTime = 10;
    }

    /**
     * Sets the attributes of the dinosaur object to those of a baby Stegosaur
     */
    @Override
    void setBabyAttributes() {
        maxHitPoints = 50;
        hungryHealth = 25;
        breedingHealth = Integer.MAX_VALUE;
        mateTime = Integer.MAX_VALUE;
        matureCounter = new Counter(30);
        addCapability(DinosaurState.BABY);
    }
}
