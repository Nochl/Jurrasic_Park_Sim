package game.consumable;

import edu.monash.fit2099.engine.Action;
import game.PortableItem;
import game.actions.EatFruitAction;
import game.actions.EatMeatAction;
import game.enums.FoodCapabilities;

import java.util.List;

public class CarnivoreMealKit extends PortableItem {

    public CarnivoreMealKit() {
        super("carnivore meal kit", '$');
        addCapability(FoodCapabilities.FULL_HEALTH);
        allowableActions.add(new EatMeatAction(this));

    }
}
