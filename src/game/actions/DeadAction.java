package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Implements an action where dinosaurs die
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see Action
 */
public class DeadAction extends Action {
    /**
     * Shows that actor has died
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    /**
     * String that says actor has died
     * @param actor The actor performing the action.
     * @return String
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has died";
    }
}
