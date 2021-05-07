package game;

import edu.monash.fit2099.engine.*;

public class LocationFollowBehaviour implements Behaviour {

    private Location location;

    public LocationFollowBehaviour(Location location) {
        this.location = location;
    }

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
