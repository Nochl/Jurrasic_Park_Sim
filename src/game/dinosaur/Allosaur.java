package game.dinosaur;

import edu.monash.fit2099.engine.*;
import game.Counter;
import game.enums.DietCapabilities;
import game.enums.DinosaurCapabilities;
import game.enums.DinosaurState;

public class Allosaur extends Dinosaur{

    /**
     * Constructor.
     *
     * @param name the name of the Actor
     */
    public Allosaur(String name, Boolean baby) {
        super(name, 'A', 100, baby);
        maxunconsciousTime = 20;
        addCapability(DietCapabilities.CARNIVORE);
        addCapability(DinosaurCapabilities.ALLOSAUR);
        if (baby) {setBabyAttributes();}
        else {growUp();}
    }

    public Allosaur(String name, Boolean baby, char gender) {
        super(name, 'A', 100, baby, gender);
        maxunconsciousTime = 20;
        addCapability(DietCapabilities.CARNIVORE);
        addCapability(DinosaurCapabilities.ALLOSAUR);
        if (baby) {setBabyAttributes();}
        else {growUp();}

    }



    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20, "gouges");
    }

    @Override
    Counter getAttackTimeoutCounter() {
        return new Counter(20);
    }

    @Override
    void growUp() {
        maxHitPoints = 100;
        hungryHealth = 70;
        breedingHealth = 50;
        mateTime = 20;
    }

    @Override
    void setBabyAttributes() {
        maxHitPoints = 50;
        hungryHealth = 25;
        breedingHealth = Integer.MAX_VALUE;
        mateTime = Integer.MAX_VALUE;
        matureCounter = new Counter(40);
        addCapability(DinosaurState.BABY);
    }

}
