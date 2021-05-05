package game;

import edu.monash.fit2099.engine.Item;
import game.consumable.Fruit;
import game.eggs.AllosaurEgg;
import game.eggs.BrachiosaurEgg;
import game.eggs.StegosaurEgg;
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

    public VendingItemFactory() {
    }

    public ArrayList<VendingMachineItems> getPossibleVendingItems() {
        ArrayList<VendingMachineItems> possibleVendingItems = new ArrayList<>();
        for (VendingMachineItems item : VendingMachineItems.values()) {
            possibleVendingItems.add(item);
        }
        return possibleVendingItems;
    }

    public Item createVendingItem(VendingMachineItems vendingMachineItem, Ecopoints playerEcoPoints) {
        Item item = null;
        switch (vendingMachineItem) {
            case FRUIT:
                item = createFruit();
                break;
            case VEGETARIAN_MEAL_KIT:
                item = createVegetarianMealKit();
                break;
            case CARNIVORE_MEAL_KIT:
                item = createCarnivoreMealKit();
                break;
            case STEGOSAUR_EGG:
                item = createStegosaurEgg(playerEcoPoints);
                break;
            case BRACHIOSAUR_EGG:
                item = createBrachiosaurEgg(playerEcoPoints);
                break;
            case ALLOSAUR_EGG:
                item = createAllosaurEgg(playerEcoPoints);
                break;
            case LASER_GUN:
                item = createLaserGun();
                break;
        }
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

    private Item createAllosaurEgg(Ecopoints playerEcoPoints) {
        return new AllosaurEgg(10, playerEcoPoints);
    }

    private Item createBrachiosaurEgg(Ecopoints playerEcoPoints) {
        return new BrachiosaurEgg(15, playerEcoPoints);
    }

    private Item createStegosaurEgg(Ecopoints playerEcoPoints) {
        return new StegosaurEgg(6, playerEcoPoints);
    }
}
