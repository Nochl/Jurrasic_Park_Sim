package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.PickFruitAction;
import game.consumable.Fruit;

import java.util.ArrayList;

public class Bush extends Ground{
    private ArrayList<Fruit> inBush = new ArrayList<>();
    /**
     * Constructor.
     *
     */
    public Bush() {
        super('b');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        return new Actions(new PickFruitAction(inBush));
    }
}
