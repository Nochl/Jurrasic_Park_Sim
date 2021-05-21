package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.NumberRange;
import game.enums.MapCapabilities;

public class MapSwitch {

    public MapSwitch() {
    }

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
