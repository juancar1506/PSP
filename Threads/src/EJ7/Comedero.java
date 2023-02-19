package EJ7;

public class Comedero {

    private boolean lleno;
    private boolean enUso;

    public Comedero() {
        this.lleno = true;
        this.enUso = false;
    }

    public  boolean acceder (String nombre) {
        
       // Accede el cuidador 
       if (nombre.equalsIgnoreCase("cuidador")) {
            return !estaEnUso();
       } else {
           // Aceden los animales
            return !estaEnUso() && estaLleno();
       }

    }
    
    // Se llena el comedero.
    public synchronized void llenar () {
        lleno = true;
        this.notifyAll();
    }

    public  boolean estaLleno () {
        return lleno;
    }

    public  boolean estaEnUso () {
        return enUso;
    }
    
    // Se va del comedero
    public synchronized void salir () {
        enUso = false;
        notifyAll();
    }

    // Entra el animal y vacia el comedero.
    public synchronized void entrar () {
        enUso = true;
        lleno = false;
        this.notifyAll();
    }
}
