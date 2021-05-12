package game.ground;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

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
}
