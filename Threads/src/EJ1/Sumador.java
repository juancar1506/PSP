package EJ1;

public class Sumador implements Runnable {
    
    private int[] numeros;
    private int inicio;
    private int fin;
    private int suma;
    
    /* Get suma para mostrar en el main. */
    public int getSuma() {
        return suma;
    }

    // Constructor
    public Sumador(int inicio, int[] numeros) {
        this.numeros = numeros;
        this.inicio = inicio;
        this.fin = this.inicio + 250;
        this.suma = 0; 
    }


    @Override
    public void run() {
        sumar();
    }

    /* Método que sumará los numeros del array  */ 
    public synchronized void sumar () {
        for (int i = inicio; i < fin; i++) {
            suma += numeros[i];
        }
    }
    
}
