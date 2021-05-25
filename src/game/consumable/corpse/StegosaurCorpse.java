package game.consumable.corpse;

import edu.monash.fit2099.engine.Actor;

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
