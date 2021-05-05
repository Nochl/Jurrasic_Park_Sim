package game;

import java.util.ArrayList;

public class EcoHold {
    public static ArrayList<Ecopoints> ecopoints = new ArrayList<>();

    public EcoHold(Ecopoints eco) {}

    public static void addWorldEco(int amount){
        for (Ecopoints eco:ecopoints) {
            eco.addPoints(amount);
        }
    }

    public static void addPlayerEco(Ecopoints eco){
        ecopoints.add(eco);
    }
}
