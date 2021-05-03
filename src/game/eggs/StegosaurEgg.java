package game.eggs;

import game.dinosaur.Dinosaur;
import game.dinosaur.Stegosaur;

public class StegosaurEgg extends Egg {

    public StegosaurEgg(int lifespan) {
        super("stegosaur egg", lifespan);
    }

    @Override
    Dinosaur createDinosaur() {
        return new Stegosaur("stegosaur");
    }
}
