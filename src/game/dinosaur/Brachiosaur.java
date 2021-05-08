package game.dinosaur;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Counter;
import game.actions.AttackAction;
import game.actions.FeedingAction;
import game.enums.DietCapabilities;
import game.enums.DinosaurCapabilities;
import game.enums.DinosaurState;

public class Brachiosaur extends Dinosaur{
    /**
     * Constructor.
     *
     * @param name the name of the Actor
     */
    public Brachiosaur(String name, Boolean baby) {
        super(name, 'B', 100, baby);
        maxunconsciousTime = 20;
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.BRACHIOSAUR);
        if (baby) {setBabyAttributes();}
        else {growUp();}
    }

    public Brachiosaur(String name, Boolean baby, char gender) {
        super(name, 'B', 100, baby, gender);
        maxunconsciousTime = 20;
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.BRACHIOSAUR);
        if (baby) {setBabyAttributes();}
        else {growUp();}
    }



    @Override
    void setBabyAttributes() {
        maxHitPoints = 80;
        hungryHealth = 40;
        breedingHealth = Integer.MAX_VALUE;
        mateTime = Integer.MAX_VALUE;
        matureCounter = new Counter(50);
        addCapability(DinosaurState.BABY);
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
