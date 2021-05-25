package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.DinosaurHold;
import game.dinosaur.Dinosaur;
import game.enums.DinosaurCapabilities;
import game.ground.Lake;

/**
 * Implements an action where dinosaurs are able to drink from the Lake object
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see game.ground.Lake
 * @see Action
 */
public class DrinkLakeAction extends Action {
    private Lake lake;

    public DrinkLakeAction(Lake lake) {
        this.lake = lake;
    }

    /**
     * Executes the action of actor drinking form the lake
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing how the actor drank from the lake
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dino = DinosaurHold.getDinosaur(actor);
        if (dino == null) {
            return actor + " is not capable of drinking from lake";
        }
        int lakeSipCapacity = lake.getSipCapacity();
        int maxSipsGiven;
        if (dino.hasCapability(DinosaurCapabilities.BRACHIOSAUR)) {
            maxSipsGiven = 80;
        } else {
            maxSipsGiven = 30;
        }
        int actualSipsGiven = Math.min(lakeSipCapacity, maxSipsGiven);
        // reduce the sip capacity in lake
        lake.reduceSipCapacity(actualSipsGiven);

        // increase thirst level in dinosaur
        dino.drink(actualSipsGiven);

        return menuDescription(actor);
    }

    /**
     * A description of the action in the menu
     * @param actor The actor performing the action.
     * @return a string containing the description
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks from lake";
    }
}
