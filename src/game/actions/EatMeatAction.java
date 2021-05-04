package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.Corpse;
import game.dinosaur.Allosaur;
import game.eggs.Egg;

public class EatMeatAction extends EatingAction{

    public EatMeatAction(Item food) {
        super(food);
    }

    @Override
    void healActor(Actor dinosaur, GameMap map) {
        if (dinosaur instanceof Allosaur) {
            if (food instanceof Corpse) {
                dinosaur.heal(20);
            } else if (food instanceof Egg) {
                map.locationOf(dinosaur).removeItem(food);
                dinosaur.heal(10);
            } else {
                dinosaur.heal(5);
            }
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " devours a " + food;
    }
}
