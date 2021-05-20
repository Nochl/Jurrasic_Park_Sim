package game.consumable;

import game.enums.FoodTypeCapabilities;

/**
 * Implements a fish object in game world that is able to be eaten by Pterodactyls.
 * Has a food capability of SEAFOOD
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see FoodTypeCapabilities
 * @see Pterodactyl
 */
public class Fish extends Consumable{
    /**
     * Constructor
     */
    public Fish() {
        super("fish", '*', 0, 0, 0, 0, 10);
        addCapability(FoodTypeCapabilities.SEAFOOD);
    }

}
