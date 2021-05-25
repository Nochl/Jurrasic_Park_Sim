package game.consumable.corpse;

import edu.monash.fit2099.engine.Actor;

/**
 * Implements the corpse of an Allosaur
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see Corpse
 * @see game.dinosaur.Allosaur
 */
public class AllosaurCorpse extends Corpse {
    /**
     * Constructor for Corpse class
     *
     * @param actor      an Actor object that denotes a dinosaur instance object
     */
    public AllosaurCorpse(Actor actor) {
        super(actor, 50, '^');
    }
}
