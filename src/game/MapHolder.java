package game;

import edu.monash.fit2099.engine.GameMap;

import java.util.ArrayList;

/**
 * Implements a class that holds a list of GameMaps available in the world
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see GameMap
 * @see edu.monash.fit2099.engine.World
 */
public class MapHolder {
    /**
     * An Array list of Game Maps
     */
    private static ArrayList<GameMap> maps = new ArrayList<>();

    /**
     * Adds a new Game Map to maps
     * @param map A GameMap class objecy
     */
    public static void addMap(GameMap map){
        if (!maps.contains(map)) {
            maps.add(map);
        }
    }

    /**
     * Get another map that is not given map
     * @param map A GameMap class object that denotes the game map the player is on
     * @return a GameMap the player is not currently on
     */
    public static GameMap getOtherMap(GameMap map){
        for (GameMap nextmap:maps){
            if (nextmap != map){
                return nextmap;
            }
        }
        return null;
    }

    /**
     * Gets a list of all of Game Maps
     * @return an Array list of Game Maps
     */
    public static ArrayList<GameMap> getMaps() {
        return maps;
    }
}
