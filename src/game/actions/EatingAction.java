package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.consumable.Consumable;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Stegosaur;
import game.enums.ItemTypeCapabilities;

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

        // Checks if food is corpse
        if (food.hasCapability(ItemTypeCapabilities.CORPSE)) {
           // Only removes corpse if it has no more points (has capability corpse done)
            if (food.hasCapability(ItemTypeCapabilities.CORPSEDONE)) {
                {map.locationOf(dinosaur).removeItem(food);}
            }
        // if its not a corpse, it immediately removes it
       } else {map.locationOf(dinosaur).removeItem(food);}

   }

    @Override
    public abstract String menuDescription(Actor actor);
}
