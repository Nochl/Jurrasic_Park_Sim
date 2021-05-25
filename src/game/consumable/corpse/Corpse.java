package game.consumable.corpse;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import game.Counter;
import game.actions.EatMeatAction;
import game.consumable.Consumable;
import game.enums.DinosaurCapabilities;
import game.enums.FoodTypeCapabilities;
import game.enums.ItemTypeCapabilities;

import static java.lang.Math.min;

/**
 * Implements a corpse of a dinosaur in game world. Corpse can only be eaten by Carnivores
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 4.0.0
 * @see Consumable
 * @see FoodTypeCapabilities
 * @see EatMeatAction
 */
public abstract class Corpse extends Consumable {
    /**
     * Amount of foodPoints left (for when Pterodactyls are slowly eating)
     */
    int remaining;
    /**
     * A counter class that represents the amount of turns left until item disappears
     */
    private Counter lifespan;

    /**
     * Constructor for Corpse class
     * @param character display character of corpse
     * @param actor an Actor object that denotes a dinosaur instance object
     */
    public Corpse(Actor actor, int foodPoints, char character) {
        super("dead " + actor.toString(), character, 0, 0, 0, 20, 10);
        remaining = foodPoints;
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

    /**
     * Amount of HP dinosaur gains if it eats a corpse
     * @param actor dinosaur eats food
     * @return int amount to add to HP
     */
    @Override
    public int getEatenHealth(Actor actor) {
        // Feeds whole points to Allosaur
        if (actor.hasCapability(DinosaurCapabilities.ALLOSAUR)) {
            remaining = 0;
            addCapability(ItemTypeCapabilities.CORPSEDONE);
            return remaining;

        // If Pterodactyl, feed it 10 or remaining points
        } else if (actor.hasCapability(DinosaurCapabilities.PTERODACTYL)){
            int heal = min(remaining, 10);
            remaining = remaining-10;
            //set it to be DONE if there is no more remaining points
            if (remaining < 0) {addCapability(ItemTypeCapabilities.CORPSEDONE);}
            return heal;

        // if its neither dinosaur, return nothing (they shouldn't be able to access this anyway)
        } return 0;
    }

}
