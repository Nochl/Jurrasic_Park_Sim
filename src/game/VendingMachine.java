package game;

import edu.monash.fit2099.engine.Item;
import game.actions.VendingAction;


public class VendingMachine extends Item {

    /***
     * Constructor.
     */
    public VendingMachine() {
        super("vending machine", '!', false);
        allowableActions.add(new VendingAction());
    }

}
