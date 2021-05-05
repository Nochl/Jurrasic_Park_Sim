package game.consumable;

import game.PortableItem;
import game.dinosaur.Brachiosaur;
import game.enums.FoodTypeCapabilities;

import game.dinosaur.Dinosaur;
import game.dinosaur.Stegosaur;

import java.util.ArrayList;


public class Consumable extends PortableItem {
    protected int fedHealth;
    protected ArrayList<Integer> eatenHealth = new ArrayList<>();

    public Consumable (String name, char character, int fedHealth, Integer steg, Integer brac, Integer allo) {
        super(name, character);
        this.fedHealth = fedHealth;
        eatenHealth.add(steg);
        eatenHealth.add(brac);
        eatenHealth.add(allo);
    }

    public int getFedHealth() {
        return fedHealth;
    }

    public int getEatenHealth(Dinosaur dinosaur) {
        if (dinosaur instanceof Stegosaur) {
            return eatenHealth.get(0);
        }
        else if (dinosaur instanceof Brachiosaur) {
            return eatenHealth.get(1);
        }
        else {
            return eatenHealth.get(2);
        }
    }
}



