package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.consumable.Corpse;
import game.dinosaur.Dinosaur;
import game.dinosaur.Allosaur;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	public AttackAction(Dinosaur target) {this.target = target; }

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}

		if (actor instanceof Dinosaur && target instanceof Dinosaur) {
			if (((Dinosaur) target).isCurrentlyTimedOut(actor)) {
				return actor + " cannot attack " + target + " because they are timed out";
			}
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		if (actor instanceof Allosaur) {
			actor.heal(20);
		}

		target.hurt(damage);
		if (!target.isConscious()) {
			Corpse corpse = new Corpse(target.toString());
			map.locationOf(target).addItem(corpse);
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		} else {
			if (target instanceof Dinosaur && actor instanceof Dinosaur){
				Dinosaur dinosaur = (Dinosaur) actor;
				((Dinosaur) target).addAttacker(dinosaur);
			}
		}
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
