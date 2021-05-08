package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.FindNearestLocation;
import game.actions.AttackAction;
import game.actions.EatMeatAction;
import game.enums.DinosaurCapabilities;
import game.enums.FoodTypeCapabilities;

import java.util.ArrayList;

public class HuntingBehaviour extends HungryBehaviour {
    boolean isFollowingTarget = false;
    Behaviour followBehaviour = null;

    @Override
    public Action getAction(Actor actor, Actions actions, GameMap map) {
        for (Action action : actions.getUnmodifiableActionList()) {
            if (action instanceof EatMeatAction || action instanceof AttackAction) {
                return action;
            }
        }
        if (isFollowingTarget) {
            return followBehaviour.getAction(actor, actions, map);
        }
        ArrayList<Actor> suitableActors = getSuitableActors(actor, map);
        ArrayList<Location> suitableFoodLocations = getSuitableFoodLocations(map);
        if (suitableActors.size() == 0 && suitableFoodLocations.size() == 0) {
            return new DoNothingAction();
        }
        followBehaviour = getFollowBehaviour(actor, map, suitableActors, suitableFoodLocations);
        isFollowingTarget = true;
        return followBehaviour.getAction(actor, actions, map);
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

    private ArrayList<Actor> getSuitableActors(Actor actor, GameMap map) {
        ArrayList<Actor> suitableActors = new ArrayList<>();
        NumberRange Xrange = map.getXRange();
        NumberRange YRange = map.getYRange();

        for (int x : Xrange) {
            for (int y : YRange) {
                Actor mapActor = map.getActorAt(map.at(x, y));
                if (mapActor != null && mapActor != actor && mapActor.hasCapability(DinosaurCapabilities.STEGOSAUR)) {
                    suitableActors.add(mapActor);
                }
            }
        }
        return suitableActors;
    }

    private ArrayList<Location> getSuitableFoodLocations(GameMap map) {
        ArrayList<Location> foodLocations = new ArrayList<>();
        NumberRange Xrange = map.getXRange();
        NumberRange YRange = map.getYRange();
        for (int x : Xrange) {
            for (int y : YRange) {
                Location location = map.at(x, y);
                if (containsSuitableItem(location)) {
                    foodLocations.add(location);
                }
            }
        }
        return foodLocations;
    }

    /**
     * Checks if the given location contains items that have VEGETABLE Food Type capability
     *
     * @param location A Location class object
     * @return a boolean True if the location contains a VEGETABLE item, else False
     */
    private boolean containsSuitableItem(Location location) {
        for (Item item : location.getItems()) {
            if (item.hasCapability(FoodTypeCapabilities.MEAT)) {
                return true;
            }
        }
        return false;
    }
}
