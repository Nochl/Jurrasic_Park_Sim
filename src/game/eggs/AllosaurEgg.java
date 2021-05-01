package game.eggs;

import game.dinosaur.Allosaur;
import game.dinosaur.Dinosaur;

public class AllosaurEgg extends DinosaurEgg{

    public AllosaurEgg(String name, int lifespan) {
        super(name, lifespan);
    }

    @Override
    Dinosaur createDinosaur() {
        return new Allosaur("allosaur");
    }
}
