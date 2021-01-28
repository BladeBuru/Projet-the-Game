package appli;

import java.util.ArrayList;

public class Joueur {
    ArrayList<String> carteEnMain = new ArrayList<String>(6);
    ArrayList<String> pioche = new ArrayList<String>(60);
    String nom = new String();
    String ascendant;
    String descendant;

    public static void Initialise(Joueur j1, Joueur j2, String nom1, String nom2){
        j1.nom = nom1;
        j2.nom = nom2;

        System.out.println("j1 : " + j1.nom);
        System.out.println("j2 :  + j2.nom");
    }
}
