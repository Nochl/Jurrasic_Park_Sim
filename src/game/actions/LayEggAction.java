package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.consumable.eggs.*;
import game.dinosaur.Dinosaur;
import game.enums.DinosaurCapabilities;
import game.enums.ItemTypeCapabilities;

public class LayEggAction extends Action {
    /**
     * Egg from Dinosaur
     */
    private Item egg;

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (Item item:actor.getInventory()) {
            if (item.hasCapability(ItemTypeCapabilities.EGG)) {
                egg = item;
            }
        }
        actor.removeItemFromInventory(egg);
        map.locationOf(actor).addItem(egg);

        return (actor.toString()+" has laid an egg at: "+map.locationOf(actor).x()+","+map.locationOf(actor).y());
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "lay an egg.";
    }
}
