package game;

import edu.monash.fit2099.engine.GameMap;

import java.util.ArrayList;

public class MapHolder {
    private static ArrayList<GameMap> maps = new ArrayList<>();

    public static void addMap(GameMap map){
        maps.add(map);
    }

    public static GameMap getOtherMap(GameMap map){
        for (GameMap nextmap:maps){
            if (nextmap != map){
                return nextmap;
            }
        }
        return null;
    }


}
