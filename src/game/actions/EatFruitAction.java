package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Stegosaur;

public class EatFruitAction extends EatingAction {

    public EatFruitAction(Item newfood) {
        super(newfood);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " chomps on a " + food;
    }
}
