package game;

import edu.monash.fit2099.engine.*;
import game.actions.EndGameAction;
import game.actions.NextMapAction;
import game.consumable.Consumable;
import game.enums.ActorTypeCapabilities;
import game.enums.FoodTypeCapabilities;
import game.enums.MapCapabilities;

import java.util.ArrayList;

/**
 * Class representing the Player.
 */
public class Player extends Actor {

	/**
	 * A Counter object that denotes the amount of moves the player can take
	 */
	Counter targetMoves;

	/**
	 * An int object denoting the amount of ecopoints required to win the game
	 */
	int targetEcopoints;

	/**
	 * Ecopoints object which stores the amount of ecopoints the player has
	 */
	private Ecopoints ecopoints;

	/**
	 * Menu object for displaying the players possible actions
	 */
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

	/**
	 * Determines what the player's actions are for current game turn
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return an Action class object denoting the action that the player will take in this turn
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		Sky.tick();
		actions.add(new EndGameAction());

		if (map.locationOf(this).getGround().hasCapability(MapCapabilities.EDGEMAP)){
			actions.add(new NextMapAction());
		}
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Gets the player's ecopints
	 * @return an Ecopoints object
	 */
	public Ecopoints getEcopoints() {
		return ecopoints;
	}
}
