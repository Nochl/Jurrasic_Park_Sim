package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.consumable.Fish;
import game.enums.ActorMobilityCapabilities;
import game.utils.RandomNumberGenerator;

import java.util.ArrayList;

/**
 * Implements a lake ground object in game world
 *
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see Ground
 */
public class Lake extends Ground {

    /**
     *  an int denoting the amount of sips an actor can take from lake
     */
    int capacity;

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
        capacity = 25;
        initialFishAmount = 5;
        seaCreatures = new ArrayList<>();
        for (int i = 0; i < initialFishAmount; i++) {
            seaCreatures.add(new Fish());
        }
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        return super.allowableActions(actor, location, direction);
    }

    //    @Override
//    public Actions allowableActions(Actor actor, Location location, String direction) {

//        return new DrinkLakeAction();
//        return null;
//    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        Double randomNumber = RandomNumberGenerator.randomDouble();
        if (randomNumber <= 0.6) {
            seaCreatures.add(new Fish());
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(ActorMobilityCapabilities.FLY);
    }
}
