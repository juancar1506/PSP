package EJ8;

public class SalaEspera {
    public static void main(String[] args) {
        Hospital hospital = new Hospital();

        // Creamos la enfermera y la ponemos en marcha
        Thread enfermera = new Thread(new Enfermero(hospital));
        enfermera.start();

        // Creamos los doctores y los ponemos en marcha
        int numDoctores = 3;
        Thread[] doctores = new Thread[numDoctores];
        for (int i = 0; i < numDoctores; i++) {
            doctores[i] = new Thread(new Doctor(hospital, "doctor "+ (i+1)));
            doctores[i].start();
        }

        // Esperamos un tiempo para que los threads hagan su trabajo
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Terminamos la simulación
        enfermera.interrupt();
        for (Thread doctor : doctores) {
            doctor.interrupt();
        }
    }
}
       

