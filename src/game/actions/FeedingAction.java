package game.actions;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.PortableItem;
import game.consumable.Consumable;
import game.dinosaur.Dinosaur;
import game.enums.DietCapabilities;
import game.enums.FoodTypeCapabilities;

import java.util.ArrayList;

public class FeedingAction extends Action {
    private Dinosaur target;
    private ArrayList<Item> food = new ArrayList<>();

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
        target.heal(give.getEatenHealth(target));
        actor.removeItemFromInventory(giveFood);
        return (target.toString()+" has been fed a "+give.toString()+" and has gained "+give.getEatenHealth(target)+" health!");
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Feed Dinosaur";
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

