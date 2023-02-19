package EJ5;

public class Primos implements Runnable {

    private int inicio;
    private int fin;
    private final int[] primos;
    private int count = 0;

    public Primos(int inicio, int fin, int[] primos) {
        this.inicio = inicio;
        this.fin = fin;
        this.primos = primos;
    }

    @Override
    public void run() {
        // Números hasta el fin
        for (int i = inicio; i <= fin; i++) {
            int contador = 0;
            
            // Comprobar que el número actual sea divisible entre 0
            for (int j = i; j >= 1; j--) {
                if (i % j == 0) {
                    contador++;
                }
            }
            
            if (contador == 2) {
                // se devulve el número primo
                synchronized (primos) {
                    primos[count++] = i;
                }
            }
        }    
        // Se muestran los números 
        for (int i = 0; i < count; i++) {
            System.out.print(primos[i] + " ");
          }
    }
    
}
