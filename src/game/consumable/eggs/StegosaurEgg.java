package game.consumable.eggs;

import game.EcoHold;
import game.dinosaur.Dinosaur;
import game.dinosaur.Stegosaur;

public class StegosaurEgg extends Egg {
    public StegosaurEgg() {
        super("stegosaur egg", 15);
    }

    @Override
    Dinosaur createDinosaur() {
        return new Stegosaur("stegosaur", Boolean.TRUE);
    }

    @Override
    void increaseEcoPoints() {
        EcoHold.addWorldEco(100);
    }
}
