package game;

import edu.monash.fit2099.engine.Actor;
import game.dinosaur.Dinosaur;

import java.util.ArrayList;

public class DinosaurHold {
    private static ArrayList<Dinosaur> dinosaurs = new ArrayList<>();

    public static void addDinosaur(Dinosaur dinosaur) {
        dinosaurs.add(dinosaur);
    }

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
