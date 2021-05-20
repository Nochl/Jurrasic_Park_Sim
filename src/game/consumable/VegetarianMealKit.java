package game.consumable;

import edu.monash.fit2099.engine.Action;
import game.PortableItem;
import game.actions.EatFruitAction;
import game.enums.FoodCapabilities;
import game.enums.FoodTypeCapabilities;

import java.util.List;

/**
 * Implements a vegetarian meal kit consumable item that fully heals herbivores
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 3.0.0
 * @see Consumable
 * @see FoodTypeCapabilities
 */
public class VegetarianMealKit extends Consumable {

    public VegetarianMealKit() {
        super("vegetarian meal kit", '#', Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 0);
        addCapability(FoodTypeCapabilities.VEGETABLE);
        allowableActions.add(new EatFruitAction(this));
    }

}
