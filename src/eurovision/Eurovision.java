/*
El programa principal definirà la estructura de dades per a emmagatzemar el total de
països que participen en la final (26 països), i li demanarà a l'usuari que ompli els seus
noms (només els noms), inicialitzant el comptador de vots a 0 per a cada país.
 */
package eurovision;

import java.util.Scanner;

class Pais {
    
    String nom;
    int vots;
}

public class Eurovision {

    /**
     * Classe main on s'executa el programa.
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //Marquem el nombre de paisos que hi haura
        final int NUMPAISOS = 26;
        final int WINNER_LOOSER = 2;
        boolean repetir = true;

        //Creem el vector per guardar el nom dels paisos junt els seus vots
        Pais[] pais = new Pais[NUMPAISOS];

        //Cridem la funcio que demanara que l'usuari introdueixi la informacio sobre els diferents paisos
        Funcions.IntroduirPaisos(pais, scan);
        do {
            Funcions.Buidarvots(pais);
            int[][] matriu_premis = new int[WINNER_LOOSER][NUMPAISOS];
            //Cridem les votacions per cada pais
            for (int i = 0; i < pais.length; i++) {
                Funcions.Votar(pais, matriu_premis, i);
            }
            //Cridem la funcio que imprimira els diferents paisos en dues columnes
            Funcions.MostrarPaisosOrdenats(pais);
            System.out.println();
            int[] guanyadors = Funcions.EscollirWinners(matriu_premis);
            int[] loosers = Funcions.EscollirLoosers(matriu_premis);
            Funcions.MostrarGuanyadors(guanyadors, pais);
            System.out.println();
            Funcions.MostrarPerdedors(loosers, pais);
            repetir = Funcions.Repetirprograma(scan);
        } while (repetir);
    }    
}
