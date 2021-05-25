package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.World;
import game.Player;
import game.consumable.corpse.*;
import game.consumable.eggs.*;
import game.dinosaur.Dinosaur;
import game.enums.DinosaurCapabilities;

/**
 * Creates a new corpse dependent on Dinosaur Species
 *
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 */
public class CorpseFactory {
    /**
     * Create a new Corpse with a specific type dependent on the dinosaur species
     * @param actor An Dinosaur actor
     * @return Corpse object
     */
    public static Corpse getCorpse(Actor actor) {
        if (actor.hasCapability(DinosaurCapabilities.ALLOSAUR)) {
            return new AllosaurCorpse(actor);
        } else if (actor.hasCapability(DinosaurCapabilities.BRACHIOSAUR)) {
            return new BrachiosaurCorpse(actor);
        } else if (actor.hasCapability(DinosaurCapabilities.STEGOSAUR)) {
            return new StegosaurCorpse(actor);
        } else {
            return new PterodactylCorpse(actor);
        }
    }
}
