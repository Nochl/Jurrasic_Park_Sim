package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.DinosaurHold;
import game.consumable.corpse.Corpse;
import game.dinosaur.Dinosaur;
import game.enums.CorpseFactory;
import game.enums.DinosaurCapabilities;

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

	/**
	 * Executes action for given actor to attack target Actor
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of how the actor has attacked the target actor
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		// Check if the target is a pterodactyl
		if (target.hasCapability(DinosaurCapabilities.PTERODACTYL)) {
			actor.heal(Integer.MAX_VALUE);
			Corpse corpse = CorpseFactory.getCorpse(target);
			map.locationOf(target).addItem(corpse);
			DropDeadActorInventory(map);
			return System.lineSeparator() + target + " is killed instantly by " + actor;
		}
		Weapon weapon = actor.getWeapon();

		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}

		// Checks if the target and actor have a Dinosaur counterpart
		Dinosaur dinoTarget = DinosaurHold.getDinosaur(target);
		Dinosaur dinoAttacker = DinosaurHold.getDinosaur(actor);
		if (dinoTarget != null && dinoAttacker != null) {
			if (dinoTarget.isCurrentlyTimedOut(dinoTarget)) {
				return actor + " cannot attack " + target + " because they are timed out";
			}
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);

		if (actor.hasCapability(DinosaurCapabilities.ALLOSAUR)) {
			actor.heal(20);
		}

		if (!target.isConscious()) {
			Corpse corpse = CorpseFactory.getCorpse(target);
			map.locationOf(target).addItem(corpse);
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);
			
			result += System.lineSeparator() + target + " is killed.";
		} else {
			if (dinoTarget != null){
				dinoTarget.addAttacker(dinoAttacker);
			}
		}
		return result;
	}

	/**
	 * Provides a description of the action
	 * @param actor The actor performing the action.
	 * @return a string describing the action
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}

	/**
	 * Drops all of the actors items in the game map and removes the actor
	 * @param map a GameMap object
	 */
	private void DropDeadActorInventory(GameMap map) {
		Actions dropActions = new Actions();
		for (Item item : target.getInventory())
			dropActions.add(item.getDropAction());
		for (Action drop : dropActions)
			drop.execute(target, map);
		map.removeActor(target);
	}
}
