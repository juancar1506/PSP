package EJ3;

public class Receptor implements Runnable {

    private String nombre;
    private Mensaje mensaje;

    public Receptor(String nombre, Mensaje mensaje) {
        this.nombre = nombre;
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (mensaje) {
                try {
                    // El receptor espera el mensaje...
                    mensaje.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(nombre +" ha recibido: '"+ mensaje.getMensaje()+"'");
            }
        }
        
    }
    
}
