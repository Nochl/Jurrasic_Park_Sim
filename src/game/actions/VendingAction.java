package game.actions;

import edu.monash.fit2099.engine.*;
import game.EcoHold;
import game.Ecopoints;
import game.Player;
import game.VendingItemFactory;
import game.enums.VendingMachineItems;

import java.util.ArrayList;

/**
 * A class that creates instances of vending machine items for player and extends from action.
 *
 * @author Timothy Jordan, Enoch Leow
 * @author Enoch Leow
 * @version 3.0.0
 * @see VendingItemFactory
 * @see VendingMachineItems
 * @see Player
 */
public class VendingAction extends Action {
    /**
     * A VendingItemFactory object that handles creating instances of vending machine items
     */
    private VendingItemFactory vendingItemFactory;
    /**
     * An ArrayList of VendingMachineItems that denotes all possible vending machine items and costs
     */
    private ArrayList<VendingMachineItems> menuOptions;

    /**
     * Constructor for Vending Action
     */
    public VendingAction() {
        vendingItemFactory = VendingItemFactory.getInstance();
        ArrayList<VendingMachineItems> vendingMachineOptions = vendingItemFactory.getPossibleVendingItems();
        // Null object is added so indices and options align
        menuOptions = new ArrayList<>();
        menuOptions.add(null);
        for (int i = 0; i < vendingMachineOptions.size(); i++) {
            menuOptions.add(vendingMachineOptions.get(i));
        }
    }

    /**
     * Allows the player actor to purchase a vending machine item
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing the player's actions of buying an item from the vending machine
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (!(actor instanceof Player)) {
            return actor + " is not allowed to access Vending Machine!";
        }
        Display display = new Display();
        // access the current actor's ecopoints
        Ecopoints playerEco = EcoHold.getPlayerEco(actor);
        // checks if the actor has an ecopoints attributes
        if (playerEco == null) {
            return actor + " does not have a ecopoints implemented";
        }
        // Displays the vending machine menu
        displayVendingMachineMenu(playerEco, display);
        // Asks the player what item they want to purchase
        int key = getActorMenuOption(display);
        String description;
        int itemCost = menuOptions.get(key).getCost();
        // Checks if the player has enough ecopoints to purchase item
        if (itemCost > playerEco.getPoints()) {
            description = actor + " does not have enough EcoPoints to purchase " + menuOptions.get(key).getName();
        } else {
            playerEco.removePoints(itemCost);
            Item item = vendingItemFactory.createVendingItem(menuOptions.get(key));
            actor.addItemToInventory(item);
            description = menuOptions.get(key).getName() + " has been added to " + actor + "'s inventory";
        }

        return description;
    }

    /**
     * Processes the player's input and checks if they chosen a valid number
     * and that they have enough ecopoints.
     *
     * @param display a Display object that enable I/O operations
     * @return a int value that represents a menu option
     */
    private int getActorMenuOption(Display display) {
        char key;
        boolean validKey = false;
        int intKey = 0;
        do {
            key = display.readChar();
            if (Character.isDigit(key)) {
                intKey = Character.getNumericValue(key);
                if (intKey > 0 && intKey < menuOptions.size()) {
                    validKey = true;
                }
            }
        } while (!validKey);
        return intKey;
    }

    /**
     * Prints the display menu for vending machine
     *
     * @param playerEco an Ecopoints objects that represents the player's current ecopoints
     * @param display A Display object that enables use to print an end line
     */
    private void displayVendingMachineMenu(Ecopoints playerEco, Display display) {
        display.endLine();

        System.out.println("----------------------------------");
        System.out.println("          Vending Machine         ");
        System.out.println("----------------------------------");
        for (int i = 1; i < menuOptions.size(); i++) {
            String itemName = menuOptions.get(i).getName();
            int itemCost = menuOptions.get(i).getCost();
            System.out.println(i + ". " + itemName + " (" + itemCost + ")");
        }
        System.out.println("----------------------------------");
        System.out.println("EcoPoints : " + playerEco.getPoints());
    }

    /**
     * Provides a description of action accessing vending machine in game world
     * @param actor The actor performing the action.
     * @return a string containing the action the player can do
     */
    @Override
    public String menuDescription(Actor actor) {
        return "access vending machine";
    }

    /**
     * sets the specific hotkey to run this action as 'a'
     * @return a string of the hotkey
     */
    @Override
    public String hotkey() {
        return "a";
    }
}
