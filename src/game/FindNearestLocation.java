package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Map;

/**
 * Implements a class that determines the closest actor and closest location
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 5.0.0
 * @see Actor
 * @see Location
 * @see Location
 */
public class FindNearestLocation {

    /**
     * Method which finds the closest Actor in a given list
     * @param thisActor Current actor in focus
     * @param actorList List of other actors
     * @param map Current gamemap
     * @return closest Actor to thisActor from actorLost
     */
    public static Actor closestActor(Actor thisActor, ArrayList<Actor> actorList, GameMap map) {
        Actor closeActor = null;
        int minDistance = Integer.MAX_VALUE;
        Location thisActorLocation = map.locationOf(thisActor);

        for (Actor potentialActor:actorList){
            int distance = distance(thisActorLocation, map.locationOf(potentialActor));
                if (distance < minDistance){
                    closeActor = potentialActor;
                    minDistance = distance;
                }
            }
        return closeActor;
    }

    /**
     * Method which finds the closest Location to thisActor in a given list
     * @param thisActor Current actor in focus
     * @param locationList List of Locations
     * @param map Current gamemap
     * @return closest Locaiton to thisActor from locationList
     */
    public static Location closestLocation(Actor thisActor, ArrayList<Location> locationList, GameMap map) {
        Location closeLocation = null;
        int minDistance = Integer.MAX_VALUE;
        Location thisActorLocation = map.locationOf(thisActor);

        for (Location potentialLocation:locationList){
            int distance = distance(thisActorLocation, potentialLocation);
            if (distance < minDistance){
                closeLocation = potentialLocation;
                minDistance = distance;
            }
        }
        return closeLocation;
    }


    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private static int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
