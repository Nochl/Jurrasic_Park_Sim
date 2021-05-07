package game.dinosaur;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Counter;
import game.actions.AttackAction;
import game.actions.FeedingAction;
import game.enums.DietCapabilities;
import game.enums.DinosaurCapabilities;

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
        mateTime = 30;
        maxunconsciousTime = 20;
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.BRACHIOSAUR);
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions action = new Actions();
        action.add(new FeedingAction(this));
        action.add(new AttackAction(this));
        return action;
    }

    @Override
    Counter createTimeoutCounter() {
        return new Counter(50);
    }
}
