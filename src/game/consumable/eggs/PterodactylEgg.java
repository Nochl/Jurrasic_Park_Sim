package game.consumable.eggs;

import game.EcoHold;
import game.dinosaur.Pterodactyl;
import game.dinosaur.Dinosaur;

/**
 * Implements a Pterodactyl egg that extends from Egg abstract class
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 3.0.0
 * @see Egg
 * @see Pterodactyl
 */
public class PterodactylEgg extends Egg {

    /**
     * Constructor for BrachiosaurEgg
     */
    public PterodactylEgg() {
        super("Pterodactyl egg", 30);
    }

    /**
     * Creates an instance of Pterodactyl
     *
     * @return a new instance of Pterodactyl class object
     */
    @Override
    Dinosaur createDinosaur() {
        pic.println("A Pterodactyl has hatched!!!");
        return new Pterodactyl("Pterodactyl", true);
    }

    /**
     * Increments world ecopoints
     */
    @Override
    void increaseEcoPoints() {
        EcoHold.addWorldEco(1000);
    }
}