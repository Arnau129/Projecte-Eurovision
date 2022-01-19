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
        //Creem el vector per guardar el nom dels paisos junt els seus vots
        Pais[] pais = new Pais[NUMPAISOS];
        //Cridem la funcio que demanara que l'usuari introdueixi la informacio sobre els diferents paisos
        IntroduirPaisosAutomaticament(pais, scan);
        //Cridem les votacions per cada pais
        int[] seleccio_paisos = new int[10];
        for (int i = 0; i < 9; i++) {
            Utils.Cadena_sense_repeticio_amb_numero_prohibit(seleccio_paisos, pais.length, 0, i);
        }
        for (int i = 0; i < pais.length; i++) {
            Votar(pais, i);
        }
        //Cridem la funcio que imprimira un llistat amb els diferents paisos
        MostrarPaisos(pais);

    }

    static void MostrarPaisos(Pais[] pais) {
        for (int i = 0; i < pais.length; i++) {
            System.out.println("El nom del pais es " + pais[i].nom + " i te " + pais[i].vots + " vots.");
        }
    }
   
    static void IntroduirPaisos(Pais[] pais, Scanner scan) {
        for (int i = 0; i < pais.length; i++) {
            System.out.print("Introdueix el nom del pais: ");
            String nomPais = scan.nextLine();
            pais[i] = new Pais();
            pais[i].nom = nomPais;
        }
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
   
    static void Votar(Pais[] pais, int pais_votador) {
        //Marquem el numero de vegades que es pot votar
        final int PAISOS_A_VOTAR = 10;
        //Creem un vector on guardar a quins paisos votar i l'omplim
        int[] seleccio_paisos = new int[PAISOS_A_VOTAR];
        for (int i = 0; i < PAISOS_A_VOTAR; i++) {
            Utils.Cadena_sense_repeticio_amb_numero_prohibit(seleccio_paisos, pais.length, 0, pais_votador);
        }
        //Creem un vector on guardar quants punts donara i l'omplim
        int[] punts = new int[PAISOS_A_VOTAR];
        for (int i = 0; i < PAISOS_A_VOTAR; i++) {
            Utils.Cadena_sense_repeticio(punts, pais.length, 0);
        }
        //Canviem que es pugui votar amb 9 punts per 12
        for (int i = 0; i < punts.length; i++) {
            if (punts[i] == 9) {
                punts[i] = 12;
            }
        }
        //Sumem ls punts
        for (int i = 0; i < 10; i++) {
            pais[seleccio_paisos[i]].vots = punts[i];
        }

    }
   
}
