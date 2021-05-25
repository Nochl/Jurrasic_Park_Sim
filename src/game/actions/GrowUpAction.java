package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Implements an action that show that the actor has grown up
 * @author Tim Jordan
 * @author Enoch Leow
 * @see Actor
 * @see Action
 */
public class GrowUpAction extends Action {

    /**
     * executes grow up action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing actor growing up
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    /**
     * gets a description of action
     * @param actor The actor performing the action.
     * @return a string containing description
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has matured into an adult!";
    }
}
