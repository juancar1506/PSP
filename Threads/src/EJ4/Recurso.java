package EJ4;


public class Recurso implements Runnable {

    private String nombre;
    private Impresora impresora;

    
    public Recurso(String nombre, Impresora impresora) {
        this.nombre = nombre;
        this.impresora = impresora;
    }

    @Override
    public void run() {
        // Se lanza la impresión
        impresora.imprimir(nombre);
    }
    
}
