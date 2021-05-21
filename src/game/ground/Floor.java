package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.NextMapAction;
import game.enums.MapCapabilities;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	public Floor() {
		super('_');
	}

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions actions = new Actions();
//		if (hasCapability(MapCapabilities.EDGEMAP)) {
//			actions.add(new NextMapAction());
//		}
		return actions;
	}
}
