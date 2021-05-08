package game.enums;

/**
 * An enumerator class that contains all that can be bought from a vending machine and
 * how much each one costs
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.2
 */
public enum VendingMachineItems {
    FRUIT (30, "fruit"), VEGETARIAN_MEAL_KIT(100, "vegetarian meal kit"),
    CARNIVORE_MEAL_KIT(500, "carnivore meal kit"), STEGOSAUR_EGG(200, "stegosaur egg"),
    BRACHIOSAUR_EGG(500, "brachiosaur egg"), ALLOSAUR_EGG(1000, "allosaur egg"),
    LASER_GUN(500, "laser gun"),;

    private final int cost;
    private final String name;

    VendingMachineItems(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
