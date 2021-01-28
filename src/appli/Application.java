package appli;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Joueur j1 = new Joueur();
        Joueur j2 = new Joueur();

        fonction.initialiser(j1,"NORD");
        fonction.initialiser(j2,"SUD");


        fonction.jouer(j1, j2, sc);

        sc.close();

    }
}
