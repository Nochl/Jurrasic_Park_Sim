package game.eggs;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Location;
import game.Counter;
import game.PortableItem;
import game.consumable.Consumable;
import game.dinosaur.Dinosaur;
import game.enums.FoodTypeCapabilities;

import java.util.List;

public abstract class Egg extends Consumable {
    Counter lifespan;

    public Egg(String name, int lifespan) {
        super(name, 'e', 10, 0, 0, 10);
        this.lifespan = new Counter(lifespan);
        addCapability(FoodTypeCapabilities.MEAT);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = super.getAllowableActions();
//        actions.add(new EatMeatAction(this))
        return actions;
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
