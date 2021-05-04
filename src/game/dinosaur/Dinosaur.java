package game.dinosaur;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

import game.Behaviour;
import game.Counter;
import game.WanderBehaviour;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Dinosaur extends Actor {
    protected ArrayList<Behaviour> behaviour = new ArrayList<Behaviour>();
    protected int hungryhealth;
    protected int breedinghealth;
    protected HashMap<Dinosaur, Counter> dinosaurAttackers;
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Dinosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        behaviour.add(new WanderBehaviour());
        dinosaurAttackers = new HashMap<>();
    }



    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        this.hurt(1);

        for (Dinosaur dinosaur : dinosaurAttackers.keySet()) {
            Counter attackTimer = dinosaurAttackers.get(dinosaur);
            attackTimer.dec();
            if (attackTimer.getValue() == 0) {
                dinosaurAttackers.remove(dinosaur);
            }

        }

        for (Behaviour thisbehaviour : behaviour) {
            Action action = thisbehaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    public void addAttacker(Dinosaur dinosaur) {dinosaurAttackers.put(dinosaur, createTimeoutCounter());
    }

    public boolean isCurrentlyTimedOut(Actor dinosaur) {
        Counter attackTimeout = dinosaurAttackers.get(dinosaur);
        return attackTimeout != null;
    }

    abstract Counter createTimeoutCounter();
}


