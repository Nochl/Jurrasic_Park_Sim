package game;

import game.actions.EatFruitAction;
import game.enums.DietCapabilities;

public class Fruit extends PortableItem{

    public Fruit() {
        super("fruit", 'f');
        addCapability(DietCapabilities.VEGETABLE);
        allowableActions.add(new EatFruitAction(this));
    }
}
