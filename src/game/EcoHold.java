package game;

import edu.monash.fit2099.engine.Actor;

import java.util.HashMap;

public class EcoHold {
    public static HashMap<Actor, Ecopoints> ecopoints = new HashMap<>();

//    public EcoHold() {}

    public static void addWorldEco(int amount){
        for (Actor player : ecopoints.keySet()) {
            Ecopoints eco = ecopoints.get(player);
            eco.addPoints(amount);
        }
    }

    public static void addPlayerEco(Player player){
        ecopoints.put(player, player.getEcopoints());
    }

    public static Ecopoints getPlayerEco(Actor player) {
        return ecopoints.get(player);
    }
}
