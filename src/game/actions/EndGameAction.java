package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;

/**
 * An action taken by player when they want the game to end. Removes the player from the game world
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see Action
 * @see game.Player
 */
public class EndGameAction extends Action {

    /**
     * A string containing a description of how the player ended the game
     */
    String description;
    /**
     * Constructor
     */
    public EndGameAction(String description) {
        this.description = description;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return description;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "End game";
    }
}
