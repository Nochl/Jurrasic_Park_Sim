package game.eggs;

import game.EcoHold;
import game.Ecopoints;
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
    public AllosaurEgg(int lifespan) {
        super("allosaur egg", lifespan);
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
