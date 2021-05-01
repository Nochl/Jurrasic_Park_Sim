package game.eggs;

import game.dinosaur.Dinosaur;
import game.dinosaur.Stegosaur;

public class StegosaurEgg extends DinosaurEgg{

    public StegosaurEgg(String name, int lifespan) {
        super(name, lifespan);
    }

    @Override
    Dinosaur createDinosaur() {
        return new Stegosaur("stegosaur");
    }
}
