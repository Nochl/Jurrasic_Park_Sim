package game;

import edu.monash.fit2099.engine.Item;
import game.consumable.CarnivoreMealKit;
import game.consumable.Fruit;
import game.consumable.VegetarianMealKit;
import game.consumable.eggs.AllosaurEgg;
import game.consumable.eggs.BrachiosaurEgg;
import game.consumable.eggs.PterodactylEgg;
import game.consumable.eggs.StegosaurEgg;
import game.enums.VendingMachineItems;

import java.util.ArrayList;

/**
 * A factory class that creates instances of each vending machine item.
 * Uses VendingMachineItems enum class to determine which item to create.
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 3.0.0
 */
public class VendingItemFactory {
    /**
     * a static attribute of VendingItemFactory
     */
    static VendingItemFactory factory = null;

    /**
     * A public method that returns a singleton of VendingItemFactory
     * @return a reference to VendingItemFactory singleton
     */
    public static VendingItemFactory getInstance(){
        if (factory == null) {
            factory = new VendingItemFactory();
        }
        return factory;
    }

    /**
     * private constructor for VendingItemFactory
     */
    private VendingItemFactory() {
    }

    /**
     * returns an ArrayList of possible Vending Machine Items and corresponding costs
     * @return an ArrayList containing all VendingMachineItems values and enumerators
     */
    public ArrayList<VendingMachineItems> getPossibleVendingItems() {
        ArrayList<VendingMachineItems> possibleVendingItems = new ArrayList<>();
        for (VendingMachineItems item : VendingMachineItems.values()) {
            possibleVendingItems.add(item);
        }
        return possibleVendingItems;
    }

    /**
     * Creates an instance of a item depending on the given VendingMachineItems
     * input
     * @param vendingMachineItem an instance of VendingMachineItems that details a vending machine items
     * @return an instance of Item that is a vending machine item
     */
    public Item createVendingItem(VendingMachineItems vendingMachineItem) {
        Item item = switch (vendingMachineItem) {
            case FRUIT -> createFruit();
            case VEGETARIAN_MEAL_KIT -> createVegetarianMealKit();
            case CARNIVORE_MEAL_KIT -> createCarnivoreMealKit();
            case STEGOSAUR_EGG -> createStegosaurEgg();
            case BRACHIOSAUR_EGG -> createBrachiosaurEgg();
            case ALLOSAUR_EGG -> createAllosaurEgg();
            case LASER_GUN -> createLaserGun();
            case PTERODACTYL_EGG -> createPterodactylEgg();
        };
        return item;
    }

    /**
     * Creates an instance of Item fruit
     * @return a fruit Item
     */
    private Item createFruit() {
        return new Fruit();
    }

    /**
     * Creates an instance of Item vegetarian meal kit
     * @return a vegetarian meal kit Item
     */
    private Item createVegetarianMealKit() {
        return new VegetarianMealKit();
    }

    /**
     * Creates an instance of Item carnivore meal kit
     * @return a carnivore meal kit Item
     */
    private Item createCarnivoreMealKit() {
        return new CarnivoreMealKit();
    }

    /**
     * Creates an instance of Item laser gun
     * @return a laser gun Item
     */
    private Item createLaserGun() {
        return new LaserGun();
    }

    /**
     * Creates an instance of Item allosaur egg
     * @return an allosaur egg Item
     */
    private Item createAllosaurEgg() {
        return new AllosaurEgg();
    }

    /**
     * Creates an instance of Item brachiosaur egg
     * @return a brachiosaur egg Item
     */
    private Item createBrachiosaurEgg() {
        return new BrachiosaurEgg();
    }

    /**
     * Creates an instance of Item stegosaur egg
     * @return a stegosaur egg Item
     */
    private Item createStegosaurEgg() {
        return new StegosaurEgg();
    }

    /**
     * Creates and instance of Item Pterodactyl Egg
     * @return a Pterodactyl egg item
     */
    private Item createPterodactylEgg() {
        return new PterodactylEgg();
    }
}
