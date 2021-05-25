package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Application;
import game.MapHolder;

/**
 * Action to move the player from one GameMap to Another
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.2.0
 */
public class NextMapAction extends Action {
    /**
     * Holder of the destination GameMap for the Actor
     */
    private GameMap nextmap;

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        int oldx = map.locationOf(actor).x();
        int oldy = map.locationOf(actor).y();
        int newy;

        nextmap = MapHolder.getOtherMap(map);

        if (oldy != 0){
            newy = 0;
        }
        else {
            newy = nextmap.getYRange().max();
        }

        Location nextlocation = nextmap.at(oldx, newy);


        map.removeActor(actor);
        nextmap.addActor(actor, nextlocation);

        return  actor + " moved to next map";
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
