package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.PickFruitAction;
import game.consumable.Fruit;
import game.enums.DinosaurCapabilities;
import game.enums.FruitCapabilities;

import java.util.ArrayList;

public class Bush extends Ground{
    private ArrayList<Fruit> inBush = new ArrayList<>();
    /**
     * Constructor.
     *
     */
    public Bush() {
        super('b');

    }

    public void tick(Location location) {
        double prob = Math.random();
        super.tick(location);
        if (prob < 0.5) {
            inBush.add(new Fruit(FruitCapabilities.ON_FLOOR));
        }

        if (location.getActor() != null && location.getActor().hasCapability(DinosaurCapabilities.BRACHIOSAUR)) {
            double brachStepProb = Math.random();
            if (brachStepProb <= 0.2) {
                location.setGround(new Dirt());
            }
        }
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        return new Actions(new PickFruitAction(inBush));
    }
}
