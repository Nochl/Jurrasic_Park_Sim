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
    LASER_GUN(500, "laser gun"), PTERODACTYL_EGG(200, "pterodactyl egg");

    /**
     * Denotes the cost of purchasing this vending machine item
     */
    private final int cost;
    /**
     * name of the vending machine item
     */
    private final String name;

    /**
     * Constructor for VendingMachineItems
     * @param cost cost of purchasing the item
     * @param name name of the item
     */
    VendingMachineItems(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    /**
     * gets the cost
     * @return an int containing the cost of the vending machine item
     */
    public int getCost() {
        return cost;
    }

    /**
     * gets the name of the item
     * @return a string containing the name of the vending machine item
     */
    public String getName() {
        return name;
    }

}
