package game.consumable.corpse;

import edu.monash.fit2099.engine.Actor;

/**
 * Implements the corpse of a Steogosaur
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see Corpse
 * @see StegosaurCorpse
 */
public class StegosaurCorpse extends Corpse {
    /**
     * Constructor for Corpse class
     *
     * @param actor      an Actor object that denotes a dinosaur instance object
     */
    public StegosaurCorpse(Actor actor) {
        super(actor, 50, '$');
    }
}
