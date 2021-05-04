package game.eggs;

import edu.monash.fit2099.engine.Location;
import game.Counter;
import game.PortableItem;
import game.actions.EatMeatAction;
import game.dinosaur.Dinosaur;
import game.enums.DietCapabilities;


public abstract class Egg extends PortableItem {
    Counter lifespan;

    public Egg(String name, int lifespan) {
        super(name, 'e');
        this.lifespan = new Counter(lifespan);
        addCapability(DietCapabilities.MEAT);
        allowableActions.add(new EatMeatAction(this));
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        lifespan.dec();
        if (lifespan.getValue() == 0) {
            currentLocation.removeItem(this);
        }
        Dinosaur dinosaur = createDinosaur();
        currentLocation.addActor(dinosaur);
    }

    abstract Dinosaur createDinosaur();
}
