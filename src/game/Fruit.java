package game;

import game.enums.DietCapabilities;

public class Fruit extends PortableItem{

    public Fruit() {
        super("fruit", 'f');
        addCapability(DietCapabilities.VEGETABLE);
    }
}
