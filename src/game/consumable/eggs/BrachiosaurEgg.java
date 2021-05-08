package game.consumable.eggs;

import game.EcoHold;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Dinosaur;

public class BrachiosaurEgg extends Egg {
    public BrachiosaurEgg() {
        super("brachiosaur egg", 30);
    }


    @Override
    Dinosaur createDinosaur() {
        return new Brachiosaur("brachiosaur", Boolean.TRUE);
    }

    @Override
    void increaseEcoPoints() {
        EcoHold.addWorldEco(1000);
    }
}
