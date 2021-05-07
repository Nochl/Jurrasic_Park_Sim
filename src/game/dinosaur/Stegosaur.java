package game.dinosaur;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Counter;
import game.actions.AttackAction;
import game.actions.FeedingAction;
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
        mateTime = 10;
        maxunconsciousTime = 20;
        addCapability(DietCapabilities.HERBIVORE);
        addCapability(DinosaurCapabilities.STEGOSAUR);
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions action = new Actions();
        action.add(new FeedingAction(this));
        if (isConscious()) {
            action.add(new AttackAction(this));
        }
        return action;
    }

    @Override
    Counter createTimeoutCounter() {
        return new Counter(30);
    }
}
