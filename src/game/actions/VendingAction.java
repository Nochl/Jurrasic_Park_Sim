package game.actions;

import edu.monash.fit2099.engine.*;
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
    private VendingItemFactory vendingItemFactory;
    private ArrayList<VendingMachineItems> menuOptions;

    public VendingAction() {
        vendingItemFactory = new VendingItemFactory();
        ArrayList<VendingMachineItems> vendingMachineOptions = vendingItemFactory.getPossibleVendingItems();
        // Null object is added so indices and options align
        menuOptions = new ArrayList<>();
        menuOptions.add(null);
        for (int i = 0; i < vendingMachineOptions.size(); i++) {
            menuOptions.add(vendingMachineOptions.get(i));
        }
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (!(actor instanceof Player)) {
            return actor + " is not allowed to access Vending Machine!";
        }
        Display display = new Display();
        displayVendingMachineMenu((Player)actor, display);
        int key = getActorMenuOption(actor, display);
        String description;
        Ecopoints playerEcopoints = ((Player)actor).getEcopoints();
        int itemCost = menuOptions.get(key).getCost();
        if (itemCost > playerEcopoints.getPoints()) {
            description = actor + " does not have enough EcoPoints to purchase " + menuOptions.get(key).getName();
        } else {
            playerEcopoints.removePoints(itemCost);
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
     * @param actor
     * @param display
     * @return
     */
    private int getActorMenuOption(Actor actor, Display display) {
        char key;
        boolean validKey = false;
        do {
            key = display.readChar();
            if (key > 0 && key <= menuOptions.size()) {
                validKey = true;
            }
        } while (!Character.isDigit(key) && validKey);
        return Character.getNumericValue(key);
    }

    /**
     * Prints the display menu for vending machine
     *
     * @param display A Display object that enables use to print an end line
     */
    private void displayVendingMachineMenu(Player player, Display display) {
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
        System.out.println("EcoPoints : " + player.getEcopoints().getPoints());
    }

    @Override
    public String menuDescription(Actor actor) {
        return "access vending machine";
    }

    @Override
    public String hotkey() {
        return "a";
    }
}
