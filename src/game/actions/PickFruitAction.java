package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.consumable.Fruit;
import game.dinosaur.Stegosaur;
import game.enums.ActorTypeCapabilities;
import game.enums.DietCapabilities;
import game.enums.DinosaurCapabilities;

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
                Fruit added = fruitList.remove(0);
                actor.addItemToInventory(added);
                return ("You picked a "+added.toString()+"!");
            }
            else {
                return ("You search the tree or bush for fruit, but you can’t find any ripe ones");
            }
        }

        else if (actor.hasCapability(DinosaurCapabilities.STEGOSAUR)){
            Fruit added = fruitList.remove(0);
            actor.heal(added.getEatenHealth(actor));
            return (actor.toString()+" has eaten a "+added.toString()+"!");

        }

        else {
            int size = fruitList.size();
            for (int i = 0; i < size; i++){
                actor.heal(fruitList.remove(0).getEatenHealth(actor));
            }
            return (actor.toString()+" has eaten all fruits in the tree!");

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
        return null;
    }
}
