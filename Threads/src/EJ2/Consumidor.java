package EJ2;

public class Consumidor implements Runnable {

    private String nombre;
    private Numero numero;
    
    
    public Consumidor(String nombre, Numero numero) {
        this.nombre = nombre;
        this.numero = numero;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (numero) {
                try {
                    // El consumidor esta esperando el número que tiene que producir el productor
                    System.out.println(nombre + " esperando numero..");
                    // Espera...
                    numero.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(nombre + " ha recibido el número: "+ numero.getNumero());
            }
        }
    }
    
    
}
