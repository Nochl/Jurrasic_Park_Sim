package game.actions;

import edu.monash.fit2099.engine.*;
import game.EcoHold;
import game.Player;
import game.PortableItem;
import game.consumable.Consumable;
import game.dinosaur.Dinosaur;
import game.enums.DietCapabilities;
import game.enums.FoodTypeCapabilities;

import java.util.ArrayList;

/**
 * Implements a feeding action
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 3.0.0
 * @see Action
 */
public class FeedingAction extends Action {
    /**
     * a Dinosaur object
     */
    private Dinosaur target;

    /**
     * An array list of items
     */
    private ArrayList<Item> food = new ArrayList<>();

    /**
     * Constructor
     * @param target the Dinosaur we want to target
     */
    public FeedingAction(Dinosaur target) {
        this.target = target;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (target.hasCapability(DietCapabilities.CARNIVORE)) {
            for (Item item:actor.getInventory()){
                if (item.hasCapability(FoodTypeCapabilities.MEAT)) {
                    food.add(item);
                }
            }
        }

        else if (target.hasCapability(DietCapabilities.HERBIVORE)) {
            for (Item item:actor.getInventory()){
                if (item.hasCapability(FoodTypeCapabilities.VEGETABLE)) {
                    food.add(item);
                }
            }
        }

        int selection = displayFeedingActionMenu();
        Item giveFood = food.get(selection-1);
        Consumable give = (Consumable)giveFood;
        target.heal(give.getFedHealth());
        actor.removeItemFromInventory(giveFood);
        EcoHold.addWorldEco(10);
        return (target.toString()+" has been fed a "+give+" and has gained "+give.getEatenHealth(target)+" health!");
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Feed "+target.toString();
    }


    private int displayFeedingActionMenu() {
        Display display = new Display();
        display.println("------------------------");
        display.println("What Would You Like to Feed the Dinosaur?");
        int counter = 1;
        for (Item item:food) {
            display.println(counter + ") " + item.toString());
            counter++;
        }
        display.println("Selection: ");
        return Character.getNumericValue(display.readChar());
    }
}

