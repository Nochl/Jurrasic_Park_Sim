package game.consumable;

import game.PortableItem;
import game.enums.FoodCapabilities;
import game.enums.FoodTypeCapabilities;

public class VegetarianMealKit extends Consumable {

    public VegetarianMealKit() {
        super("vegetarian meal kit", '#', Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
        addCapability(FoodTypeCapabilities.VEGETABLE);
    }
}
