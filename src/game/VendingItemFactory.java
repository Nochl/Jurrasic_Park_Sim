package game;

import edu.monash.fit2099.engine.Item;
import game.consumable.Fruit;
import game.eggs.AllosaurEgg;
import game.eggs.BrachiosaurEgg;
import game.eggs.StegosaurEgg;

public class VendingItemFactory {
    public VendingItemFactory() {
    }

    public Item createFruit() {
        return new Fruit();
    }

    public Item createVegetarianMealKit() {
        return new VegetarianMealKit();
    }

    public Item createCarnivoreMealKit() {
        return new CarnivoreMealKit();
    }

    public Item createCorpse(String name) {
        return new Corpse(name);
    }

    public Item createLaserGun() {
        return new LaserGun();
    }

    public Item createAllosaurEgg() {
        return new AllosaurEgg(10);
    }

    public Item createBrachiosaurEgg() {
        return new BrachiosaurEgg(15);
    }

    public Item createStegosaurEgg() {
        return new StegosaurEgg(6);
    }
}
