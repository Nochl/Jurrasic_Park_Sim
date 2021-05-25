package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Implements an action to state that the actor is unconscious
 */
public class UnconsciousAction extends Action {

    /**
     * Executes action of actor being unconscious
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String containing a description of actor being unconscious
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    /**
     * a String containing a description of actor being unconscious
     * @param actor The actor performing the action.
     * @return a String containing a description of actor being unconscious
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is unconscious";
    }
}
