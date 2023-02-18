package EJ4;

public class ControlAcceso {
    private static final int RECURSOS = 10;
    public static void main(String[] args) {
        // Se crea la impresora que se quiere imprimir
        Impresora
         impresora = new Impresora();


        // Se crean los recursos que estan esperando a la impresora
        Thread[] recursos = new Thread[10];

        for (int i = 0; i <RECURSOS; i++) {
            recursos[i] = new Thread(new Recurso("recurso "+(i+1), impresora));
            recursos[i].start();
        }
        // Se espera a la finalización de todos los hilos.
        for (int i = 0; i < RECURSOS; i++) {
            try {
                recursos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Impresión finalizada");
    }
}
