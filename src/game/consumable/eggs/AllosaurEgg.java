package game.consumable.eggs;

import game.EcoHold;
import game.dinosaur.Allosaur;
import game.dinosaur.Dinosaur;

/**
 * Implements allosaur egg that extends from Egg abstract class
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 3.0.0
 * @see Egg
 * @see Allosaur
 */
public class AllosaurEgg extends Egg {
    /**
     * Constructor method for allosaurus egg
     */
    public AllosaurEgg() {
        super("allosaur egg", 25);
    }

    /**
     * Creates an instance of allosaur dinosaur
     * @return a new instance of Allosaur class object
     */
    @Override
    Dinosaur createDinosaur() {
        pic.println("An Allosaur has hatched!!!");
        return new Allosaur("allosaur", true);
    }

    /**
     * Increments ecopoints in the world
     */
    @Override
    void increaseEcoPoints() {
        EcoHold.addWorldEco(1000);
    }
}
