package EJ8;

import java.util.Random;

public class Doctor implements Runnable {

    private Hospital hospital;
    private Random random;
    private String nombre;

    public Doctor(Hospital hospital, String nombre) {
        this.hospital = hospital;
        this.nombre = nombre;
        this.random = new Random();
    }

    public void run() {
        while (true) {
            // Atiende el paciente
            String paciente = hospital.atenderPaciente();
            System.out.println(nombre + " está atendiendo a " + paciente);
            try {
                // Tiempo que tarda en atender al paciente.
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
   
}
