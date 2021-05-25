package game.consumable.corpse;

import edu.monash.fit2099.engine.Actor;

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
