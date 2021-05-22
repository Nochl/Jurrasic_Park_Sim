package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.NumberRange;
import game.enums.MapCapabilities;

/**
 * Implements a class which adds an edge capability to top/bottom ground objects
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see GameMap
 * @see edu.monash.fit2099.engine.World
 */
public class MapSwitch {

    /**
     * Adds capability of EDGE to the top/bottom row ground objects of a GameMap
     * @param startmap GameMap object to add ground capabilties
     * @param position The position ("top" or "bottom") where the next map will be
     */
    public static void addSwitch(GameMap startmap, String position) {
        int y = 0;
        int x = startmap.getXRange().max();

        if (position.equals("top")) {
            y = 0;
        } else if (position.equals("bottom")) {
            y = startmap.getYRange().max();
        }

        for (int i = 0; i < x; i++) {
            startmap.at(i, y).getGround().addCapability(MapCapabilities.EDGEMAP);

        }
    }
}
