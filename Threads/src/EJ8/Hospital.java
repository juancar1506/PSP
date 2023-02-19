package EJ8;

import java.util.ArrayList;

public class Hospital {

    private static final String[] NOMBRES = {"Juan", "Maria", "Pedro", "Luisa", "Ana", "Jose", "Sofia", "Diego", "Carla", "Mario"};
    private ArrayList<String> pacientes;

    public Hospital() {
        this.pacientes = new ArrayList<String>();
    }

    // Se agrega un pacinete al listado
    public synchronized void agregarPaciente(String paciente) {
        pacientes.add(paciente);
        System.out.println("Paciente " + paciente + " ha llegado al hospital y está esperando en la sala de espera.");
        notifyAll();
    }

    public synchronized String atenderPaciente() {
        // Minimo tiene que haber al menos 2 paciente
        while (pacientes.size() < 2) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        // Borra el paciente de la lista.
        String paciente = getNumPacientes();
        System.out.println("El paciente " + paciente + " está siendo atendido por el doctor.");
        notifyAll();
        return paciente;
    }
    
    // Recupera el paciente actual.
    public synchronized String getNumPacientes() {
        if (pacientes.size() > 0) {
            return pacientes.remove(0);
        } else {
            return null;
        }
    }
    
    // Genera el nombre de un paciente aleatorio
    public static String generarNombrePacienteAleatorio() {
        int indice = (int) (Math.random() * NOMBRES.length);
        return NOMBRES[indice];
    }
}
