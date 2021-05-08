package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.FindNearestLocation;
import game.actions.EatFruitAction;
import game.enums.FoodTypeCapabilities;

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
public class ScavengingBehaviour extends HungryBehaviour {

    @Override
    public Action getAction(Actor actor, Actions actions, GameMap map) {
        for (Action action : actions.getUnmodifiableActionList()) {
            if (action instanceof EatFruitAction) {
                followBehaviour = null;
                return action;
            }
        }
        if (followBehaviour != null) {
            return followBehaviour.getAction(actor, actions, map);
        }

        ArrayList<Location> foodLocations = getSuitableFoodLocations(map, FoodTypeCapabilities.VEGETABLE);
        // Checks if there are no food items in the map
        if (foodLocations.size() == 0) {
            return new DoNothingAction();
        }

        // Determines the closest food location and creates a follow behaviour for
        // dinosaur to move to the item
        Location closestFoodLocation = FindNearestLocation.closestLocation(actor, foodLocations, map);
        followBehaviour = new LocationFollowBehaviour(closestFoodLocation);
        return followBehaviour.getAction(actor, actions, map);
    }
}
