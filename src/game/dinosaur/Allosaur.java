package game.dinosaur;

import edu.monash.fit2099.engine.IntrinsicWeapon;
import game.Counter;
import game.enums.DietCapabilities;
import game.enums.DinosaurCapabilities;

public class Allosaur extends Dinosaur{

    /**
     * Constructor.
     *
     * @param name the name of the Actor
     */
    public Allosaur(String name) {
        super(name, 'A', 100);
        maxHitPoints = 100;
        hungryhealth = 140;
        breedinghealth = 50;
        mateTime = 20;
        maxunconsciousTime = 20;
        addCapability(DietCapabilities.CARNIVORE);
        addCapability(DinosaurCapabilities.ALLOSAUR);
    }

    public Allosaur(String name, char gender) {
        super(name, 'A', 100, gender);
        maxHitPoints = 100;
        hungryhealth = 140;
        breedinghealth = 50;
        mateTime = 20;
        maxunconsciousTime = 20;
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
