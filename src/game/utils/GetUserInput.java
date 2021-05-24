package game.utils;

import edu.monash.fit2099.engine.Display;

/**
 * Implements a static class that helps get user inputs
 */
public class GetUserInput {
    /**
     * Gets a user input within the range minimum and maximum
     * @param display a Display object
     * @param minimum minimum value that the input can be
     * @param maximum maximum value that the input can be
     * @return a user input in range minimum-maximum
     */
    public static int getInputInRange(Display display, int minimum, int maximum) {
        display.println("Please input a integer between " + minimum + " and " + maximum);
        char key;
        boolean validKey = false;
        int intKey = 0;
        do {
            key = display.readChar();
            if (Character.isDigit(key)) {
                intKey = Character.getNumericValue(key);
                if (intKey >= minimum && intKey <= maximum) {
                    validKey = true;
                }
            }
        } while (!validKey);
        return intKey;
    }
}
