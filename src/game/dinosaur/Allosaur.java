package game.dinosaur;

import edu.monash.fit2099.engine.*;
import game.Counter;
import game.enums.DietCapabilities;
import game.enums.DinosaurCapabilities;
import game.enums.DinosaurState;

public class Allosaur extends Dinosaur{

    /**
     * Constructor that creates an instance of BABY Allosaur
     *
     * @param name the name of the Actor
     */
    public Allosaur(String name) {
        super(name, 'A', 25);
        setBabyAttributes();
        addCapability(DietCapabilities.CARNIVORE);
        addCapability(DinosaurCapabilities.ALLOSAUR);
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
    }

}
