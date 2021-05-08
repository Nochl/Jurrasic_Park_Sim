package game.dinosaur;

import game.Counter;
import game.enums.DietCapabilities;
import game.enums.DinosaurCapabilities;
import game.enums.DinosaurState;

public class Brachiosaur extends Dinosaur{
    /**
     * Constructor.
     *
     * @param name the name of the Actor
     */
    public Brachiosaur(String name) {
        super(name, 'B', 100);
        maxHitPoints = 160;
        hungryHealth = 140;
        breedingHealth = 70;
        mateTime = 30;
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.BRACHIOSAUR);
    }

    public Brachiosaur(String name, DinosaurState dinosaurState) {
        super(name, 'B', 100, dinosaurState);
        maxHitPoints = 160;
        hungryHealth = 140;
        breedingHealth = 70;
        mateTime = 30;
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.BRACHIOSAUR);
    }

    @Override
    Counter createTimeoutCounter() {
        return new Counter(50);
    }
}
