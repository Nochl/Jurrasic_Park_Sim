package game.eggs;

import game.Ecopoints;
import game.dinosaur.Dinosaur;
import game.dinosaur.Stegosaur;

public class StegosaurEgg extends Egg {
    public StegosaurEgg(int lifespan, Ecopoints ecopoints) {
        super("stegosaur egg", lifespan, ecopoints);
    }

    @Override
    Dinosaur createDinosaur() {
        return new Stegosaur("stegosaur");
    }

    @Override
    void increaseEcoPoints() {
        ecopoints.addPoints(100);
    }
}
