package game.eggs;

import game.Ecopoints;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Dinosaur;

public class BrachiosaurEgg extends Egg {
    public BrachiosaurEgg(int lifespan, Ecopoints ecopoints) {
        super("brachiosaur egg", lifespan, ecopoints);
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
        ecopoints.addPoints(1000);
    }
}
