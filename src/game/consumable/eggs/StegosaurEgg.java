package game.consumable.eggs;

import game.EcoHold;
import game.dinosaur.Allosaur;
import game.dinosaur.Dinosaur;
import game.dinosaur.Stegosaur;

/**
 * Implements Stegosaur egg that extends from Egg abstract class
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 3.0.0
 * @see Egg
 * @see Stegosaur
 */
public class StegosaurEgg extends Egg {
    /**
     * Constructor method for StegosaurEgg
     */
    public StegosaurEgg() {
        super("stegosaur egg", 15);
    }

    /**
     * Creates a new Stegosaur object
     * @return a new instance of Stegosaur class object
     */
    @Override
    Dinosaur createDinosaur() {
        pic.println("A Stegosaur has hatched!!!");
        return new Stegosaur("stegosaur", true);
    }

    /**
     * increments ecopoints
     */
    @Override
    void increaseEcoPoints() {
        EcoHold.addWorldEco(100);
    }
}
