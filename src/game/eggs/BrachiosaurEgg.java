package game.eggs;

import game.dinosaur.Brachiosaur;
import game.dinosaur.Dinosaur;

public class BrachiosaurEgg extends DinosaurEgg{

    public BrachiosaurEgg(String name, int lifespan) {
        super(name, lifespan);
    }

    @Override
    Dinosaur createDinosaur() {
        return new Brachiosaur("brachiosaur");
    }
}
