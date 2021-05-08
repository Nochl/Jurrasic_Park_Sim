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
    public Allosaur(String name) {
        super(name, 'A', 100);
        maxHitPoints = 100;
        hungryHealth = 140;
        breedingHealth = 50;
        mateTime = 20;
        addCapability(DietCapabilities.CARNIVORE);
        addCapability(DinosaurCapabilities.ALLOSAUR);
    }

    public Allosaur(String name, DinosaurState dinosaurState) {
        super(name, 'A', 100, dinosaurState);
        maxHitPoints = 100;
        hungryHealth = 140;
        breedingHealth = 50;
        mateTime = 20;
        addCapability(DietCapabilities.CARNIVORE);
        addCapability(DinosaurCapabilities.ALLOSAUR);
    }
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20, "gouges");
    }

    @Override
    Counter createTimeoutCounter() {
        return new Counter(20);
    }
}
