package appli;

import java.util.Collections;
import java.util.Scanner;

public class fonction {
    public static void initialiser(Joueur j, String nom) {
        j.ascendant = "60";
        j.descendant = "01";
        j.nom = nom;

        for (int i = 1; i < 59; i++) {
            int t = i + 1;
            if (i < 9) {
                j.pioche.add("0" + t);
            } else {
                j.pioche.add("" + t);
            }
        }

        piocher(j, 6);
    }

    public static void piocher(Joueur j, int i) {
        for (int l = 0; l < i; l++) {
            int indiceAuHasard = (int) (Math.random() * (j.pioche.size() - 1));
            j.carteEnMain.add(j.pioche.get(Integer.parseInt(String.valueOf(indiceAuHasard))));
            j.pioche.remove(indiceAuHasard);
        }
        Collections.sort(j.carteEnMain);  // Sort myNumbers
    }

    public static void afficher(Joueur j, Joueur j2, boolean o) {

        System.out.println(j.nom + " ^[" + j.descendant + "] " + "v[" + j.ascendant + "]" + " (m" +
                j.carteEnMain.size() + "p" + j.pioche.size() + ")");
        System.out.println(j2.nom + " ^[" + j2.descendant + "] " + "v[" + j2.ascendant + "]" + " (m" +
                j2.carteEnMain.size() + "p" + j2.pioche.size() + ")");

        if (o) {
            System.out.print("cartes " + j2.nom + " ");
            System.out.print("{ ");
            for (String i : j2.carteEnMain)
                System.out.print(i + " ");

            System.out.println("}");
        } else {
            System.out.print("cartes " + j.nom + " ");
            System.out.print("{ ");
            for (String i : j.carteEnMain) {
                System.out.print(i + " ");
            }
            System.out.println("}");
        }

    }

    public static void jouer(Joueur j, Joueur j2, Scanner sc) {
        int indiceDepart = (int) (Math.random() * (2));

        while(indiceDepart<5){

            if (indiceDepart % 2 == 0){
                afficher(j,j2,false);
                separateurDeSaisi(sc,j);
            }else{
                afficher(j,j2,true);
                separateurDeSaisi(sc,j2);
            }
            indiceDepart++;
        }
    }

    public static void separateurDeSaisi(Scanner sc, Joueur j) {
        System.out.print("> ");
        int i = 0;
        String saisie = sc.nextLine();
        String[] arrOfStr = saisie.split(" ");
        while (!estValide(arrOfStr, j)) {
            System.out.print("#> ");
            String jsp = sc.nextLine();
            arrOfStr = jsp.split(" ");

        }

        int cartePiocher = calculCartePiocher(arrOfStr, j);

        System.out.println(arrOfStr.length + " cartes posées, " + cartePiocher + " cartes piochées");
//        for (String a : arrOfStr)
//            System.out.println(a);
    }

    public static boolean estValide(String[] str, Joueur j) {
        boolean bien = true, cartePrésenteEnMain;

        if(str.length<2)
            return false;

        for (int i = 0; i < str.length; i++) {
            String nombre = "";
            if (str[i].length() >= 2)
                nombre = "" + str[i].charAt(0) + str[i].charAt(1);

            String signe = "";
            if (str[i].length() > 2)
                signe = "" + str[i].charAt(2);
            String adverse = "";
            if(str[i].length() > 3)
                adverse = "" + str[i].charAt(3);

            cartePrésenteEnMain = false;
            for (String carte : j.carteEnMain) {
                if (nombre.equals(carte)) {
                    cartePrésenteEnMain = true;
                }
                //System.out.println(carte);
            }
            if (!(signe.equals("v") || signe.equals("^")))
                return false;
            if (!(adverse.equals("'") || str[i].length() <= 3))
                return false;
            if (!cartePrésenteEnMain)
                return false;
        }
        return bien;
    }

    public static int calculCartePiocher(String[] str, Joueur j){

        int nb = str[0].length();
        for (int i = 1; i < str.length; i++) {
            if(nb < str[i].length())
                nb = str[i].length();
        }
        if(nb == 3)
            return 2;
        return  6 - j.carteEnMain.size();
    }
}

