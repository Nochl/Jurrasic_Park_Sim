package game.eggs;

import game.dinosaur.Allosaur;
import game.dinosaur.Dinosaur;

public class AllosaurEgg extends Egg {

    public AllosaurEgg(int lifespan) {
        super("allosaur egg", lifespan);
    }

    @Override
    Dinosaur createDinosaur() {
        return new Allosaur("allosaur");
    }
}
