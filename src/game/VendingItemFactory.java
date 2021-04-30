package game;

import edu.monash.fit2099.engine.Item;

public class VendingItemFactory {
    public VendingItemFactory() {
    }

    // wait for implementation of fruit
    public Item createFruit() {

//        return new Fruit();
        return null;
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

//    public Item createEgg() {
//    }
}
