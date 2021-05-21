package game.ground;

import edu.monash.fit2099.engine.*;
import game.actions.NextMapAction;
import game.actions.PickFruitAction;
import game.enums.MapCapabilities;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');
	}

	@Override
	public void tick(Location location) {
		double random = Math.random();
		super.tick(location);

		int isBush = 0;
		int isTree = 0;

		for (Exit exit : location.getExits()) {
			Location destination = exit.getDestination();
			if (destination.getGround() instanceof Bush) {
				isBush++;
			} else if (destination.getGround() instanceof Tree) {
				isTree++;
			}
		}

		/* bush spawn conditional to if location is next to bush */
		if (isTree ==0) {
			if (isBush >= 2) {
				if (random <= 0.1) {
					location.setGround(new Bush());
				}
			}
			else {
				if (random <= 0.001) {
					location.setGround(new Bush());
				}
			}

		}

	}

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions actions = new Actions();
//		if (hasCapability(MapCapabilities.EDGEMAP)) {
//			actions.add(new NextMapAction());
//		}
//		else {System.out.println("rip");}
		return actions;
	}
}
