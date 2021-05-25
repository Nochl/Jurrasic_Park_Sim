package game;

import edu.monash.fit2099.engine.*;
import game.actions.VendingAction;

/**
 * Implements a Vending Machine class object that extends from Ground
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 3.0.0
 * @see Ground
 * @see VendingAction
 */
public class VendingMachine extends Ground {

    /***
     * Constructor.
     */
    public VendingMachine() {
        super('!');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Automatically blocks thrown objects at Vending Machine
     * @return a boolean true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    /**
     * Returns allowable actions that can be done to Vending Machine
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an Actions class containing actions that can be done to Vending Machine
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        actions.add(new VendingAction());
        return actions;
    }
}
