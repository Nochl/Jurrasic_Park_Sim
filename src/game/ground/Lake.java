package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Sky;
import game.actions.DrinkLakeAction;
import game.actions.EatFishAction;
import game.consumable.Fish;
import game.enums.ActorMobilityCapabilities;
import game.enums.GroundTypeCapabilities;
import game.utils.RandomNumberGenerator;

import java.util.ArrayList;

/**
 * Implements a lake ground object in game world
 *
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.3
 * @see Ground
 */
public class Lake extends Ground {

    /**
     *  an int denoting the amount of sips an actor can take from lake
     */
    int sipCapacity;

    /**
     * an int denoting the minimum sip capacity
     */
    int minSipCapacity;

    /**
     * an int denoting the maximum sip capacity
     */
    int maxSipCapacity;

    /**
     * An array list of fish in the lake
     */
    ArrayList<Fish> fishes;

    /**
     * Ini
     */
    int initialFishAmount;

    /**
     * Constructor.
     */
    public Lake() {
        super('~');
        addCapability(GroundTypeCapabilities.LAKE);
        sipCapacity = 25;
        maxSipCapacity = sipCapacity;
        minSipCapacity = 0;
        initialFishAmount = 5;
        fishes = new ArrayList<>();
        for (int i = 0; i < initialFishAmount; i++) {
            fishes.add(new Fish());
        }
    }

    /**
     * returns all the actions that can be done to lake
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an Actions object containing all allowable actions to lake object
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if (sipCapacity > 0) {
            actions.add(new DrinkLakeAction(this));
        }
        if (fishes.size() > 0) {
            actions.add(new EatFishAction(fishes));
        }
        return actions;
    }

    /**
     * ticks the lake object in game world
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        Double randomNumber = RandomNumberGenerator.randomDouble();
        if (randomNumber <= 0.6) {
            fishes.add(new Fish());
        }
        increaseSipCapacity(Sky.getRainAmount());
    }

    /**
     * Checks if the given actor is allowed to enter the ground
     * @param actor the Actor to check
     * @return true if the actor has the capability to FLY, else false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(ActorMobilityCapabilities.FLY);
    }

    /**
     * Gets the sip capacity of lake
     * @return an int denoting sip capacity
     */
    public int getSipCapacity() {
        return sipCapacity;
    }

    /**
     * Reduces sip capacity by given amount. If new amount is less than minSipCapacity,
     * sipCapacity will be set to minSipCapacity
     * @param amount an int denoting amount of sips to remove
     */
    public void reduceSipCapacity(int amount) {
        int value = sipCapacity + amount;
        sipCapacity = Math.max(value, minSipCapacity);
    }

    /**
     * Increases sip capacity by given amount. If new amount is greater than
     * maxSipCapacity, sipCapacity will be set to maxSipCapacity
     * @param amount an int denoting the amount of sips to add
     */
    public void increaseSipCapacity(int amount) {
        int value = sipCapacity + amount;
        sipCapacity = Math.min(value, maxSipCapacity);
    }
}
