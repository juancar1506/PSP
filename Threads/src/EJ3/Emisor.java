package EJ3;

public class Emisor implements Runnable{

    Mensaje mensaje;
    
    public Emisor(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                synchronized (mensaje) {
                    // Se cambia el valor del mensaje.
                    mensaje.setMensaje();

                    // Se indica que el mensaje ha cambiado
                    mensaje.notifyAll();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
