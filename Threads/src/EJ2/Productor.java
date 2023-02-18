package EJ2;


public class Productor implements Runnable {

    private Numero numero;

    public Productor (Numero numero) {
        this.numero = numero;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                // Espera un 1  segundo entre acción
                Thread.sleep(1000);

                synchronized (numero) {
                    // El valor del número cambia a un número aleatorio.    
                    numero.setNumero(numero.generarAleatorio());
                    
                    // Se envia a todos los demás hilos.
                    numero.notifyAll();
                }
                
            } catch (InterruptedException e) {
                    e.printStackTrace();;
            }
        }
    }
}
