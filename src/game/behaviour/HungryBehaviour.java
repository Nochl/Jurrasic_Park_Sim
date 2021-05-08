package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.actions.PickFruitAction;
import game.enums.DinosaurCapabilities;
import game.enums.FoodTypeCapabilities;
import game.enums.FruitCapabilities;

import java.util.ArrayList;

public class HungryBehaviour {
    protected Behaviour followBehaviour = null;

    protected static ArrayList<Actor> getSuitableDinosaurs(Actor thisActor, GameMap map, DinosaurCapabilities dinosaurCapability) {
        ArrayList<Actor> suitableActors = new ArrayList<>();
        NumberRange Xrange = map.getXRange();
        NumberRange YRange = map.getYRange();

        for (int x : Xrange) {
            for (int y : YRange) {
                Actor actor = map.getActorAt(map.at(x, y));
                if (actor != null && actor != thisActor && actor.hasCapability(dinosaurCapability)) {
                    suitableActors.add(actor);
                }
            }
        }
        return suitableActors;
    }

    protected static ArrayList<Location> getSuitableFruitLocations(GameMap map, FoodTypeCapabilities foodType, FruitCapabilities fruitCapability) {
        ArrayList<Location> foodLocations = new ArrayList<>();
        NumberRange Xrange = map.getXRange();
        NumberRange YRange = map.getYRange();
        for (int x : Xrange) {
            for (int y : YRange) {
                Location location = map.at(x, y);
                if (fruitCapability == FruitCapabilities.IN_TREE) {
                    Actions actions = location.getGround().allowableActions(location.getActor(), location, "");
                    for (Action action : actions) {
                        if (action instanceof PickFruitAction) {
                            foodLocations.add(location);
                        }
                    }
                } else if (containsSuitableItem(location, foodType)) {
                    foodLocations.add(location);
                }
            }
        }
        return foodLocations;
    }

    protected static ArrayList<Location> getSuitableMeatLocations(GameMap map, FoodTypeCapabilities foodType) {
        ArrayList<Location> foodLocations = new ArrayList<>();
        NumberRange Xrange = map.getXRange();
        NumberRange YRange = map.getYRange();
        for (int x : Xrange) {
            for (int y : YRange) {
                Location location = map.at(x, y);
                if (containsSuitableItem(location, foodType)) {
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
    protected static boolean containsSuitableItem(Location location, FoodTypeCapabilities foodType) {

        for (Item item : location.getItems()) {
            if (item.hasCapability(foodType)) {
                return true;
            }
        }
        return false;
    }

}
