package game.consumable;

import edu.monash.fit2099.engine.Action;
import game.PortableItem;
import game.actions.EatFruitAction;
import game.actions.EatMeatAction;
import game.actions.PickFruitAction;
import game.enums.FoodTypeCapabilities;

import game.enums.FruitCapabilities;

/**
 * Implements a fruit consumable item that gives health to stegosaurs and brachiosaurs and able to be fed
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 5.0.0
 * @see Consumable
 * @see FruitCapabilities
 */
public class Fruit extends Consumable {

    /**
     * Constructor of Fruit class
     * @param fruitCapability an enum of FruitCapabilities
     */
    public Fruit(FruitCapabilities fruitCapability) {
        super("Fruit", 'f', 20, 10, 5, 0, 0);
        addCapability(fruitCapability);
        addCapability(FoodTypeCapabilities.VEGETABLE);
    }

    /**
     * Constructor of Fruit class
     */
    public Fruit() {
        super("Fruit", 'f', 20, 10, 5, 0, 0);
        addCapability(FoodTypeCapabilities.VEGETABLE);
        allowableActions.add(new EatFruitAction(this));
    }

}
