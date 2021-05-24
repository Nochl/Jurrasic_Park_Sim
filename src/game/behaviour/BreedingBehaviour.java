package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.FindNearestLocation;
import game.actions.MatingAction;
import game.dinosaur.Dinosaur;
import game.enums.DinosaurCapabilities;
import game.enums.Gender;
import game.enums.Mateable;

import java.util.ArrayList;

public class BreedingBehaviour implements Behaviour {

    public BreedingBehaviour() {
    }

    @Override
    public Action getAction(Actor actor, GameMap map, Actions actions) {
        if (!actor.hasCapability(Mateable.MATEABLE)){
            return null;
        }

        ArrayList<Actor> actors = new ArrayList<>();
        boolean hasMate = false;
        Action mateAction = null;
        for (Action act:actions) {
            if (act instanceof MatingAction){
                hasMate = true;
                mateAction = act;
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
        return false;
    }
}
