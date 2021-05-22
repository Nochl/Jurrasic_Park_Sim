package game.dinosaur;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Counter;
import game.actions.AttackAction;
import game.actions.FeedingAction;
import game.enums.DietCapabilities;
import game.enums.DinosaurCapabilities;
import game.enums.DinosaurState;

/**
 * Implements Pterodactyl that extends from Dinosaur Class,
 * eats fish and corpses
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 4.1.0
 * @see Dinosaur
 * @see Counter
 */
public class Pterodactyl extends Dinosaur{
    /**
     * Constructor method for Pterodactyl
     *
     * @param name the name of the Actor
     * @param baby a boolean value that denotes if the Pterodactyl being created starts as a baby
     */
    public Pterodactyl(String name, Boolean baby) {
        super(name, 'P', 100, baby);
        maxunconsciousTime = 20;
        maxThirst = 100;
        thirst = 60;
        addCapability(DietCapabilities.CARNIVORE);
        addCapability(DietCapabilities.SEAFOOD);
        addCapability(DinosaurCapabilities.PTERODACTYL);
        if (baby) {setBabyAttributes();}
        else {growUp();}
    }

    /**
     * Constructor method for Brachiosaur
     * @param name a string name of the dinosaur
     * @param baby a boolean if the dinosaur is a baby
     * @param gender a char to denote that it is 'm' male or 'f' female
     */
    public Pterodactyl(String name, Boolean baby, char gender) {
        super(name, 'P', 100, baby, gender);
        maxunconsciousTime = 20;
        maxThirst = 100;
        thirst = 60;
        addCapability(DietCapabilities.CARNIVORE);
        addCapability(DietCapabilities.SEAFOOD);
        addCapability(DinosaurCapabilities.PTERODACTYL);
        if (baby) {setBabyAttributes();}
        else {growUp();}
    }

    /**
     * Changes the attributes of the dinosaur into those of a baby Pterodactyl
     */
    @Override
    void setBabyAttributes() {
        maxHitPoints = 80;
        hungryHealth = 40;
        breedingHealth = Integer.MAX_VALUE;
        mateTime = Integer.MAX_VALUE;
        matureCounter = new Counter(50);
        addCapability(DinosaurState.BABY);
    }

    @Override
    Counter getAttackTimeoutCounter() {
        return new Counter(50);
    }

    /**
     * Sets the attributes of the dinosaur object to those of an adult Pterodactyl
     */
    @Override
    void growUp() {
        maxHitPoints = 160;
        hungryHealth = 140;
        breedingHealth = 70;
        mateTime = 30;
    }
}
