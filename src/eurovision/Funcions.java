/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eurovision;

import java.util.Scanner;
import utils.Utils;

/**
 * Classe on estan totes les funcions
 * @author Angel, Arnau, Andrés
 */
public class Funcions {
    /**
     * Funció per a saber si l'usuari vol que acabi el programa o que torni a fer el concurs un altre vegada.
     * @param scan és el scanner que es crea en la class on implementes la funció.
     * @return la decisió de l'usuari.
     */
    static boolean Repetirprograma(Scanner scan){
        boolean comprovant = true;
        System.out.println("Vols fer un altre vegade el concurs?(Y/N)");
        String entrada = scan.nextLine();
        if (entrada.equals("Y") || entrada.equals("y")) {
            comprovant = true;
        }else if (entrada.equals("N") || entrada.equals("n")) {
            comprovant = false;
        }else {
            System.out.println("Valor incorrecte.");
            comprovant=Repetirprograma(scan);
        }
        return comprovant;
    }
    /**
     * Funció per a ordenar el vector de països passat en la funció.
     * @param pais és el vector on estan guardat tots els països.
     */
    static void MostrarPaisosOrdenats(Pais[] pais) {
        for (int x = 0; x < pais.length; x++) {
            for (int i = 0; i < pais.length - x - 1; i++) {
                if (pais[i].vots < pais[i + 1].vots) {
                    Pais[] tmp = new Pais[1];
                    tmp[0] = pais[i + 1];
                    pais[i + 1] = pais[i];
                    pais[i] = tmp[0];
                }
            }
        }
        for (int i = 0; i < 13; i++) {
            System.out.printf("\n %3s %-15s %3s %6s %3s %-15s %3s \n", (i + 1) + ".", pais[i].nom, pais[i].vots, "", (i + 14) + ".", pais[i + 13].nom, pais[i + 13].vots);

        }
    }
    /**
     * Funció que demana a l'usuari que introdueixi el nom dels països i els guardi.
     * @param pais és el vector de països buit.
     * @param scan és el scanner que es crea en la class on implementes la funció.
     */
    static void IntroduirPaisos(Pais[] pais, Scanner scan) {
        for (int i = 0; i < pais.length; i++) {
            System.out.print("Introdueix el nom del pais: ");
            String nomPais = TestStringBlacklist(scan);
            pais[i] = new Pais();
            pais[i].nom = nomPais;
        }
    }
    /**
     * Funció que s'encarrega de dir si el país que l'usuari introdueix el fa de manera correcta o errònia.
     * @param scan és el scanner que es crea en la class on implementes la funció.
     * @return boolean que diu si el país s'introdueix correctament o no.
     */
    static String TestStringBlacklist(Scanner scan) {
        String entradaCorrecta;
        String temp;
        boolean paraula = false;
        do {
            int i = 0;
            temp = scan.nextLine();
            if (temp.equals("") || temp.equals(" ")) {
                System.out.println("No s'ha introduit un pais correctament, torna-ho a intentar: ");
            } else {
                if (temp.charAt(i) < 65 || temp.charAt(i) > 122) {
                    System.out.println("No s'ha introduit un pais correctament, torna-ho a intentar: ");
                } else {
                    paraula = true;
                }
            }
            i++;
        } while (!paraula);
        entradaCorrecta = temp;
        return entradaCorrecta;
    }
    /**
     * Funció que fa que el pais_votador voti i alhora es guarda en la matriu premis els vots que ha fet i també els vots que no ha fet.
     * @param pais és el vector de països.
     * @param premis és la matriu on es guarden els winners i els loosers.
     * @param pais_votador és el país que està votant.
     */
    static void Votar(Pais[] pais, int[][] premis, int pais_votador) {
        //Marquem el numero de vegades que es pot votar
        final int PAISOS_A_VOTAR = 10;
        //Creem un vector on guardar a quins paisos votar i l'omplim
        int[] seleccio_paisos = new int[PAISOS_A_VOTAR];
        Utils.Cadena_sense_repeticio_amb_numero_prohibit(seleccio_paisos, (pais.length - 1), 0, pais_votador);
        for (int i = 0; i < seleccio_paisos.length; i++) {
            Rellenar_Looser(premis, seleccio_paisos[i]);
        }
        //Creem un vector on guardar quants punts donara i l'omplim
        int[] punts = new int[PAISOS_A_VOTAR];
        Utils.Cadena_sense_repeticio(punts, 10, 1);
        //Canviem que es pugui votar amb 9 punts per 12
        for (int i = 0; i < punts.length; i++) {
            if (punts[i] == 9) {
                punts[i] = 12;
                Rellenar_Winner(premis, seleccio_paisos[i]);
            }
        }
        //Sumem ls punts
        for (int i = 0; i < 10; i++) {
            pais[seleccio_paisos[i]].vots = pais[seleccio_paisos[i]].vots + punts[i];
        }
    }
    /**
     * Funció que emplena la matriu premis i dins d'ella winners.
     * @param premis és la matriu on es guarden els winners i els loosers.
     * @param pais és el país al qual se li suma 1 si ha obtingut un vot de 12.
     */
    static void Rellenar_Winner(int[][] premis, int pais) {
        final int WINNER = 0;
        premis[WINNER][pais]++;
    }
    /**
     * Funció que emplena la matriu premis i dins d'ella loosers.
     * @param premis és la matriu on es guarden els winners i els loosers.
     * @param pais és el país al qual se li suma 1 si ha obtingut qualsevol vot.
     */
    static void Rellenar_Looser(int[][] premis, int pais) {
        final int LOOSER = 1;
        premis[LOOSER][pais]++;
    }
    /**
     * Funció que a partir de la matriu premis saca un vector on estan tots els winners.
     * @param premis és la matriu on es guarden els winners i els loosers.
     * @return un vector on estan guardat tots els països que han guanyat el premi "TheBest".
     */
    static int[] EscollirWinners(int[][] premis) {
        final int WINNER = 0;
        int numeroGran = premis[WINNER][0];//inicialitzem al primer numero del vector premis per a que sigui l'inicialitzador
        int contadorGuanyadors = 0;//Aquest contador l'usarem a l'hora de guardar els guanyadors
        for (int i = 0; i < premis[WINNER].length; i++) {//Bucle per trobar el numero més gran del contador de 12 vots
            if (premis[WINNER][i] > numeroGran) {
                numeroGran = premis[WINNER][i];
            }
        }
        int guanyadors = NumeroWinnersLoosers(premis, numeroGran, WINNER);//Trobem quants guanyadors hi han per crear el vector
        int[] winners = new int[guanyadors];//Creem el vector apartir de quants guanyadors hi han
        for (int i = 0; i < premis[WINNER].length; i++) {//Bucle per a que passi per tota la matriu de premis i guardi els guanyadors al vector winners
            if (premis[WINNER][i] == numeroGran) {
                winners[contadorGuanyadors] = i;
                contadorGuanyadors++;
            }
        }
        return winners;
    }
    /**
     * Funció que a partir de la matriu premis saca un vector on estan tots els loosers.
     * @param premis és la matriu on es guarden els winners i els loosers.
     * @return un vector on estan guardat tots els països que han guanyat el premi "TheLooser".
     */
    static int[] EscollirLoosers(int[][] premis) {
        final int LOOSER = 1;
        int numeroPetit = premis[LOOSER][0];//inicialitzem al primer numero del vector premis per a que sigui l'inicialitzador
        int contadorLoosers = 0;//Aquest contador l'usarem a l'hora de guardar els perdedors
        for (int i = 0; i < premis[LOOSER].length; i++) {//Bucle per trobar el numero més gran del contador de no vots
            if (premis[LOOSER][i] < numeroPetit) {
                numeroPetit = premis[LOOSER][i];
            }
        }
        int perdedors = NumeroWinnersLoosers(premis, numeroPetit, LOOSER);//Trobem quants perdedors hi han per crear el vector
        int[] loosers = new int[perdedors];//Creem el vector apartir de quants perdedors hi han
        for (int i = 0; i < premis[LOOSER].length; i++) {//Bucle per a que passi per tota la matriu de premis i guardi els perdedors al vector loosers
            if (premis[LOOSER][i] == numeroPetit) {
                loosers[contadorLoosers] = i;
                contadorLoosers++;
            }
        }
        return loosers;
    }
    /**
     * Funció que treu quants winners o loosers hi ha, depenent del que vulguis, que això es tria en el paràmetre "winnerlooser".
     * @param premis és la matriu on es guarden els winners i els loosers.
     * @param numero és el número més gran o més petit que hi ha en la matriu premis on s'haurà de comparar entre la matriu premis.
     * @param winnerlooser és on tries que vols treure, o winners o loosers.
     * @return un vector on estan tots els països que són winners o loosers.
     */
    static int NumeroWinnersLoosers(int[][] premis, int numero, int winnerlooser) {//Funcio per returnar quants loosers o winners hi han
        int winnersloosers = 0;
        for (int i = 0; i < premis[winnerlooser].length; i++) {
            if (premis[winnerlooser][i] == numero) {
                winnersloosers++;
            }
        }
        return winnersloosers;
    }
    /**
     * Funció que mostra els guanyadors del premi "TheBest".
     * @param guanyadors és el vector on estan tots els guanyadors.
     * @param pais és el vector de països.
     */
    static void MostrarGuanyadors(int[] guanyadors, Pais[] pais) {
        if (guanyadors.length == 1) {
            System.out.println("El guanyador del premi “TheBest” és:");
        } else {
            System.out.println("Els guanyadors del premi “TheBest” son:");
        }
        for (int i = 0; i < guanyadors.length; i++) {
            System.out.println(pais[guanyadors[i]].nom);
        }
    }
    /**
     * Funció que mostra els perdedors del premi "TheLooser".
     * @param perdedors és el vector on estan tots els perdedors.
     * @param pais és el vector de països.
     */
    static void MostrarPerdedors(int[] perdedors, Pais[] pais) {
        if (perdedors.length == 1) {
            System.out.println("El guanyador del segon premi “TheLooser” és:");
        } else {
            System.out.println("Els guanyadors del segon premi “TheLooser” son:");
        }
        for (int i = 0; i < perdedors.length; i++) {
            System.out.println(pais[perdedors[i]].nom);
        }
    }
}
