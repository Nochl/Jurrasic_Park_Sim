package game;

import edu.monash.fit2099.engine.*;
import game.actions.VendingAction;


public class VendingMachine extends Ground {

    /***
     * Constructor.
     */
    public VendingMachine() {
        super('!');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        actions.add(new VendingAction());
        return actions;
    }
}
