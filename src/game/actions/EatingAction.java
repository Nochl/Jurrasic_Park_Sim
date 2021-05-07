package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.consumable.Consumable;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Stegosaur;

public abstract class EatingAction extends Action {
    protected Item item;
    protected Consumable food;

    public EatingAction(Item newfood) {
        this.item = newfood;
        this.food = (Consumable)item;
    }

    @Override
    public String execute(Actor dinosaur, GameMap map) {
        healActor(dinosaur, map);
        return menuDescription(dinosaur);
    }

   public void healActor(Actor dinosaur, GameMap map){
        dinosaur.heal(food.getEatenHealth(dinosaur));
        map.locationOf(dinosaur).removeItem(food);

   }

    @Override
    public abstract String menuDescription(Actor actor);
}
