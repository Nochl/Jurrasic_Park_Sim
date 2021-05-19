package game.consumable;

import edu.monash.fit2099.engine.Action;
import game.PortableItem;
import game.actions.EatFruitAction;
import game.actions.EatMeatAction;
import game.actions.PickFruitAction;
import game.enums.FoodTypeCapabilities;

import game.enums.FruitCapabilities;

public class Fruit extends Consumable {

    public Fruit(FruitCapabilities fruitCapability) {
        super("Fruit", 'f', 20, 10, 5, 0, 0);
        addCapability(fruitCapability);
        addCapability(FoodTypeCapabilities.VEGETABLE);
    }

    public Fruit() {
        super("Fruit", 'f', 20, 10, 5, 0, 0);
        addCapability(FoodTypeCapabilities.VEGETABLE);
        allowableActions.add(new EatFruitAction(this));
    }

}
