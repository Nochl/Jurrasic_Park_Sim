package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.FindNearestLocation;
import game.actions.DrinkLakeAction;
import game.enums.DinosaurCapabilities;
import game.enums.GroundTypeCapabilities;
import game.enums.ThirstCapabilities;
import game.ground.Lake;

import java.util.ArrayList;

/**
 * Implements Thirsty Behaviour for Dinosaurs to find and drink water when they are thristy
 *
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 2.0.0
 * @see game.dinosaur.Dinosaur
 * @see ThirstCapabilities
 * @see FindNearestLocation
 * @see Behaviour
 */
public class ThirstyBehaviour implements Behaviour{
    ArrayList<Location> lakeLocation = new ArrayList<>();

    /**
     * Moves actor towards a lake and drinks if its close enough
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @param actions an Actions object denoting all the allowable actions near the player
     * @return Either a MoveActorAction if its not close to a water source, or DrinkLakeAction if it is
     */
    @Override
    public Action getAction(Actor actor, GameMap map, Actions actions) {
        // Check if actor is thirsty
        if (!actor.hasCapability(ThirstCapabilities.THIRSTY)) {
            return null;
        }

        // Check if on top of lake (for Pterodactyls) ****** fix to differentiate action for dinosaurs
        if (actor.hasCapability(DinosaurCapabilities.PTERODACTYL) &&
                map.locationOf(actor).getGround().hasCapability(GroundTypeCapabilities.LAKE)) {
            return new DrinkLakeAction((Lake) map.locationOf(actor).getGround());
        }

        // Check if next to lake (other dinosaurs)
        Location lake = null;
        for (Exit next: map.locationOf(actor).getExits()) {
            if (next.getDestination().getGround().hasCapability(GroundTypeCapabilities.LAKE)) {
                lake = next.getDestination();
            }
        }
        if (lake != null) {
            return new DrinkLakeAction((Lake) lake.getGround());
        }


        // Look for all lakes on the map
        for (int i = 0; i < map.getXRange().max(); i++){
            for (int j = 0; j < map.getYRange().max(); j++) {
                Location spot = map.at(i, j);
                if (spot.getGround().hasCapability(GroundTypeCapabilities.LAKE)) {
                    lakeLocation.add(spot);
                }
            }
        }

        // If there are no filled lakes
        if (lakeLocation.size() < 1) {
            return null;
        }

        // Find closest lake
        Location nearestLake = FindNearestLocation.closestLocation(actor, lakeLocation, map);

        // Go to closest location
        return new LocationFollowBehaviour(nearestLake).getAction(actor, map, actions);
    }
}
