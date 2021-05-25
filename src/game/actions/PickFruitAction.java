package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.EcoHold;
import game.consumable.Fruit;
import game.enums.ActorTypeCapabilities;
import game.enums.DinosaurCapabilities;

import java.util.ArrayList;

/**
 * Implements action for actors to pick a fruit
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 3.0.0
 * @see game.ground.Bush
 * @see Action
 * @see Actor
 * @see Fruit
 */
public class PickFruitAction extends Action {

    /**
     * an Array list containing Fruit items
     */
    private ArrayList<Fruit> fruitList;

    /**
     * Constructor
     * @param fruitList an Array list containing fruit items
     */
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
        double random = Math.random();
        if (actor.hasCapability(ActorTypeCapabilities.PLAYER)){
            if (random < 0.6) {
                Fruit added = fruitList.remove(0);
                actor.addItemToInventory(added);
                EcoHold.addWorldEco(10);
                return ("You picked a "+added.toString()+"!");
            }
            else {
                return ("You search the tree or bush for fruit, but you can’t find any ripe ones");
            }
        }

        else if (actor.hasCapability(DinosaurCapabilities.STEGOSAUR)){
            Fruit added = fruitList.remove(0);
            actor.heal(added.getEatenHealth(actor));
            return (actor+" has eaten a "+added+"!");

        }

        else {
            int size = fruitList.size();
            for (int i = 0; i < size; i++){
                actor.heal(fruitList.remove(0).getEatenHealth(actor));
            }
            return (actor+" has eaten all fruits in the tree!");

        }
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */

    @Override
    public String menuDescription(Actor actor) {
        return (actor.toString()+" picks fruit from tree/bush");
    }
}
