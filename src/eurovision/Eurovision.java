/*
El programa principal definirà la estructura de dades per a emmagatzemar el total de
països que participen en la final (26 països), i li demanarà a l'usuari que ompli els seus
noms (només els noms), inicialitzant el comptador de vots a 0 per a cada país.
 */
package eurovision;

import java.util.Scanner;
import utils.Utils;

class Pais {

    String nom;
    int vots;
}

public class Eurovision {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //Marquem el nombre de paisos que hi haura
        final int NUMPAISOS = 26;
        final int WINNER_LOOSER = 2;
        //Creem el vector per guardar el nom dels paisos junt els seus vots
        Pais[] pais = new Pais[NUMPAISOS];
        int[][] matriu_premis = new int[WINNER_LOOSER][NUMPAISOS];
        //Cridem la funcio que demanara que l'usuari introdueixi la informacio sobre els diferents paisos
        IntroduirPaisos(pais, scan);
        //Cridem les votacions per cada pais
        for (int i = 0; i < pais.length; i++) {
            Votar(pais, matriu_premis, i);
        }
        //Cridem la funcio que imprimira els diferents paisos en dues columnes
        MostrarPaisosOrdenats(pais);
        for (int i = 0; i < matriu_premis.length; i++) {
            for (int j = 0; j < matriu_premis[i].length; j++) {
                System.out.println(matriu_premis[i][j]);
            }
            System.out.println();
        }
        int[] guanyadors = EscollirWinners(matriu_premis);
        int[] loosers = EscollirLoosers(matriu_premis);
        MostrarGuanyadors(guanyadors, pais);
        System.out.println();
        MostrarPerdedors(loosers, pais);
    }

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

    static void IntroduirPaisos(Pais[] pais, Scanner scan) {
        for (int i = 0; i < pais.length; i++) {
            System.out.print("Introdueix el nom del pais: ");
            String nomPais = TestStringBlacklist(scan);
            pais[i] = new Pais();
            pais[i].nom = nomPais;
        }
    }

    static String TestStringBlacklist(Scanner scan) {
        String entradaCorrecta;
        String temp;
        boolean paraula = false;
        do {
            int i =0;
            temp = scan.nextLine();
            if (temp.equals("") || temp.equals(" ")){
                System.out.println("No s'ha introduit un pais correctament, torna-ho a intentar: ");
            }
            else{
                if (temp.charAt(i) < 65 || temp.charAt(i) > 122){
                    System.out.println("No s'ha introduit un pais correctament, torna-ho a intentar: ");
                }
                else{
                    paraula = true;
                }
            }
            i++;
        } while (!paraula);
        entradaCorrecta = temp;
        return entradaCorrecta;
    }
   
    static void IntroduirPaisosAutomaticament(Pais[] pais, Scanner scan) {
        for (int i = 0; i < pais.length; i++) {
            String nomPais = paisPosicio(i);
            pais[i] = new Pais();
            pais[i].nom = nomPais;
        }
    }

    static String paisPosicio(int posicio) {
        //Marquem el nombre de paisos que hi haura
        final int NUMPAISOS = 26;
        //Creem el vector per guardar el nom dels paisos junt els seus vots
        String[] pais = new String[NUMPAISOS];
        //Omplim l'array
        pais[0] = "The Netherlands";
        pais[1] = "Italy";
        pais[2] = "Russia";
        pais[3] = "Switzerland";
        pais[4] = "Norway";
        pais[5] = "Sweden";
        pais[6] = "Azerbaijan";
        pais[7] = "North Macedonia";
        pais[8] = "Australia";
        pais[9] = "Iceland";
        pais[10] = "Czech Republic";
        pais[11] = "Denmark";
        pais[12] = "Slovenia";
        pais[13] = "France";
        pais[14] = "Cyprus";
        pais[15] = "Malta";
        pais[16] = "Serbia";
        pais[17] = "Albania";
        pais[18] = "Estonia";
        pais[19] = "San Marino";
        pais[20] = "Greece";
        pais[21] = "Spain";
        pais[22] = "Israel";
        pais[23] = "Germany";
        pais[24] = "Belarus";
        pais[25] = "United Kingdom";
        String nomPais = pais[posicio];
        return nomPais;
    }

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

    static void Rellenar_Winner(int[][] premis, int pais) {
        final int WINNER = 0;
        premis[WINNER][pais]++;
    }

    static void Rellenar_Looser(int[][] premis, int pais) {
        final int LOOSER = 1;
        premis[LOOSER][pais]++;
    }

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

    static int NumeroWinnersLoosers(int[][] premis, int numero, int winnerlooser) {//Funcio per returnar quants loosers o winners hi han
        int winnersloosers = 0;
        for (int i = 0; i < premis[winnerlooser].length; i++) {
            if (premis[winnerlooser][i] == numero) {
                winnersloosers++;
            }
        }
        return winnersloosers;
    }

    static void MostrarGuanyadors(int[] guanyadors, Pais[] pais) {
        if (guanyadors.length == 1) {
             System.out.println("El guanyador del premi “TheBest” és:");
        }
        else{
             System.out.println("Els guanyadors del premi “TheBest” son:");
        }
        for (int i = 0; i < guanyadors.length; i++) {
            System.out.println(pais[guanyadors[i]].nom);
        }
    }

    static void MostrarPerdedors(int[] perdedors, Pais[] pais) {
        if (perdedors.length == 1) {
             System.out.println("El guanyador del segon premi “TheLooser” és:");
        }
        else{
             System.out.println("Els guanyadors del segon premi “TheLooser” son:");
        }
        for (int i = 0; i < perdedors.length; i++) {
            System.out.println(pais[perdedors[i]].nom);
        }
    }
}