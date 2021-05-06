package game.eggs;

import game.EcoHold;
import game.Ecopoints;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Dinosaur;

public class BrachiosaurEgg extends Egg {
    public BrachiosaurEgg(int lifespan) {
        super("brachiosaur egg", lifespan);
    }

//    public BrachiosaurEgg(int lifespan) {
//        super("brachiosaur egg", lifespan);
//    }

    @Override
    Dinosaur createDinosaur() {
        return new Brachiosaur("brachiosaur");
    }

    @Override
    void increaseEcoPoints() {
        EcoHold.addWorldEco(1000);
    }
}
