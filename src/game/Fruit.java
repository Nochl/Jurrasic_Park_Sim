package game;

import game.actions.EatFruitAction;
import game.enums.DietCapabilities;

import game.dinosaur.Dinosaur;
import game.dinosaur.Stegosaur;

import java.util.ArrayList;

public class Fruit extends PortableItem{

    public Fruit() {
        super("fruit", 'f');
        addCapability(DietCapabilities.VEGETABLE);
        allowableActions.add(new EatFruitAction(this));
    }

    public int getFedHealth() {
        return 20;
    }

    public int getEatenHealth(Dinosaur dinosaur) {
        if (dinosaur instanceof Stegosaur) {
            return 10;
        }
        else {
            return 5;
        }
    }
}
