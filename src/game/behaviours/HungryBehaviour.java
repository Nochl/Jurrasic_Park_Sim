package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.enums.DinosaurCapabilities;
import game.enums.FoodTypeCapabilities;

import java.util.ArrayList;

public abstract class HungryBehaviour implements Behaviour {
    Behaviour followBehaviour = null;

    protected ArrayList<Actor> getSuitableDinosaurs(Actor thisActor, GameMap map, DinosaurCapabilities dinosaurCapability) {
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

    protected ArrayList<Location> getSuitableFoodLocations(GameMap map, FoodTypeCapabilities foodType) {
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
    protected boolean containsSuitableItem(Location location, FoodTypeCapabilities foodType) {
        for (Item item : location.getItems()) {
            if (item.hasCapability(foodType)) {
                return true;
            }
        }
        return false;
    }

}
