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
 */
public class AllosaurEgg extends Egg {
    public AllosaurEgg() {
        super("allosaur egg", 25);
    }

    @Override
    Dinosaur createDinosaur() {
        return new Allosaur("allosaur");
    }

    @Override
    void increaseEcoPoints() {
        EcoHold.addWorldEco(1000);
    }
}
