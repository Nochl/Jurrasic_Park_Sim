package game;

import edu.monash.fit2099.engine.Location;
import game.enums.DietCapabilities;

public class Corpse extends PortableItem {

    /**
     * A counter class that represents the amount of turns left until item disappears
     */
    private Counter lifespan;

    public Corpse(String name) {
        super("dead " + name, '%');
        lifespan = new Counter(20);
        addCapability(DietCapabilities.MEAT);
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
