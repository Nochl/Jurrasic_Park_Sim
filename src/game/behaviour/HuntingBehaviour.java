package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.FindNearestLocation;
import game.actions.AttackAction;
import game.actions.EatMeatAction;
import game.enums.DinosaurCapabilities;
import game.enums.FoodTypeCapabilities;
import game.enums.HungryCapabilities;

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

    /**
     * A Behaviour object that is used to follow a certain food source
     */
    private Behaviour followBehaviour;

    /**
     * Constructor
     */
    public HuntingBehaviour() {
        followBehaviour = null;
    }

    /**
     * Tries to find/attack a suitable food source around it's vicinity. If not able to find one around
     * it will find and go to the nearest food source.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @param actions an Actions object that determines the surrounding actions available
     * @return an Action instance that denotes the action the actor can currently do
     */
    @Override
    public Action getAction(Actor actor, GameMap map, Actions actions) {
        // Returns null if Dinosaur is not hungry
        if (!actor.hasCapability(HungryCapabilities.HUNGRY)) {
            return null;
        }

        // Checks if Dinosaur is an Allosaur
        if (!actor.hasCapability(DinosaurCapabilities.ALLOSAUR)) {
            return null;
        }
        // Checks if the Allosaur Dinosaur is near a stegosaur
        Actor closePrey = HungryBehaviour.checkSurroundingSuitableDinosaurs(map.locationOf(actor), DinosaurCapabilities.STEGOSAUR);
        if (closePrey != null) {
            followBehaviour = null;
            return new AttackAction(closePrey);
        }

        System.out.println("Hunting Behaviour");

        // Checks if the Allosaur Dinosaur is standing on a food item
        Item closeFood = HungryBehaviour.CheckStandingOnSuitableFood(map.locationOf(actor), FoodTypeCapabilities.MEAT);
        if (closeFood != null) {
            followBehaviour = null;
            return new EatMeatAction(closeFood);
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

    /**
     * Initialises a FollowBehaviour class object that follows the closest suitable food source for actor
     * @param actor An Actor class object
     * @param map a GameMap object that is the current game map the actor is in
     * @param preys an Array list of Actors that the Dinosaur can attack
     * @param foodLocations an Array list of Locations that contain suitable food items
     * @return a Behaviour class object that the actor will follow
     */
    private Behaviour getFollowBehaviour(Actor actor, GameMap map,
                                         ArrayList<Actor> preys, ArrayList<Location> foodLocations) {
        boolean foundPrey = false;
        boolean foundFood = false;
        Actor prey = null;
        Location foodLocation = null;

        // Checks if there are suitable prey. If so, get the closest suitable prey
        if (preys.size() > 0) {
            prey = FindNearestLocation.closestActor(actor, preys, map);
            foundPrey = true;
        }

        // Checks if there are suitable food locations. If so, get the closest one
        if (foodLocations.size() > 0) {
            foodLocation = FindNearestLocation.closestLocation(actor, foodLocations, map);
            foundFood = true;
        }

        // Check if there is a food location
        if (!foundPrey && foundFood) {
            return new LocationFollowBehaviour(foodLocation);
        }
        // Checks if there is a suitable prey
        else if (foundPrey && !foundFood) {
            return new FollowBehaviour(prey);
        }

        // Determine whether the prey or food item is the closest food source
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
