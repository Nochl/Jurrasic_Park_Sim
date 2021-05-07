package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.FindNearestLocation;
import game.actions.EatFruitAction;
import game.enums.FoodTypeCapabilities;

import java.util.ArrayList;

public class ScavengingBehaviour implements Behaviour {
    LocationFollowBehaviour followTarget = null;

    @Override
    public Action getAction(Actor actor, Actions actions, GameMap map) {
        for (Action action : actions.getUnmodifiableActionList()) {
            if (action instanceof EatFruitAction) {
                followTarget = null;
                return action;
            }
        }
        if (followTarget != null) {
            return followTarget.getAction(actor, actions, map);
        }
        NumberRange Xrange = map.getXRange();
        NumberRange YRange = map.getYRange();

        ArrayList<Location> foodLocations = new ArrayList<>();
        for (int x = 0; x < Xrange.max(); x++) {
            for (int y = 0; y < YRange.max(); y++) {
                Location location = map.at(x, y);
                if (containsSuitableItem(location)) {
                    foodLocations.add(location);
                }
            }
        }
        Location closestFoodLocation = FindNearestLocation.closestLocation(actor, foodLocations, map);
        followTarget = new LocationFollowBehaviour(closestFoodLocation);
        return followTarget.getAction(actor, actions, map);
    }

    private boolean containsSuitableItem(Location location) {
        for (Item item : location.getItems()) {
            if (item.hasCapability(FoodTypeCapabilities.VEGETABLE)) {
                return true;
            }
        }
        return false;
    }
}
