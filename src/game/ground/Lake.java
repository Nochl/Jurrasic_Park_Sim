package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Sky;
import game.actions.DrinkLakeAction;
import game.consumable.Fish;
import game.enums.ActorMobilityCapabilities;
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
     * An array list of fish in the lake
     */
    ArrayList<Fish> seaCreatures;

    /**
     * Ini
     */
    int initialFishAmount;
    /**
     * Constructor.
     */
    public Lake() {
        super('~');
        sipCapacity = 25;
        initialFishAmount = 5;
        seaCreatures = new ArrayList<>();
        for (int i = 0; i < initialFishAmount; i++) {
            seaCreatures.add(new Fish());
        }
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        return new Actions(new DrinkLakeAction(this));
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        Double randomNumber = RandomNumberGenerator.randomDouble();
        if (randomNumber <= 0.6) {
            seaCreatures.add(new Fish());
        }
        sipCapacity =  Sky.getRainAmount();
    }

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
     * Reduces sip capacity by given amount
     * @param amount an int denoting amount of sips to remove
     */
    public void reduceSipCapacity(int amount) {
        sipCapacity -= amount;
    }
}
