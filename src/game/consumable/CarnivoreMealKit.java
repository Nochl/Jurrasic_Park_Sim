package game.consumable;

import edu.monash.fit2099.engine.Action;
import game.PortableItem;
import game.actions.EatFruitAction;
import game.actions.EatMeatAction;
import game.enums.FoodCapabilities;

import java.util.List;

/**
 * Implements carnivore meal kit for game. Extends from Consumable
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 2.0.0
 * @see Consumable
 */
public class CarnivoreMealKit extends Consumable {

    /**
     * Constructor for carnivore meal kit
     */
    public CarnivoreMealKit() {
        super("carnivore meal kit", '$', Integer.MAX_VALUE, 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
}
