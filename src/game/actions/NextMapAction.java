package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Application;

public class NextMapAction extends Action {
    private GameMap nextmap;
    private Application application;

    public NextMapAction() {
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
        int x = map.locationOf(actor).x();
        int y = map.locationOf(actor).y();






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
        return "Move to the next map";
    }
}
