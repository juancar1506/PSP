package EJ8;

public class SalaEspera {
    public static void main(String[] args) {
        Hospital hospital = new Hospital();

        // Creamos enfermero
        Thread enfermero = new Thread(new Enfermero(hospital));
        enfermero.start();

        // Creamos los doctores
        int numDoctores = 3;
        Thread[] doctores = new Thread[numDoctores];
        for (int i = 0; i < numDoctores; i++) {
            doctores[i] = new Thread(new Doctor(hospital, "doctor "+ (i+1)));
            doctores[i].start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Fin
        enfermero.interrupt();
        for (Thread doctor : doctores) {
            doctor.interrupt();
        }
    }
}
       

