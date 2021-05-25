package game.consumable.corpse;

import edu.monash.fit2099.engine.Actor;

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
