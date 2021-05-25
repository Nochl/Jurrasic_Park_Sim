package game.ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * Implements a wall ground object
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see Ground
 */
public class Wall extends Ground {

	/**
	 * Constructor
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Denotes if the actor can enter this ground
	 * @param actor the Actor to check
	 * @return a boolean false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Denotings if ground can block thrown objects
	 * @return a boolean true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
