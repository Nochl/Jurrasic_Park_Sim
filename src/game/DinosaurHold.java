package game;

import edu.monash.fit2099.engine.Actor;
import game.dinosaur.Dinosaur;

import java.util.ArrayList;

/**
 * Implements a class which holds all the dinosaur objects in the world
 * @author Tim Jordan
 * @author Enoch Leow
 * @version 5.0.0
 */
public class DinosaurHold {
    /**
     * ArrayList of Dinosaur objects
     */
    private static ArrayList<Dinosaur> dinosaurs = new ArrayList<>();

    /**
     * Add a dinosaur object to the ArrayList
     * @param dinosaur Dinosaur object
     */
    public static void addDinosaur(Dinosaur dinosaur) {
        dinosaurs.add(dinosaur);
    }

    /**
     * Gets Dinosaur object of an Actor
     * @param actor Actor object that we want the Dinosaur Object of
     * @return Dinosaur Object
     */
    public static Dinosaur getDinosaur(Actor actor) {
        Dinosaur object = null;
        for (Dinosaur dino : dinosaurs) {
            if (dino == actor) {
                object = dino;
            }
        }
        return object;
    }
}
