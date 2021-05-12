package game;

import edu.monash.fit2099.engine.Item;
import game.consumable.CarnivoreMealKit;
import game.consumable.Fruit;
import game.consumable.VegetarianMealKit;
import game.consumable.eggs.AllosaurEgg;
import game.consumable.eggs.BrachiosaurEgg;
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
    static VendingItemFactory factory = null;

    public static VendingItemFactory getInstance(){
        if (factory == null) {
            factory = new VendingItemFactory();
        }
        return factory;
    }

    private VendingItemFactory() {
    }

    public ArrayList<VendingMachineItems> getPossibleVendingItems() {
        ArrayList<VendingMachineItems> possibleVendingItems = new ArrayList<>();
        for (VendingMachineItems item : VendingMachineItems.values()) {
            possibleVendingItems.add(item);
        }
        return possibleVendingItems;
    }

    public Item createVendingItem(VendingMachineItems vendingMachineItem) {
        Item item = switch (vendingMachineItem) {
            case FRUIT -> createFruit();
            case VEGETARIAN_MEAL_KIT -> createVegetarianMealKit();
            case CARNIVORE_MEAL_KIT -> createCarnivoreMealKit();
            case STEGOSAUR_EGG -> createStegosaurEgg();
            case BRACHIOSAUR_EGG -> createBrachiosaurEgg();
            case ALLOSAUR_EGG -> createAllosaurEgg();
            case LASER_GUN -> createLaserGun();
            default -> null;
        };
        return item;
    }

    private Item createFruit() {
        return new Fruit();
    }

    private Item createVegetarianMealKit() {
        return new VegetarianMealKit();
    }

    private Item createCarnivoreMealKit() {
        return new CarnivoreMealKit();
    }

    private Item createLaserGun() {
        return new LaserGun();
    }

    private Item createAllosaurEgg() {
        return new AllosaurEgg();
    }

    private Item createBrachiosaurEgg() {
        return new BrachiosaurEgg();
    }

    private Item createStegosaurEgg() {
        return new StegosaurEgg();
    }
}
