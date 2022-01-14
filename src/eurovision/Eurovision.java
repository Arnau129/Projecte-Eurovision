/*
El programa principal definirà la estructura de dades per a emmagatzemar el total de
països que participen en la final (26 països), i li demanarà a l'usuari que ompli els seus
noms (només els noms), inicialitzant el comptador de vots a 0 per a cada país.
 */
package eurovision;

import java.util.Scanner;
import utils.Utils;
import static utils.Utils.Cadena_sense_repeticio;
import static utils.Utils.Cadena_sense_repeticio_amb_numero_prohibit;

class Pais {

    String nom;
    int vots;
}

public class Eurovision {

    public static void MostrarPais(Pais pais) {
        System.out.println("El nombre del pais es " + pais.nom + " i te " + pais.vots + " vots.");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int [] vector = new int [10];
        final int NUMPAISOS = 26;
        Pais[] pais = new Pais[NUMPAISOS];
        Cadena_sense_repeticio_amb_numero_prohibit(vector, 26, 0, 10);
        for (int i = 0; i < vector.length; i++){
        System.out.println(vector[i]);
        }
        for (int i = 0; i < pais.length; i++) {
            System.out.print("Introdueix el nom del pais: ");
            String nomPais = scan.nextLine();
            pais[i] = new Pais();
            pais[i].nom = nomPais;
        }
        for (int i = 0; i < pais.length; i++) {
            MostrarPais(pais[i]);
        }
        
        
    }

    static void votar(Pais[] pais, int pais_votador) {
        final int PAISOS_A_VOTAR = 10;
        int[]seleccion_paisos = new int[PAISOS_A_VOTAR];
        int[]puntos = new int[PAISOS_A_VOTAR];
        
        for (int i = 0; i < 10; i++){
        pais[seleccion_paisos[i]].vots = puntos[i];
        }
    }

}
