package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.behaviour.Behaviour;

/**
 * Implements a behaviour in which the actor follows location
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see Location
 * @see Behaviour
 */
public class LocationFollowBehaviour implements Behaviour {

    /**
     * A Location in which we want to go to
     */
    private Location location;

    /**
     * Constructor
     * @param location A Location that we want to go to
     */
    public LocationFollowBehaviour(Location location) {
        this.location = location;
    }

    /**
     * Gets an action for actor to follow the location
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @param actions an Actions object denoting all the allowable actions near the player
     * @return an Action object that which allows the player to follow the location
     */
    @Override
    public Action getAction(Actor actor, GameMap map, Actions actions) {
        if(!map.contains(actor))
            return null;

        Location here = map.locationOf(actor);

        int currentDistance = distance(here, location);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, location);
                if (newDistance < currentDistance) {
                    return new MoveActorAction(destination, exit.getName());
                }
            }
        }

        return null;
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

}
