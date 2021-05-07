package game.consumable;

import edu.monash.fit2099.engine.Location;
import game.Counter;
import game.actions.EatMeatAction;
import game.consumable.Consumable;
import game.enums.FoodTypeCapabilities;

public class Corpse extends Consumable {

    /**
     * A counter class that represents the amount of turns left until item disappears
     */
    private Counter lifespan;

    public Corpse(String name) {
        super("dead " + name, '%', 0, 0, 0, 20);
        lifespan = new Counter(20);
        addCapability(FoodTypeCapabilities.MEAT);
        allowableActions.add(new EatMeatAction(this));
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        lifespan.dec();
        if (lifespan.getValue() == 0) {
            currentLocation.removeItem(this);
        }

    }
}
