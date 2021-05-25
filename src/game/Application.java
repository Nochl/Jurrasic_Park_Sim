package game;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	/**
	 * Runs the application
	 * @param args an empty argument
	 */
	public static void main(String[] args) {
		GameDriver driver = new GameDriver();
		driver.startGame();
	}
}
