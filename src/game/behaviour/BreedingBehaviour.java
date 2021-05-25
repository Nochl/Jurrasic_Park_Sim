package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.FindNearestLocation;
import game.actions.MatingAction;
import game.dinosaur.Dinosaur;
import game.enums.*;

import java.util.ArrayList;

/**
 * Implements Breeding Behaviour for Dinosaurs to find a mate and create an egg in the female
 *
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 2.0.0
 * @see game.dinosaur.Dinosaur
 * @see FindNearestLocation
 * @see Behaviour
 */
public class BreedingBehaviour implements Behaviour {
    /**
     * Holds all location of actors
     */
    private ArrayList<Actor> actors = new ArrayList<>();

    /**
     * gets an actions according to breeding behaviour
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @param actions an Actions object denoting all the allowable actions near the player
     * @return an Action according to breeding behaviour
     */
    @Override
    public Action getAction(Actor actor, GameMap map, Actions actions) {
        // Sees if dinosaur is capable of mating
        if (!actor.hasCapability(Mateable.MATEABLE)){
            return null;
        }

        // Checks if there is a MateAction available
        boolean hasMate = false;
        Action mateAction = null;
        for (Action act:actions) {
            if (act instanceof MatingAction){
                // extra check if dinosaur is a Pterodactyl
                if (actor.hasCapability(DinosaurCapabilities.PTERODACTYL)) {
                    // check if both actors are on a tree
                    if (map.locationOf(actor).getGround().hasCapability(GroundTypeCapabilities.TREE) &&
                            map.locationOf(((MatingAction) act).getTarget()).getGround().hasCapability(GroundTypeCapabilities.TREE)) {
                        // check if same species
                        if (sameDinosaur(actor, ((MatingAction) act).getTarget())) {
                            hasMate = true;
                            mateAction = act;
                        }
                    }
                }

                else {
                    // check if same species
                    if (sameDinosaur(actor, ((MatingAction) act).getTarget())) {
                        hasMate = true;
                        mateAction = act;
                    }
                }
            }
        }

        if (hasMate) {
            actor.removeCapability(Mateable.MATEABLE);
            return mateAction;
        }

        else {
            NumberRange x = map.getXRange();
            NumberRange y = map.getYRange();

            for (int xvalue : x) {
                for (int yvalue : y) {
                    Actor potential = map.getActorAt(map.at(xvalue, yvalue));
                    if (potential != null) {
                        if (sameDinosaur(actor, potential)) {
                            if (potential.hasCapability(Mateable.MATEABLE)) {
                                if (actor.hasCapability(Gender.MALE) && potential.hasCapability(Gender.FEMALE))
                                    actors.add(map.getActorAt(map.at(xvalue, yvalue)));
                                else if (actor.hasCapability(Gender.MALE) && potential.hasCapability(Gender.FEMALE))
                                    actors.add(map.getActorAt(map.at(xvalue, yvalue)));
                            }
                        }
                    }
                }
            }

            Actor closest = FindNearestLocation.closestActor(actor, actors, map);

            Behaviour behaviour = new FollowBehaviour(closest);
            return behaviour.getAction(actor, map, actions);
        }
    }


    /**
     * A check to see if 2 dinosaurs are compatible (same species & opposite sex)
     * @param current An Actor object
     * @param potential An Actor object
     * @return a boolean true if actors are compatible, else false
     */
    public boolean sameDinosaur(Actor current, Actor potential){
        if (current.hasCapability(DinosaurCapabilities.BRACHIOSAUR) && potential.hasCapability(DinosaurCapabilities.BRACHIOSAUR)){
            return true;
        }
        else if (current.hasCapability(DinosaurCapabilities.STEGOSAUR) && potential.hasCapability(DinosaurCapabilities.STEGOSAUR)){
            return true;
        }
        else if (current.hasCapability(DinosaurCapabilities.ALLOSAUR) && potential.hasCapability(DinosaurCapabilities.ALLOSAUR)){
            return true;
        }
        else if (current.hasCapability(DinosaurCapabilities.PTERODACTYL) && potential.hasCapability(DinosaurCapabilities.PTERODACTYL)){
            return true;
        }
        return false;
    }
}
