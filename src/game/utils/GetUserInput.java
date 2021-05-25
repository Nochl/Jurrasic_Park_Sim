package game.utils;

import edu.monash.fit2099.engine.Display;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Implements a static class that helps get user inputs
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.2.0
 * @see Display
 */
public class GetUserInput {
    /**
     * Gets a user input within the range minimum and maximum
     * @param display a Display object
     * @param minimum minimum value that the input can be
     * @param maximum maximum value that the input can be
     * @return a user input in range minimum-maximum
     */
    public static int askForIntInRange(Display display, int minimum, int maximum) {
        display.println("Please input a integer between " + minimum + " and " + maximum);
        Scanner scanner = new Scanner(System.in);
        int key = 0;
        boolean validKey = false;
        do {
            try {
                key = scanner.nextInt();
                if (key >= minimum && key <= maximum) {
                    validKey = true;
                }
            } catch (InputMismatchException e) {
                validKey = false;
            }
        } while (!validKey);
        return key;
    }
}
