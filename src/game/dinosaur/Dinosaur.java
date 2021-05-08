package game.dinosaur;

import edu.monash.fit2099.engine.*;

import game.actions.AttackAction;
import game.actions.MatingAction;
import game.behaviour.Behaviour;
import game.Counter;
import game.behaviour.BreedingBehaviour;
import game.actions.FeedingAction;
import game.behaviour.WanderBehaviour;
import game.consumable.Corpse;
import game.enums.DinosaurCapabilities;
import game.enums.Gender;
import game.enums.GroundTypeCapabilities;
import game.enums.DinosaurState;
import game.enums.Mateable;
import game.ground.Dirt;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implements a Dinosaur class that extends from actor.
 * @author  Tim Jordan
 * @author  Enoch Leow
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

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */

    public Dinosaur(String name, char displayChar, int hitPoints, Boolean baby) {
        super(name, displayChar, hitPoints);
        behaviours.add(new BreedingBehaviour());
        behaviours.add(new WanderBehaviour());
        dinosaurAttackers = new HashMap<>();
        canBreed = new Counter(mateTime);
        double random = Math.random();
        if (random <0.5) {
            addCapability(Gender.MALE);
        }
        else {
            addCapability(Gender.FEMALE);
        }

    }

    public Dinosaur(String name, char displayChar, int hitPoints, Boolean baby, char gender) {
        super(name, displayChar, hitPoints);
        behaviours.add(new BreedingBehaviour());
        behaviours.add(new WanderBehaviour());
        dinosaurAttackers = new HashMap<>();
        canBreed = new Counter(mateTime);
        if (gender == 'm') {
            addCapability(Gender.MALE);
        }
        else {
            addCapability(Gender.FEMALE);
        }
    }



    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        checkCanMate();
        if (this.hasCapability(DinosaurCapabilities.BRACHIOSAUR)) {
            double random = Math.random();
            if (random < 0.5) {
                if (map.locationOf(this).getGround().hasCapability(GroundTypeCapabilities.BUSH)) {
                    map.locationOf(this).setGround(new Dirt());
                }
            }
        }
        if (isConscious()) {
            unconsciousTime = null;
            this.hurt(1);
            if (canBreed != null) {
                canBreed.dec();
                if (canBreed.getValue() < 1) {
                    canBreed = null;
                    addCapability(Mateable.MATEABLE);
                }
            }


            for (Actor dinosaur : dinosaurAttackers.keySet()) {
                Counter attackTimer = dinosaurAttackers.get(dinosaur);
                attackTimer.dec();
                if (attackTimer.getValue() == 0) {
                    dinosaurAttackers.remove(dinosaur);
                }
            }
            for (Actor dinosaur : dinosaurAttackers.keySet()) {
                Counter attackTimer = dinosaurAttackers.get(dinosaur);
                attackTimer.dec();
                if (attackTimer.getValue() == 0) {
                    dinosaurAttackers.remove(dinosaur);
                }
            }

            for (Behaviour thisbehaviour : behaviours) {
                Action action = thisbehaviour.getAction(this, map, actions);
                if (action != null) {
                    if (action instanceof MatingAction) {
                        resetMateTime();
                    }
                    return action;
                }
            }
            if (hasCapability(DinosaurState.BABY)) {
                matureCounter.dec();
                if (matureCounter.getValue() == 0) {
                    matureCounter = null;
                    removeCapability(DinosaurState.BABY);
                    addCapability(DinosaurState.ADULT);
                    growUp();
                }
            }

            for (Behaviour thisbehaviour : behaviours) {
                Action action = thisbehaviour.getAction(this, map, actions);
                if (action != null)
                    return action;
            }
            unconsciousCheck(map);

        }   return new DoNothingAction();
    }

    public void addAttacker(Dinosaur dinosaur) {
            dinosaurAttackers.put(dinosaur, getAttackTimeoutCounter());
        }

    public boolean isCurrentlyTimedOut(Actor dinosaur) {
        Counter attackTimeout = dinosaurAttackers.get(dinosaur);
        return attackTimeout != null;
    }

    abstract Counter getAttackTimeoutCounter();

    public void resetMateTime(){
        canBreed = new Counter(mateTime);
    }

    public void checkCanMate(){
        if (!hasCapability(Mateable.MATEABLE) && hitPoints > breedingHealth) {
            addCapability(Mateable.MATEABLE);
        }
        else if (hasCapability(Mateable.MATEABLE) && hitPoints < breedingHealth) {
            removeCapability(Mateable.MATEABLE);
        }
    }

    public void unconsciousCheck(GameMap map){
        if (unconsciousTime == null){
            unconsciousTime = new Counter(maxunconsciousTime);
        }
        else{
            unconsciousTime.dec();
        }

        if (unconsciousTime.getValue() <0) {
            Location location = map.locationOf(this);
            map.removeActor(this);
            location.addItem(new Corpse((this.name+" Corpse"), this));

        }
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

}

