package game;

import game.enums.FoodCapabilities;

public class CarnivoreMealKit extends PortableItem{

    public CarnivoreMealKit() {
        super("carnivore meal kit", '$');
        addCapability(FoodCapabilities.FULL_HEALTH);
    }
}
