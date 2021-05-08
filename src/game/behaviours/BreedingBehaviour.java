package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.FindNearestLocation;
import game.actions.MatingAction;
import game.enums.Gender;
import game.enums.Mateable;

import java.util.ArrayList;

public class BreedingBehaviour implements Behaviour {

    public BreedingBehaviour() {
    }

    @Override
    public Action getAction(Actor actor, Actions actions, GameMap map) {
        if (!actor.hasCapability(Mateable.MATEABLE)){
            return null;
        }

        ArrayList<Actor> actors = new ArrayList<>();
        Boolean hasMate = Boolean.FALSE;
        Action mateAction = null;
        for (Action act:actions) {
            if (act instanceof MatingAction){
                hasMate = Boolean.TRUE;
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
                    if (potential.hasCapability(Mateable.MATEABLE)) {
                        if (actor.hasCapability(Gender.MALE) && potential.hasCapability(Gender.FEMALE))
                            actors.add(map.getActorAt(map.at(xvalue, yvalue)));
                        else if (actor.hasCapability(Gender.MALE) && potential.hasCapability(Gender.FEMALE))
                            actors.add(map.getActorAt(map.at(xvalue, yvalue)));
                    }
                }
            }

            Actor closest = FindNearestLocation.closestActor(actor, actors, map);

            Behaviour behaviour = new FollowBehaviour(closest);
            return behaviour.getAction(actor, actions, map);
        }
    }
}
