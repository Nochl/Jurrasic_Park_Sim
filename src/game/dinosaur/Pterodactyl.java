package game.dinosaur;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import game.Counter;
import game.behaviour.GotoTreeBehaviour;
import game.enums.*;

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
     * Stamina for Pterodactyl's flight before they have to land
     */
    Counter stamina = new Counter(30);

    /**
     * Constructor method for Pterodactyl
     *
     * @param name the name of the Actor
     * @param baby a boolean value that denotes if the Pterodactyl being created starts as a baby
     */
    public Pterodactyl(String name, Boolean baby) {
        super(name, 'P', 100, baby);
        maxunconsciousTime = 20;
        maxWater = 100;
        water = 60;
        addCapability(DietCapabilities.CARNIVORE);
        addCapability(DietCapabilities.SEAFOOD);
        addCapability(DinosaurCapabilities.PTERODACTYL);
        addCapability(ActorMobilityCapabilities.FLY);
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
        maxWater = 100;
        water = 60;
        addCapability(DietCapabilities.CARNIVORE);
        addCapability(DietCapabilities.SEAFOOD);
        addCapability(DinosaurCapabilities.PTERODACTYL);
        addCapability(ActorMobilityCapabilities.FLY);
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

    /**
     * Runs the Dinosaurs functionality and behaviours each turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an Action describing what the dinosaur does in the round
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // if its flying
        if (stamina != null && !hasCapability(ActorMobilityCapabilities.WALK)) {
            stamina.dec();
            if (stamina.getValue() < 1) {
                stamina = null;
                removeCapability(ActorMobilityCapabilities.FLY);
                addCapability(ActorMobilityCapabilities.WALK);
            }
        }

        // if it has reached a tree
        if (map.locationOf(this).getGround().hasCapability(GroundTypeCapabilities.TREE)) {
            removeCapability(ActorMobilityCapabilities.WALK);
            addCapability(ActorMobilityCapabilities.FLY);
            stamina = new Counter(30);
        }

        return super.playTurn(actions, lastAction, map, display);
    }
}
