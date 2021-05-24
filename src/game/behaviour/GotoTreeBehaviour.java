package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.FindNearestLocation;
import game.dinosaur.Pterodactyl;
import game.enums.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Implements GoToTreeBehaviour which moves dinosaur to closest tree if its on the ground
 *
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 2.0.0
 * @see game.dinosaur.Dinosaur
 * @see ThirstCapabilities
 * @see FindNearestLocation
 * @see Behaviour
 */
public class GotoTreeBehaviour implements Behaviour  {
    /**
     * Holds all location of trees
     */
    private ArrayList<Location> treeLocations = new ArrayList<>();

    @Override
    public Action getAction(Actor actor, GameMap map, Actions actions) {
        // Returns null if dinosaur isn't a Pterodactyl or is flying
        if (!actor.hasCapability(DinosaurCapabilities.PTERODACTYL) || !actor.hasCapability(ActorMobilityCapabilities.WALK)) {
            return null;
        }

        // Look for all trees on the map
        for (int i = 0; i < map.getXRange().max(); i++){
            for (int j = 0; j < map.getYRange().max(); j++) {
                Location spot = map.at(i, j);
                if (spot.getGround().hasCapability(GroundTypeCapabilities.TREE)) {
                    treeLocations.add(spot);
                }
            }
        }

        // If there are no trees
        if (treeLocations.size() < 1) {
            return null;
        }

        // Find closest lake
        Location nearestTree = FindNearestLocation.closestLocation(actor, treeLocations, map);

        // Go to closest location
        return new LocationFollowBehaviour(nearestTree).getAction(actor, map, actions);
    }

}
