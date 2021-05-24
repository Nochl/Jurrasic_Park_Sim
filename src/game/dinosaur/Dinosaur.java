package game.dinosaur;

import com.sun.source.doctree.HiddenTree;
import edu.monash.fit2099.engine.*;

import game.actions.*;
import game.behaviour.*;
import game.Counter;
import game.consumable.Corpse;
import game.enums.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implements a Dinosaur class that extends from actor.
 *
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 4.1.0
 * @see Actor
 * @see Counter
 */
public abstract class Dinosaur extends Actor {
    protected ArrayList<Behaviour> behaviours = new ArrayList<>();
    protected int hungryHealth;
    protected int breedingHealth;
    protected HashMap<Actor, Counter> dinosaurAttackers;
    protected Counter canBreed;
    protected Counter unconsciousTime;
    protected int mateTime;
    protected int maxunconsciousTime = Integer.MAX_VALUE;
    protected Counter matureCounter = null;
    protected int maxWater;
    protected int water;


    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */

    public Dinosaur(String name, char displayChar, int hitPoints, Boolean baby) {
        super(name, displayChar, hitPoints);
        addBehaviours();
        dinosaurAttackers = new HashMap<>();
        canBreed = new Counter(mateTime);
        double random = Math.random();

        // Sets dinosaur gender randomly
        if (random < 0.5) {
            addCapability(Gender.MALE);
        } else {
            addCapability(Gender.FEMALE);
        }

    }

    public Dinosaur(String name, char displayChar, int hitPoints, Boolean baby, char gender) {
        super(name, displayChar, hitPoints);
        addBehaviours();
        dinosaurAttackers = new HashMap<>();
        canBreed = new Counter(mateTime);

        // Sets dinosaur gender according to input
        if (gender == 'm') {
            addCapability(Gender.MALE);
        } else {
            addCapability(Gender.FEMALE);
        }
    }


    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        System.out.println(hitPoints);

        // Display if Dinosaur is Hungry
        if (hitPoints < hungryHealth && !hasCapability(HungryCapabilities.HUNGRY)) {
            addCapability(HungryCapabilities.HUNGRY);
            display.println(this.toString()+" at "+map.locationOf(this).x()+","+map.locationOf(this).x()+" is hungry!");
        }

        // Removes hungry status (if hunger above an amount)
        if (hitPoints > hungryHealth && hasCapability(HungryCapabilities.HUNGRY)) {
            removeCapability(HungryCapabilities.HUNGRY);
        }

        // Check/Display if Dinosaur is Thirsty
        if (water < 40 && !hasCapability(ThirstCapabilities.THIRSTY)) {
            addCapability(ThirstCapabilities.THIRSTY);
            display.println(this.toString()+" at "+map.locationOf(this).x()+","+map.locationOf(this).x()+" is thirsty!");
        }

        // Removes thirsty status (if water above 40)
        if (water > 40 && hasCapability(ThirstCapabilities.THIRSTY)) {
            removeCapability(ThirstCapabilities.THIRSTY);
        }

        // Check to add mating status
        if (!hasCapability(Mateable.MATEABLE) && !hasCapability(DinosaurState.BABY) && canBreed == null) {
            addCapability(Mateable.MATEABLE);

        // Check to remove mating status
        } else if (hasCapability(Mateable.MATEABLE) && canBreed != null) {
            removeCapability(Mateable.MATEABLE);
        }

        // Running dinosaur functions (if its conscious)
        if (isConscious()) {
            unconsciousTime = null;
            // Decrementing Health and Thirst
            this.hurt(1);
            this.thirst(1);

            // If water level goes to 0, make dinosaur unconscious by reducing its health to 0
            if (water <= 0) {
                hitPoints = 0;
            }

            // Decrement breeding cooldown (if one exists)
            if (canBreed != null) {
                canBreed.dec();
                if (canBreed.getValue() < 1) {
                    canBreed = null;
                }
            }


            for (Actor dinosaur : dinosaurAttackers.keySet()) {
                Counter attackTimer = dinosaurAttackers.get(dinosaur);
                attackTimer.dec();
                if (attackTimer.getValue() == 0) {
                    dinosaurAttackers.remove(dinosaur);
                }
            }


            // check if dinosaur should grow
            if (hasCapability(DinosaurState.BABY)) {
                matureCounter.dec();
                if (matureCounter.getValue() == 0) {
                    matureCounter = null;
                    removeCapability(DinosaurState.BABY);
                    addCapability(DinosaurState.ADULT);
                    growUp();
                }
            }

            // Runs all behaviours, uses the one which is not null
            for (Behaviour thisbehaviour : behaviours) {
                Action action = thisbehaviour.getAction(this, map, actions);
                if (action != null) {
                    if (action instanceof MatingAction) {
                        resetMateTime();
                    }
                    return action;
                }
                // If theres no available behaviours, return a new DoNothingAction
            }  return new DoNothingAction();
        }

        // Dinosaur dies if unconscious for too long
        if (unconsciousCheck(map)) {
            return new DeadAction();
        }

        // if unconscious
        return new UnconsciousAction();
    }

    public void addAttacker(Dinosaur dinosaur) {
        dinosaurAttackers.put(dinosaur, getAttackTimeoutCounter());
    }


    public boolean isCurrentlyTimedOut(Actor dinosaur) {
        Counter attackTimeout = dinosaurAttackers.get(dinosaur);
        return attackTimeout != null;
    }

    abstract Counter getAttackTimeoutCounter();

    public void resetMateTime() {
        canBreed = new Counter(mateTime);
    }


    public boolean unconsciousCheck(GameMap map) {
        if (unconsciousTime == null) {
            unconsciousTime = new Counter(maxunconsciousTime);
        } else {
            unconsciousTime.dec();
        }

        if (unconsciousTime.getValue() <= 0) {
            Location location = map.locationOf(this);
            map.removeActor(this);
            location.addItem(new Corpse((this.name + " Corpse"), this));
            return true;
        }
        return false;
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions action = new Actions();
        action.add(new FeedingAction(this));
        if (isConscious()) {
            action.add(new AttackAction(this));
        }
        return action;
    }


    abstract void growUp();

    abstract void setBabyAttributes();

    public void addBehaviours() {

        behaviours.add(new ScavengingBehaviour());
        behaviours.add(new HuntingBehaviour());
        behaviours.add(new BreedingBehaviour());
        behaviours.add(new WanderBehaviour());
    }

    /////////////////////////////////////////////////////////////////////
    /**
     * Add water points to the current Actor's water total.
     *
     * This cannot take the water over the Actor's maximum. If there is an
     * overflow, water are silently capped at the maximum.
     *
     * Does not check for consciousness: unconscious Actors can still be healed
     * if the game client allows.
     *
     * @param points number of water to add.
     */
    public void drink(int points) {
        water += points;
        water = Math.min(water, maxWater);
    }

    /**
     * To reduce water level on dinosaur
     *
     * If the Actor's water level goes down to zero, it will be knocked out (by making its health 0).
     *
     * @param points number of water points to deduct.
     */
    public void thirst(int points) {
        water -= points;
    }



}

