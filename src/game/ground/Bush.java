package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.NextMapAction;
import game.actions.PickFruitAction;
import game.consumable.Fruit;
import game.enums.DinosaurCapabilities;
import game.enums.FruitCapabilities;
import game.enums.MapCapabilities;

import java.util.ArrayList;
import java.util.Map;

/**
 * Implements a ground Bush that can bear and grow fruit
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see Ground
 * @see Fruit
 */
public class Bush extends Ground{
    /**
     * An Array list of fruit denoting all the fruit in bush
     */
    private ArrayList<Fruit> inBush = new ArrayList<>();
    /**
     * Constructor.
     *
     */
    public Bush() {
        super('b');

    }

    /**
     * ticks the bush ground object
     * @param location The location of the Ground
     */
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

    /**
     * Gets all the allowable actions that can be done to bush
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A Actions object denoting all actions that can be done to bush object
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        Actions actions = new Actions();
        if (inBush.size() > 0) {
            new PickFruitAction(inBush);
        }
        return actions;
    }
}
