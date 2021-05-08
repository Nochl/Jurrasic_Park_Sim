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
        super(name, 'S', 50);
        maxHitPoints = 100;
        hungryHealth = 90;
        breedingHealth = 50;
        mateTime = 10;
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.STEGOSAUR);
    }

    public Stegosaur(String name, DinosaurState dinosaurState) {
        super(name, 'S', 50, dinosaurState);
        maxHitPoints = 100;
        hungryHealth = 90;
        breedingHealth = 50;
        mateTime = 10;
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.STEGOSAUR);
    }

    @Override
    Counter createTimeoutCounter() {
        return new Counter(30);
    }
}
