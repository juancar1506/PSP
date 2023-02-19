package EJ6;

public class Comedero {

    private boolean enUso;
    
    public synchronized void comiendo (String nombre) {
        while (enUso) {
            System.out.println(nombre + " esperando a comer");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        enUso = true;
        System.out.println(nombre + " esta comiendo");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        enUso = false;
        System.out.println(nombre  + " ha terminado de comer");
        this.notifyAll();
    }
}
