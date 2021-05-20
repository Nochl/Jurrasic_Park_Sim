package game.consumable;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import game.Counter;
import game.actions.EatMeatAction;
import game.consumable.Consumable;
import game.enums.DinosaurCapabilities;
import game.enums.FoodTypeCapabilities;

/**
 * Implements a corpse of a dinosaur in game world. Corpse can only be eaten by Carnivores
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 4.0.0
 * @see Consumable
 * @see FoodTypeCapabilities
 * @see EatMeatAction
 */
public class Corpse extends Consumable {

    /**
     * A counter class that represents the amount of turns left until item disappears
     */
    private Counter lifespan;

    /**
     * Constructor for Corpse class
     * @param name name of the corpse
     * @param actor an Actor object that denotes a dinosaur instance object
     */
    public Corpse(String name, Actor actor) {
        super("dead " + name, '%', 0, 0, 0, 20, 10);
        corpseDespawn(actor);
        addCapability(FoodTypeCapabilities.MEAT);
        allowableActions.add(new EatMeatAction(this));
    }

    /**
     * Tick function that decrements lifespan and deletes corpse if lifespan is 0
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        lifespan.dec();
        if (lifespan.getValue() == 0) {
            currentLocation.removeItem(this);
        }

    }

    /**
     * set's the lifespan of the corpse depending on the type of the dinosaur
     * @param actor an actor instance object that denotes a dinosaur type
     */
    public void corpseDespawn(Actor actor){
        if (actor.hasCapability(DinosaurCapabilities.BRACHIOSAUR)) {
            lifespan = new Counter(40);
        }
        else {
            lifespan = new Counter(20);
        }
    }
}
