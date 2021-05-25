package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.FindNearestLocation;
import game.actions.EatFishAction;
import game.actions.EatFruitAction;
import game.actions.PickFruitAction;
import game.enums.*;

import java.util.ArrayList;

/**
 * Implements Scavenging Behaviour class that implements Behaviour interface.
 * Utilised by the Dinosaur class to find and get the closest item food source
 * within the game world
 *
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 4.0.0
 * @see game.dinosaur.Dinosaur
 * @see FoodTypeCapabilities
 * @see FindNearestLocation
 * @see EatFruitAction
 * @see Behaviour
 */
public class ScavengingBehaviour implements Behaviour {

    /**
     * Constructor
     */
    public ScavengingBehaviour() {
    }

    /**
     * Gets an action for actor to eat a nearby food source or go to a closest location
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @param actions an Actions object denoting all the allowable actions near the player
     * @return An Action object
     */
    @Override
    public Action getAction(Actor actor, GameMap map, Actions actions) {
        // Returns null if Dinosaur is not hungry
        if (!actor.hasCapability(HungryCapabilities.HUNGRY)) {
            return null;
        }

        // Checks if Dinosaur is not a Stegosaur, a Brachiosaur, or a Pterodactyl
        if (!actor.hasCapability(DinosaurCapabilities.STEGOSAUR) && !actor.hasCapability(DinosaurCapabilities.BRACHIOSAUR) &&
        !actor.hasCapability(DinosaurCapabilities.PTERODACTYL)) {
            return null;
        }

        for (Action action : actions.getUnmodifiableActionList()) {
            if (actor.hasCapability(DinosaurCapabilities.STEGOSAUR) && action instanceof EatFruitAction) {
                return action;
            }
            if (actor.hasCapability(DinosaurCapabilities.BRACHIOSAUR) && action instanceof PickFruitAction) {
                return action;
            }
            if (actor.hasCapability(DinosaurCapabilities.PTERODACTYL) && action instanceof EatFishAction) {
                return action;
            }
        }

        ArrayList<Location> foodLocations = null;
        if (actor.hasCapability(DinosaurCapabilities.STEGOSAUR)) {
            foodLocations = HungryBehaviour.getSuitableFruitLocations(map, FruitCapabilities.ON_FLOOR);
        } else if (actor.hasCapability(DinosaurCapabilities.BRACHIOSAUR)) {
            foodLocations = HungryBehaviour.getSuitableFruitLocations(map, FruitCapabilities.IN_TREE);
        } else if (actor.hasCapability(DinosaurCapabilities.PTERODACTYL)) {
            foodLocations = HungryBehaviour.getSuitableMeatLocations(map, FoodTypeCapabilities.SEAFOOD);
        }
        // Checks if there are no food items in the map
        if (foodLocations == null || foodLocations.size() == 0) {
            return null;
        }

        // Determines the closest food location and creates a follow behaviour for
        // dinosaur to move to the item
        Location closestFoodLocation = FindNearestLocation.closestLocation(actor, foodLocations, map);
        LocationFollowBehaviour followBehaviour = new LocationFollowBehaviour(closestFoodLocation);
        return followBehaviour.getAction(actor, map, actions);
    }
}
