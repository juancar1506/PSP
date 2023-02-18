package EJ4;

import java.util.Random;

public class Impresora {

    private static final int AVANCE = 5000;

    private boolean enUso; 

    public synchronized void imprimir (String recurso) {
        // Se comprueba que se pueda imprimir
        while (enUso) {
            try {
                System.out.println(recurso + " esperando a la impresora...");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Se indica el recurso en impresión
        enUso = true;
        System.out.println("La impresora esta imprimiendo el recurso: "+ recurso);

        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(AVANCE));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Fin de la impresión
        enUso = false;
        System.out.println(recurso + " ha terminado de imprimirse.");
        this.notifyAll();
    }
}
