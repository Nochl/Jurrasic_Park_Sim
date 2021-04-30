package game;

import game.PortableItems.PortableItem;
import game.enums.FoodCapabilities;

public class VegetarianMealKit extends PortableItem {

    public VegetarianMealKit() {
        super("vegetarian meal kit", '#');
        addCapability(FoodCapabilities.FULL_HEALTH);
    }
}
