package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.FindNearestLocation;
import game.actions.AttackAction;
import game.actions.EatMeatAction;
import game.enums.DinosaurCapabilities;
import game.enums.FoodTypeCapabilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements a hunting behaviour that is used by Allosaurs to hunt and attack other dinosaurs
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 5.0.0
 * @see game.dinosaur.Allosaur
 * @see FindNearestLocation
 */
public class HuntingBehaviour implements Behaviour {
    protected Behaviour followBehaviour;
    public HuntingBehaviour() {
        followBehaviour = null;
    }

    @Override
    public Action getAction(Actor actor, GameMap map, Actions actions) {
        for (Action action : actions.getUnmodifiableActionList()) {
            if (action instanceof EatMeatAction || action instanceof AttackAction) {

                followBehaviour = null;
                return action;
            }
        }
        if (followBehaviour != null) {
            return followBehaviour.getAction(actor, map, actions);
        }
        ArrayList<Actor> suitableActors = HungryBehaviour.getSuitableDinosaurs(actor, map, DinosaurCapabilities.STEGOSAUR);
        ArrayList<Location> suitableFoodLocations = HungryBehaviour.getSuitableMeatLocations(map, FoodTypeCapabilities.MEAT);
        if (suitableActors.size() == 0 && suitableFoodLocations.size() == 0) {
            return new DoNothingAction();
        }
        followBehaviour = getFollowBehaviour(actor, map, suitableActors, suitableFoodLocations);
        return followBehaviour.getAction(actor, map, actions);
    }

    private Behaviour getFollowBehaviour(Actor actor, GameMap map,
                                         ArrayList<Actor> preys, ArrayList<Location> foodLocations) {
        boolean foundPrey = false;
        boolean foundFood = false;
        Actor prey = null;
        Location foodLocation = null;

        if (preys.size() > 0) {
            prey = FindNearestLocation.closestActor(actor, preys, map);
            foundPrey = true;
        }
        if (foodLocations.size() > 0) {
            foodLocation = FindNearestLocation.closestLocation(actor, foodLocations, map);
            foundFood = true;
        }
        if (!foundPrey && foundFood) {
            return new LocationFollowBehaviour(foodLocation);
        } else if (foundPrey && !foundFood) {
            return new FollowBehaviour(prey);
        }
        ArrayList<Location> allFoodLocations = new ArrayList<>();
        allFoodLocations.add(map.locationOf(prey));
        allFoodLocations.add(foodLocation);
        Location closestFoodSource = FindNearestLocation.closestLocation(actor, allFoodLocations, map);
        if (closestFoodSource.getActor() == null) {
            return new LocationFollowBehaviour(closestFoodSource);
        } else {
            return new FollowBehaviour(closestFoodSource.getActor());
        }
    }

    private Item checkSurroundingSuitableFoods(Location actorLocation, FoodTypeCapabilities foodTypeCapability) {
        for (Exit exit : actorLocation.getExits()) {
            Location nearbyLocation = exit.getDestination();
            List<Item> items = nearbyLocation.getItems();
            for (Item meat : items) {
                if (meat.hasCapability(FoodTypeCapabilities.MEAT)) {
                    return meat;
                }
            }
        }
        return null;
    }

    private Actor checkSurroundingSuitableDinosaurs(Location actorLocation, DinosaurCapabilities dinosaurCapability) {
        for (Exit exit : actorLocation.getExits()) {
            Location nearbyLocation = exit.getDestination();
            Actor actor = nearbyLocation.getActor();
            if (actor != null && actor.hasCapability(dinosaurCapability)) {
                return actor;
            }
        }
        return null;
    }
}
