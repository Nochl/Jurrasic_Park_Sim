package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.consumable.Fruit;
import game.enums.ActorTypeCapabilities;

import java.util.ArrayList;

public class PickFruitAction extends Action {
    private ArrayList<Fruit> fruitList;

    public PickFruitAction(ArrayList<Fruit> fruitList) {
        this.fruitList = fruitList;
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
        Double random = Math.random();
        if (actor.hasCapability(ActorTypeCapabilities.PLAYER)){
            if (random < 0.6) {
                actor.addItemToInventory(fruitList.remove(0));
            }
            else {
                return ("You search the tree or bush for fruit, but you can’t find any ripe ones");
            }
        }

        else {

        }
        return null;
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
