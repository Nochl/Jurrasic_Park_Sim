package game.consumable;

import game.enums.FoodTypeCapabilities;

public class Fish extends Consumable{
    public Fish() {
        super("fish", '*', 0, 0, 0, 0, 10);
        addCapability(FoodTypeCapabilities.SEAFOOD);
    }

}
