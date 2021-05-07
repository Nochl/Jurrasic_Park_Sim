package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Map;

public class FindNearestLocation {
    public static Actor closetActor(Actor thisActor, ArrayList<Actor> actorList, GameMap map) {
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

    public static Location closetLocation(Actor thisActor, ArrayList<Location> locationList, GameMap map) {
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
