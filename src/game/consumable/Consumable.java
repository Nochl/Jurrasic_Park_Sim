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

/**
 * Implements a consumable class that denotes all the items in game
 * that are consumable to all types of dinosaurs
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 3.0.0
 * @see PortableItem
 * @see Dinosaur
 * @see DinosaurCapabilities
 */
public class Consumable extends PortableItem {
    /**
     * Denots the amount of health gained if item is fed to a dinosaur
     */
    protected int fedHealth;
    /**
     * A list in which each indices contains the amount of health that each dinosaur type gains from eating this item
     * (index 0 = stegosaurus, index 1 = brachiosaurus, index 2 = allosaurus, index 3 = pterodactyl)
     */
    protected ArrayList<Integer> eatenHealth = new ArrayList<>();

    /**
     * Constructor for Consumable class
     * @param name name of the consumable
     * @param character display character of consumable item
     * @param fedHealth the amount of health gained if item is fed to a dinosaur
     * @param steg amount of health gained when stegasaurus eats it
     * @param brac amount of health gained when brachiosaurus eats it
     * @param allo amount of health gained when allosaurus eats it
     * @param ptero amount of health gained when pterodactyl eats it
     */
    public Consumable (String name, char character, int fedHealth, int steg, int brac, int allo, int ptero) {
        super(name, character);
        this.fedHealth = fedHealth;
        eatenHealth.add(steg);
        eatenHealth.add(brac);
        eatenHealth.add(allo);
        eatenHealth.add(ptero);
    }

    /**
     * Amount of HP dinosaur gains if it is fed this consumable
     * @return int amount to add to HP
     */
    public int getFedHealth() {
        return fedHealth;
    }

    /**
     * Amount of HP dinosaur gains if it eats this consumable
     * @param actor dinosaur eats food
     * @return int amount to add to HP
     */
    public int getEatenHealth(Actor actor) {
        if (actor.hasCapability(DinosaurCapabilities.STEGOSAUR)) {
            return eatenHealth.get(0);
        }
        else if (actor.hasCapability(DinosaurCapabilities.BRACHIOSAUR)) {
            return eatenHealth.get(1);
        } else if (actor.hasCapability(DinosaurCapabilities.ALLOSAUR)) {
            return eatenHealth.get(2);
        } else {
            return eatenHealth.get(3);
        }
    }

}



