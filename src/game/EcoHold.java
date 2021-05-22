package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

import java.util.HashMap;

/**
 * Implements a class which holds all the ecopoint objects of all players
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 5.0.0
 */
public class EcoHold {
    /**
     * Static HashMap holding each actor and its ecopoints
     */
    public static HashMap<Actor, Ecopoints> ecopoints = new HashMap<>();

    /**
     * Static method to give ecopoints to all players
     * @param amount integer amount of ecopoints
     */
    public static void addWorldEco(int amount){
        for (Actor player : ecopoints.keySet()) {
            Ecopoints eco = ecopoints.get(player);
            eco.addPoints(amount);
        }
    }

    /**
     * Add player to the ecopoints holder
     * @param player a Player in the game
     */
    public static void addPlayerEco(Player player){
        ecopoints.put(player, player.getEcopoints());
    }

    /**
     * Returns ecopoints
     * @param player
     * @return Ecopoints object from the player
     */
    public static Ecopoints getPlayerEco(Actor player) {
        return ecopoints.get(player);
    }
}
