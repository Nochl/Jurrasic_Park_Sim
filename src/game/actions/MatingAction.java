package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.consumable.eggs.*;
import game.enums.DinosaurCapabilities;
import game.enums.Gender;
import game.enums.Mateable;
import game.dinosaur.Allosaur;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Dinosaur;

public class MatingAction extends Action {
    /**
     * The Dinosaur that is to be mated with
     */
    protected Dinosaur target;


    public MatingAction(Dinosaur target) {
        this.target =  target;

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeCapability(Mateable.MATEABLE);
        target.removeCapability(Mateable.MATEABLE);
        if (actor.hasCapability(Gender.FEMALE)) {
            actor.addItemToInventory(eggFactory(target));
        }
        else {
            target.addItemToInventory(eggFactory(target));
        }

        return (actor.toString()+" has mated with "+target.toString()+"!!!");
    }

    public Egg eggFactory(Dinosaur dinosaur) {
        if (dinosaur.hasCapability(DinosaurCapabilities.ALLOSAUR)) {
            return new AllosaurEgg();
        } else if (dinosaur.hasCapability(DinosaurCapabilities.BRACHIOSAUR)) {
            return new BrachiosaurEgg();
        } else if (dinosaur.hasCapability(DinosaurCapabilities.STEGOSAUR)) {
            return new StegosaurEgg();
        } else {
            return new PterodactylEgg();
        }
    }

    /**
     * Gets the target Actor
     * @return Actor target
     */
    public Actor getTarget() {
        return target;
    }

    @Override
    public String menuDescription(Actor actor) {
        return (actor.toString()+" wants to breed.");
    }
}
