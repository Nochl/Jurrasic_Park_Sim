package game;

import edu.monash.fit2099.engine.*;
import game.actions.EndGameAction;
import game.actions.NextMapAction;
import game.enums.ActorTypeCapabilities;
import game.enums.GameModes;
import game.enums.MapCapabilities;

import java.util.ArrayList;

/**
 * Class representing the Player.
 */
public class Player extends Actor {

	/**
	 * A Counter object that denotes the amount of moves the player can take
	 */
	private Counter targetMoves;

	/**
	 * An int object denoting the amount of ecopoints required to win the game
	 */
	private int targetEcopoints;

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
		targetMoves = new Counter(Integer.MAX_VALUE);
		targetEcopoints = Integer.MAX_VALUE;
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
		int movesLeft = targetMoves.getValue();
		// Checks if the challenge mode
		if (hasCapability(GameModes.CHALLENGE)) {
			targetMoves.dec();
			if (targetMoves.getValue() <= 0) {
				String description;
				if (ecopoints.getPoints() >= targetEcopoints) {
					description = "Player was able to reach the target number of ecopoints!\nYou have won!";
				} else {
					description = "Player did not have enough ecopoints within the given time.\nYou have lost";
				}
				return new EndGameAction(description);
			}
			display.println((movesLeft - 1) + " moves left");
			display.endLine();
		}

		// Ticks sky class
		Sky.tick();

		actions.add(new EndGameAction(""));

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

	/**
	 * Sets the number of target moves of player
	 * @param moves an int denoting the number of target moves until game ends
	 */
	public void setTargetMoves(int moves) {
		if (moves != Integer.MAX_VALUE) {
			moves += 1;
		}
		this.targetMoves = new Counter(moves);
	}

	/**
	 * Sets the target ecopoints of player
	 * @param targetEcopoints an int denoting the target eco points
	 */
	public void setTargetEcopoints(int targetEcopoints) {
		this.targetEcopoints = targetEcopoints;
	}
}
