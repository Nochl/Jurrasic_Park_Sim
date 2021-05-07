package game.dinosaur;

import game.Counter;
import game.enums.DietCapabilities;
import game.enums.DinosaurCapabilities;

public class Stegosaur extends Dinosaur{
    /**
     * Constructor.
     *
     * @param name the name of the Actor
     */
    public Stegosaur(String name) {
        super(name, 'S', 50);
        maxHitPoints = 100;
        hungryhealth = 90;
        breedinghealth = 50;
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.STEGOSAUR);
    }

    @Override
    Counter createTimeoutCounter() {
        return new Counter(30);
    }
}
