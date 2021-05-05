package game.eggs;

import edu.monash.fit2099.engine.Location;
import game.Counter;
import game.Ecopoints;
import game.PortableItem;
import game.actions.EatMeatAction;
import game.dinosaur.Dinosaur;
import game.enums.DietCapabilities;


public abstract class Egg extends PortableItem {
    Counter lifespan;
    Ecopoints ecopoints;

    public Egg(String name, int lifespan, Ecopoints ecopoints) {
        super(name, 'e');
        this.lifespan = new Counter(lifespan);
        this.ecopoints = ecopoints;
        addCapability(DietCapabilities.MEAT);
        allowableActions.add(new EatMeatAction(this));
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        lifespan.dec();
        if (lifespan.getValue() == 0) {
            increaseEcoPoints();
            currentLocation.removeItem(this);
        }
        Dinosaur dinosaur = createDinosaur();
        currentLocation.addActor(dinosaur);
    }

    abstract Dinosaur createDinosaur();

    abstract void increaseEcoPoints();
}
