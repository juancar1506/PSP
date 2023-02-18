
package EJ1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Suma de números en paralelo, generar un array de 1000 números aleatorios y los sume en paralelo con 4 threads
 */
public class Numeros {
    
    public static void main(String[] args) {
        int[] numeros = generar1000Numeros();

        Thread[] hilos = new Thread[4];
        int sumaTotal = 0;

        for (int i = 0; i < 4; i++) {
            try {
                Sumador tarea = new Sumador(i * 250, numeros);
                hilos[i] = new Thread(tarea);
                hilos[i].start();
                hilos[i].join();
                System.out.println("Hilo: "+ (i+1) + " - Total: " + tarea.getSuma());
                sumaTotal += tarea.getSuma();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Total de números: " + sumaTotal);
    }
    /*
     * Generador de 1000 números aleatorios entre 1 y 100 
     * devuelve un array de 1000 números
     */
    static int[] generar1000Numeros() {
        int[] numeros = new int[1000];
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            numeros[i] = random.nextInt(101);
        }

        return numeros;
    }
}
