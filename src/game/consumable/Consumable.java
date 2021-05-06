package game.consumable;

import edu.monash.fit2099.engine.*;
import game.PortableItem;
import game.actions.FeedingAction;
import game.actions.PickFruitAction;
import game.dinosaur.Brachiosaur;
import game.enums.DinosaurCapabilities;
import game.enums.FoodTypeCapabilities;

import game.dinosaur.Dinosaur;
import game.dinosaur.Stegosaur;

import java.util.ArrayList;
import java.util.List;


public class Consumable extends PortableItem {
    protected int fedHealth;
    protected ArrayList<Integer> eatenHealth = new ArrayList<>();

    public Consumable (String name, char character, int fedHealth, Integer steg, Integer brac, Integer allo) {
        super(name, character);
        this.fedHealth = fedHealth;
        eatenHealth.add(steg);
        eatenHealth.add(brac);
        eatenHealth.add(allo);
        allowableActions.add(new PickUpItemAction(this));
    }

    public int getFedHealth() {
        return fedHealth;
    }

    public int getEatenHealth(Actor actor) {
        if (actor.hasCapability(DinosaurCapabilities.STEGOSAUR)) {
            return eatenHealth.get(0);
        }
        else if (actor.hasCapability(DinosaurCapabilities.BRACHIOSAUR)) {
            return eatenHealth.get(1);
        }
        else {
            return eatenHealth.get(2);
        }
    }

}



