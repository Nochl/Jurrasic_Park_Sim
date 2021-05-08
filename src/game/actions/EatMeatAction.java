package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;

public class EatMeatAction extends EatingAction{

    public EatMeatAction(Item newfood) {
        super(newfood);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " devours a " + food;
    }
}
