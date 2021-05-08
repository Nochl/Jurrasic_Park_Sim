package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.FindNearestLocation;
import game.actions.EatFruitAction;
import game.actions.PickFruitAction;
import game.enums.DinosaurCapabilities;
import game.enums.FoodTypeCapabilities;
import game.enums.FruitCapabilities;

import java.util.ArrayList;

/**
 * Implements Scavenging Behaviour class that implements Behaviour interface.
 * Utilised by the Dinosaur class to find and get the closest item food source
 * within the game world
 *
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see game.dinosaur.Dinosaur
 * @see FoodTypeCapabilities
 * @see FindNearestLocation
 * @see EatFruitAction
 * @see Behaviour
 */
public class ScavengingBehaviour implements Behaviour {
    protected Behaviour followBehaviour;
    public ScavengingBehaviour() {
        followBehaviour = null;
    }

    @Override
    public Action getAction(Actor actor, GameMap map, Actions actions) {

        for (Action action : actions.getUnmodifiableActionList()) {
            if (action instanceof EatFruitAction || action instanceof PickFruitAction) {
                followBehaviour = null;
                return action;
            }
        }
        if (followBehaviour != null) {
            return followBehaviour.getAction(actor, map, actions);
        }

        ArrayList<Location> foodLocations = null;
        if (actor.hasCapability(DinosaurCapabilities.STEGOSAUR)) {
            foodLocations = HungryBehaviour.getSuitableFruitLocations(map, FoodTypeCapabilities.VEGETABLE, FruitCapabilities.ON_FLOOR);
        }
        // Checks if there are no food items in the map
        if (foodLocations == null || foodLocations.size() == 0) {
            return new DoNothingAction();
        }

        // Determines the closest food location and creates a follow behaviour for
        // dinosaur to move to the item
        Location closestFoodLocation = FindNearestLocation.closestLocation(actor, foodLocations, map);
        followBehaviour = new LocationFollowBehaviour(closestFoodLocation);
        return followBehaviour.getAction(actor, map, actions);
    }
}
