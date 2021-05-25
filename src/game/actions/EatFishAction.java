package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.DinosaurHold;
import game.consumable.Fish;
import game.dinosaur.Dinosaur;
import game.enums.DinosaurCapabilities;
import game.utils.RandomNumberGenerator;

import java.util.ArrayList;

/**
 * Implements an eat fish action from lake
 */
public class EatFishAction extends Action {

    /**
     * A Lake object
     */
    ArrayList<Fish> fishes;

    /**
     * Constructor
     * @param fishes an Array list of Fish
     */
    public EatFishAction(ArrayList<Fish> fishes) {
        this.fishes = fishes;
    }

    /**
     * Executes action of Pterodactyl eating fishes from lake
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (!actor.hasCapability(DinosaurCapabilities.PTERODACTYL)) {
            return actor + " is unable to get fish from lake";
        }
        int fishAmount = RandomNumberGenerator.randomIntInRange(0, 2);
        int numberOfFish = fishes.size();
        for (int i = 0; i < Math.min(numberOfFish, fishAmount); i++) {
            Fish fish = fishes.remove(0);
            actor.heal(fish.getEatenHealth(actor));
        }
        return actor + " catches " + fishAmount + " fish";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " catches some fish";
    }
}
