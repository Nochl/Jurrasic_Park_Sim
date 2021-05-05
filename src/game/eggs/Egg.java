package game.eggs;

import edu.monash.fit2099.engine.Location;
import game.Counter;
import game.Ecopoints;
import game.PortableItem;
import game.actions.EatMeatAction;
import game.consumable.Consumable;
import game.dinosaur.Dinosaur;
import game.enums.FoodTypeCapabilities;

import java.util.List;

public abstract class Egg extends Consumable {
    Counter lifespan;
    Ecopoints ecopoints;

    public Egg(String name, int lifespan Ecopoints ecopoints) {
        super(name, 'e', 10, 0, 0, 10);
        this.lifespan = new Counter(lifespan);
        addCapability(FoodTypeCapabilities.MEAT);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = super.getAllowableActions();
//        actions.add(new EatMeatAction(this))
        return actions;
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
