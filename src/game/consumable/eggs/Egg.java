package game.consumable.eggs;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Location;
import game.Counter;
import game.actions.EatFruitAction;
import game.actions.EatMeatAction;
import game.consumable.Consumable;
import game.dinosaur.Dinosaur;
import game.enums.FoodTypeCapabilities;

import java.util.List;

/**
 * Implements an abstract Egg class in which child classes create an instance
 * of a dinosaur when egg hatches. Extends from consumable as it can be eaten by carnivores
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 3.0.0
 * @see Consumable
 * @see Dinosaur
 * @see Counter
 */
public abstract class Egg extends Consumable {
    /**
     * A Counter class object that denotes the amount of turns it takes for the egg to hatch
     */
    Counter lifespan;

    /**
     * Constructor for Egg class
     * @param name name of the egg
     * @param lifespan amount of turns it takes for the egg to hatch
     */
    public Egg(String name, int lifespan) {
        super(name, 'e', 10, 0, 0, 10, 0);
        this.lifespan = new Counter(lifespan);
        addCapability(FoodTypeCapabilities.MEAT);
        allowableActions.add(new EatMeatAction(this));
    }

    /**
     * Ticks the egg class to decrement lifespan and check if it is time for the egg to hatch
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        lifespan.dec();
        if (lifespan.getValue() == 0) {
            increaseEcoPoints();
            currentLocation.removeItem(this);
            if (!currentLocation.containsAnActor()) {
                Dinosaur dinosaur = createDinosaur();
                currentLocation.addActor(dinosaur);
            }
        }
    }

    /**
     * abstract class method that creates an instance of a Dinosaur
     * @return a new instance of Dinosaur class object
     */
    abstract Dinosaur createDinosaur();

    /**
     * abstract class method that increments ecopoints
     */
    abstract void increaseEcoPoints();
}
