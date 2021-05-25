package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.NextMapAction;
import game.enums.MapCapabilities;

/**
 * A class that represents the floor inside a building.
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see Ground
 */
public class Floor extends Ground {

	/**
	 * Constructor
	 */
	public Floor() {
		super('_');
	}

}
