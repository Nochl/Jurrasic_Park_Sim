package game.dinosaur;

import game.Counter;
import game.enums.DietCapabilities;
import game.enums.DinosaurCapabilities;
import game.enums.DinosaurState;

public class Brachiosaur extends Dinosaur{
    /**
     * Constructor that creates a BABY instance of Brachiosaur
     *
     * @param name the name of the Actor
     */
    public Brachiosaur(String name) {
        super(name, 'B', 40);
        setBabyAttributes();
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.BRACHIOSAUR);
    }


    @Override
    void setBabyAttributes() {
        maxHitPoints = 80;
        hungryHealth = 40;
        breedingHealth = Integer.MAX_VALUE;
        mateTime = Integer.MAX_VALUE;
        matureCounter = new Counter(50);
    }

    @Override
    Counter getAttackTimeoutCounter() {
        return new Counter(50);
    }

    @Override
    void growUp() {
        maxHitPoints = 160;
        hungryHealth = 140;
        breedingHealth = 70;
        mateTime = 30;
    }
}
