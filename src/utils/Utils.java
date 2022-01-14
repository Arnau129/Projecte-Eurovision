/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 * Llibreria d'utilitats
 *
 * @author author
 * @version version
 *
 */
public class Utils {
// <editor-fold defaultstate="collapsed" desc="Implementació de LlegirInt() i de LlegirDouble()">

    private static Scanner scan=null;
       
    public static int LlegirInt() {
        int result;

        if (scan == null)
            scan = new Scanner(System.in);
        result = LlegirInt(scan);

        return result;
    }

    public static int LlegirInt(String missatge) {
        int result;

        if (scan == null)
            scan = new Scanner(System.in);
        result = LlegirInt(scan, missatge);
        
        return result;
    }

    public static int LlegirInt(Scanner scan) {
        return LlegirInt(scan, null);
    }
    
    public static int LlegirInt(Scanner scan, String missatge, int valorMin, int valorMax)
    {
        int result =0;
        do {
            result = LlegirInt(scan, missatge);
        } while (result < valorMin || result > valorMax);
        
        return result;
    }

    public static int LlegirInt(Scanner scan, String missatge) {
        boolean dadesCorrectes;
        int result = 0;
        do {
            if (missatge != null) {
                System.out.print(missatge);
            }
            dadesCorrectes = scan.hasNextInt();
            if (dadesCorrectes) {
                result = scan.nextInt();
            } else if (scan.hasNext()) {
                scan.nextLine();
            }
        } while (!dadesCorrectes);

        return result;
    }
    
    public static double LlegirDouble() {
        double result;

        if (scan == null)
            scan = new Scanner(System.in);
        result = LlegirDouble(scan);

        return result;
    }

    public static double LlegirDouble(String missatge) {
        double result;

        if (scan == null)
            scan = new Scanner(System.in);
        result = LlegirDouble(scan, missatge);
        
        return result;
    }

    public static double LlegirDouble(Scanner scan) {
        return LlegirDouble(scan, null);
    }
    
    public static double LlegirDouble(Scanner scan, String missatge, double valorMin, double valorMax)
    {
        double result = 0;
        do {
            result = LlegirDouble(scan, missatge);
        } while (result < valorMin || result > valorMax);
        
        return result;
    }

    public static double LlegirDouble(Scanner scan, String missatge) {
        boolean dadesCorrectes;
        double result = 0;
        do {
            if (missatge != null) {
                System.out.print(missatge);
            }
            dadesCorrectes = scan.hasNextDouble();
            if (dadesCorrectes) {
                result = scan.nextDouble();
            } else if (scan.hasNext()) {
                scan.nextLine();
            }
        } while (!dadesCorrectes);

        return result;
    }

// </editor-fold>
    
// <editor-fold defaultstate="collapsed" desc="Implementació de Numeros aleatoris">
    public static int Numero_aleatori(int max, int min) {

        int numero_aleatori = (int) (Math.random() * (max - min + 1) + min);
        return numero_aleatori;
    }

    public static void Cadena_sense_repeticio(int vector[], int max, int min) {

        for (int i = 0; i < vector.length; i++) {
            boolean encontrat = false;
            int aleatori = Numero_aleatori(max, min);
            for (int j = 0; j < i; j++) {
                if (vector[j] == aleatori) {
                    encontrat = true;
                }
            }
            if (!encontrat) {
                vector[i] = aleatori;
            } else {
                i--;
            }
        }
    }public static void Cadena_sense_repeticio_amb_numero_prohibit(int vector[], int max, int min, int num_prohibit) {

        for (int i = 0; i < vector.length; i++) {
            boolean encontrat = false;
            int aleatori = Numero_aleatori(max, min);
            for (int j = 0; j < i; j++) {
                if (vector[j] == aleatori || vector[j] == num_prohibit) {
                    encontrat = true;
                }
            }
            if (!encontrat) {
                vector[i] = aleatori;
            } else {
                i--;
            }
        }
    }
// </editor-fold>
}
