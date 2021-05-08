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
    public Stegosaur(String name) {
        super(name, 'S', 25);
        setBabyAttributes();
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.STEGOSAUR);
    }

    @Override
    Counter getAttackTimeoutCounter() {
        return new Counter(30);
    }

    @Override
    void growUp() {
        maxHitPoints = 100;
        hungryHealth = 90;
        breedingHealth = 50;
        mateTime = 10;
    }

    @Override
    void setBabyAttributes() {
        maxHitPoints = 50;
        hungryHealth = 25;
        breedingHealth = Integer.MAX_VALUE;
        mateTime = Integer.MAX_VALUE;
        matureCounter = new Counter(30);
    }
}
