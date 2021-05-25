package game.behaviour;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.behaviour.Behaviour;

/**
 * Implements a Behaviour in which actor will randomly move around the map
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see Behaviour
 * @see Random
 */
public class WanderBehaviour implements Behaviour {

	/**
	 * A Random object
	 */
	private Random random = new Random();


	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map, Actions actions) {
		ArrayList<Action> actions2 = new ArrayList<>();

		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
            	actions2.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }
		
		if (!actions2.isEmpty()) {
			return actions2.get(random.nextInt(actions2.size()));
		}
		else {
			return null;
		}

	}

}
