package game.consumable;

import game.PortableItem;
import game.actions.PickFruitAction;
import game.enums.FoodTypeCapabilities;

import game.dinosaur.Dinosaur;
import game.dinosaur.Stegosaur;

public class Fruit extends Consumable {

    public Fruit() {
        super("Fruit", 'f', 20, 10, 5, 0);
        addCapability(FoodTypeCapabilities.VEGETABLE);
    }
}
