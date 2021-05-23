package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.FindNearestLocation;
import game.enums.DinosaurCapabilities;
import game.enums.GroundTypeCapabilities;

import java.util.ArrayList;

/**
 * Implements a behaviour that enables a Dinosaur to go to and drink from a lake
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.3
 * @see game.dinosaur.Dinosaur
 * @see Behaviour
 * @see game.ground.Lake
 */
public class ThirstyBehaviour implements Behaviour{

    /**
     * A LocationFollowBehaviour class object that allows the dinosaur to move to the closest
     * Lake
     */
    LocationFollowBehaviour followLake;

    /**
     * Constructor
     */
    ThirstyBehaviour() {
        followLake = null;
    }

    /**
     * Returns an action which would make the actor drink from a lake if they
     * are a dinosaur and are in the near vicinity of a lake. If there is no
     * nearby lake, returns an action which will move dinosaur to closest the lake.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @param actions an Actions object denoting all the allowable actions near the player
     * @return an Action object denoting the next action the actor should do when they are thirsty
     */
    @Override
    public Action getAction(Actor actor, GameMap map, Actions actions) {
        Ground actorGround = map.locationOf(actor).getGround();
        if (actor.hasCapability(DinosaurCapabilities.PTERODACTYL) && actorGround.hasCapability(GroundTypeCapabilities.LAKE)) {
            followLake = null;
            Actions lakeActions = actorGround.allowableActions(actor, map.locationOf(actor), "");
            return lakeActions.get(0);
        }
        Ground potentialLakeGround = checkSurroundingForLakes(map.locationOf(actor), map);
        if (potentialLakeGround != null) {
            followLake = null;
            Actions lakeActions = potentialLakeGround.allowableActions(actor, map.locationOf(actor), "");
            return lakeActions.get(0);
        }
        // Checks if the dinosaur is currently moving to a lake
        if (followLake != null) {
            return followLake.getAction(actor, map, actions);
        }
        // Gets all the lake locations in map
        ArrayList<Location> lakeLocations = getMapLakes(map);
        // Checks if there are no lakes in the game map
        if (lakeLocations.size() == 0) {
            // Actor does nothing if there are no lakes
            return new DoNothingAction();
        }
        // Determines the closest lake to the dinosaur
        Location closestLakeLocation = FindNearestLocation.closestLocation(actor, lakeLocations, map);
        // Initialised followLake for dinosaur to move to the lake
        followLake = new LocationFollowBehaviour(closestLakeLocation);
        return followLake.getAction(actor, map, actions);
    }

    /**
     * Checks the actor's surrounding locations if they are lakes. Returns a lake object if true.
     * @param actorLocation Location object denoting the current location of actor
     * @param map A GameMap object that denotes the map the actor is on
     * @return A Ground object that is an instance of lake. If there are no lakes in map, returns null
     */
    private Ground checkSurroundingForLakes(Location actorLocation, GameMap map) {
        for (Exit exit : actorLocation.getExits()) {
            Location nearbyLocation = exit.getDestination();
            if (nearbyLocation.getGround().hasCapability(GroundTypeCapabilities.LAKE)) {
                return nearbyLocation.getGround();
            }
        }
        return null;
    }

    /**
     * Gets a list of all lake locations in the game map
     * @param map A GameMap object denoting the map the actor is currently on
     * @return an ArrayList of Locations contains the list of all lake locations in map
     */
    private ArrayList<Location> getMapLakes(GameMap map) {
        ArrayList<Location> lakeLocations = new ArrayList<>();
        NumberRange Xrange = map.getXRange();
        NumberRange YRange = map.getYRange();
        for (int x : Xrange) {
            for (int y : YRange) {
                Location location = map.at(x, y);
                Ground locationGround = location.getGround();
                // Checks if the location has a lake as a ground
                if (locationGround.hasCapability(GroundTypeCapabilities.LAKE)) {
                    lakeLocations.add(location);
                }
            }
        }
        return lakeLocations;
    }
}
