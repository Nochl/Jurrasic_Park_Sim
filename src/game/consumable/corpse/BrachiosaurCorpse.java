package game.consumable.corpse;

import edu.monash.fit2099.engine.Actor;

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
