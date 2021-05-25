package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * Implements a laser gun weapon item
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 1.0.0
 * @see WeaponItem
 */
public class LaserGun extends WeaponItem {
    /**
     * Constructor.
     */
    public LaserGun() {
        super("laser", '>', 50, "zaps");
    }
}
