package game.consumable.corpse;

import edu.monash.fit2099.engine.Actor;

/**
 * Implements the corpse of a Pterodactyl
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see game.dinosaur.Pterodactyl
 * @see Corpse
 */
public class PterodactylCorpse extends Corpse {
    /**
     * Constructor for Corpse class
     *
     * @param actor      an Actor object that denotes a dinosaur instance object
     */
    public PterodactylCorpse(Actor actor) {
        super(actor, 30, '>');
    }
}
