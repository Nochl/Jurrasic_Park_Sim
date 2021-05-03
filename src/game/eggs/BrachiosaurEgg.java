package game.eggs;

import game.dinosaur.Brachiosaur;
import game.dinosaur.Dinosaur;

public class BrachiosaurEgg extends Egg {

    public BrachiosaurEgg(int lifespan) {
        super("brachiosaur egg", lifespan);
    }

    @Override
    Dinosaur createDinosaur() {
        return new Brachiosaur("brachiosaur");
    }
}
