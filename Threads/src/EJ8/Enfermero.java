package EJ8;

import java.util.Random;

public class Enfermero implements Runnable {
    private Hospital hospital;
    private Random random;

    public Enfermero(Hospital hospital) {
        this.hospital = hospital;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Espera un tiempo aleatorio antes de generar un paciente
                Thread.sleep(random.nextInt(5000) + 1000); 
            } catch (InterruptedException e) {
            }

            // Genera un nombre aleatorio para el paciente
            String nombre = hospital.generarNombrePacienteAleatorio(); 
            // Se agrega el paciente...
            hospital.agregarPaciente(nombre);
            System.out.println("Enfermera agrega paciente: " + nombre);
        }
    }
}
