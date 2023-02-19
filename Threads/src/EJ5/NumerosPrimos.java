package EJ5;

public class NumerosPrimos {
    
    private static final int MAX_NUMEROS = 1000;
    private static final int HILOS = 4;
    public static void main(String[] args) throws InterruptedException {

        // Cantidad de primos
        int[] numeros = new int[MAX_NUMEROS];

        // Se calcula el la diferencia entre el inicial y el final
        int cantidadSecuenciaParalelas = MAX_NUMEROS / HILOS; 
        Thread[] hilos = new Thread[HILOS];
        
        for (int i = 0; i < HILOS; i++) {
            System.out.println("Hilo: "+ (i+1) + " comienza");
            int inicio = i * cantidadSecuenciaParalelas + 1;
            int finalSum = inicio + cantidadSecuenciaParalelas - 1;
            hilos[i] = new Thread(new Primos(inicio, finalSum, numeros));
            hilos[i].start();
            hilos[i].join();
            System.out.println("\nHilo: "+ (i+1) + " termina");
        }

    }
}