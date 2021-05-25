package game.consumable.corpse;

import edu.monash.fit2099.engine.Actor;

/**
 * Implements the corpse of a Brachiosaur
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see Corpse
 * @see game.dinosaur.Brachiosaur
 */
public class BrachiosaurCorpse extends Corpse {
    /**
     * Constructor for Corpse class
     *
     * @param actor      an Actor object that denotes a dinosaur instance object
     */
    public BrachiosaurCorpse(Actor actor) {
        super(actor, 100, '8');
    }
}
