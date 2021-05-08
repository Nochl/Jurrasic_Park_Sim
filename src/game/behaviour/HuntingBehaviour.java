package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.behaviour.*;
import game.FindNearestLocation;
import game.actions.AttackAction;
import game.actions.EatMeatAction;
import game.enums.DinosaurCapabilities;
import game.enums.FoodTypeCapabilities;

import java.util.ArrayList;

public class HuntingBehaviour extends HungryBehaviour {
    protected Behaviour followBehaviour = null;

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
        ArrayList<Actor> suitableActors = getSuitableDinosaurs(actor, map, DinosaurCapabilities.STEGOSAUR);
        ArrayList<Location> suitableFoodLocations = getSuitableFoodLocations(map, FoodTypeCapabilities.MEAT);
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
}
