package game.consumable.eggs;

import game.EcoHold;
import game.dinosaur.Allosaur;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Dinosaur;

/**
 * Implements a brachiosaur egg that extends from Egg abstract class
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 3.0.0
 * @see Egg
 * @see Brachiosaur
 */
public class BrachiosaurEgg extends Egg {

    /**
     * Constructor for BrachiosaurEgg
     */
    public BrachiosaurEgg() {
        super("brachiosaur egg", 30);
    }

    /**
     * Creates an instance of Brachiosaur
     * @return a new instance of Brachiosaur class object
     */
    @Override
    protected Dinosaur createDinosaur() {
        pic.println("A Brachiosaur has hatched!!!");
        return new Brachiosaur("brachiosaur", true);
    }

    /**
     * Increments world ecopoints
     */
    @Override
    protected void increaseEcoPoints() {
        EcoHold.addWorldEco(1000);
    }
}
