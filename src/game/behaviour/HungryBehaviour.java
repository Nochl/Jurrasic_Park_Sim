package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.actions.PickFruitAction;
import game.enums.ActorMobilityCapabilities;
import game.enums.DinosaurCapabilities;
import game.enums.FoodTypeCapabilities;
import game.enums.FruitCapabilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements a static Hungry Behaviour class that contains useful methods
 * for Dinosaur Behaviours that relate to finding food
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 6.0.0
 * @see game.dinosaur.Dinosaur
 * @see GameMap
 * @see Actor
 * @see Behaviour
 */
public class HungryBehaviour {

    /**
     * Gets all the dinosaurs in the game map that have a certain dinosaur capability
     * @param thisActor an actor that is an instance of dinosaur
     * @param map a GameMap object that in which the actor is in
     * @param dinosaurCapability a DinosaurCapabilities instance
     * @return an Array List contains dinosaurs that have the capability
     */
    protected static ArrayList<Actor> getSuitableDinosaurs(
            Actor thisActor, GameMap map, DinosaurCapabilities dinosaurCapability, ActorMobilityCapabilities mobilityCapabily) {
        ArrayList<Actor> suitableActors = new ArrayList<>();
        NumberRange Xrange = map.getXRange();
        NumberRange YRange = map.getYRange();

        for (int x : Xrange) {
            for (int y : YRange) {
                Actor actor = map.getActorAt(map.at(x, y));
                if (actor != null && actor != thisActor && actor.hasCapability(dinosaurCapability) && actor.hasCapability(mobilityCapabily)) {
                    suitableActors.add(actor);
                }
            }
        }
        return suitableActors;
    }

    /**
     * Gets all the locations that contain fruit items that have a certain capability
     * @param map a GameMap object that in which the actor is in
     * @param fruitCapability capability of fruit item
     * @return an array list of locations that contain a suitable fruit item
     */
    protected static ArrayList<Location> getSuitableFruitLocations(GameMap map, FruitCapabilities fruitCapability) {
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
                } else if (containsSuitableItem(location, FoodTypeCapabilities.VEGETABLE)) {
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
     * Checks if the given location contains items that have Food Type capability
     * @param location A Location class object
     * @param foodType A FoodTypeCapabilities object
     * @return a boolean True if the location contains a FoodTypeCapabilities item, else False
     */
    protected static boolean containsSuitableItem(Location location, FoodTypeCapabilities foodType) {

        for (Item item : location.getItems()) {
            if (item.hasCapability(foodType)) {
                return true;
            }
        }
        return false;
    }

    protected static Item CheckStandingOnSuitableFood(Location actorLocation, FoodTypeCapabilities foodTypeCapability) {
        List<Item> items = actorLocation.getItems();
        for (Item item : items) {
            if (item.hasCapability(foodTypeCapability)) {
                return item;
            }
        }
        return null;
    }

    protected static Actor checkSurroundingSuitableDinosaurs(Location actorLocation, DinosaurCapabilities dinosaurCapability) {
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
