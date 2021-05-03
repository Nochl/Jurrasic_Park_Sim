package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public abstract class EatingAction extends Action {
    protected Item food;

    public EatingAction(Item food) {
        this.food = food;
    }

    @Override
    public String execute(Actor dinosaur, GameMap map) {
        healActor(dinosaur, map);
        return menuDescription(dinosaur);
    }

    abstract void healActor(Actor dinosaur, GameMap map);

    @Override
    public abstract String menuDescription(Actor actor);
}
