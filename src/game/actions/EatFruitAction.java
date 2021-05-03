package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Stegosaur;

public class EatFruitAction extends EatingAction {

    public EatFruitAction(Item food) {
        super(food);
    }

    @Override
    void healActor(Actor dinosaur, GameMap map) {
        map.locationOf(dinosaur).removeItem(food);
        if (dinosaur instanceof Brachiosaur) {
            dinosaur.heal(10);
        } else if (dinosaur instanceof Stegosaur) {
            dinosaur.heal(5);
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " chomps on a " + food;
    }
}
