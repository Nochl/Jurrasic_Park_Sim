package game;

import edu.monash.fit2099.engine.*;
import game.consumable.Consumable;
import game.enums.ActorTypeCapabilities;
import game.enums.FoodTypeCapabilities;

import java.util.ArrayList;

/**
 * Class representing the Player.
 */
public class Player extends Actor {

	private Ecopoints ecopoints;
	private Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		ecopoints = new Ecopoints();
		addCapability(ActorTypeCapabilities.PLAYER);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		return menu.showMenu(this, actions, display);
	}

	public Ecopoints getEcopoints() {
		return ecopoints;
	}
}
