package game.dinosaur;

import game.Counter;

public class Brachiosaur extends Dinosaur{
    /**
     * Constructor.
     *
     * @param name the name of the Actor
     */
    public Brachiosaur(String name) {
        super(name, 'B', 100);
        maxHitPoints = 160;
        hungryhealth = 140;
        breedinghealth = 70;
    }

    @Override
    Counter createTimeoutCounter() {
        return new Counter(50);
    }
}
