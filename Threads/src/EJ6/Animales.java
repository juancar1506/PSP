package EJ6;

import java.util.Random;

public class Animales implements Runnable {
    
    private String nombre;
    private Comedero comedero;
    private final Random random = new Random();

    

    public Animales(String nombre, Comedero comedero) {
        this.nombre = nombre;
        this.comedero = comedero;
    }

    void andar () {
        System.out.println(nombre + " esta andando");
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void sonido () {
        System.out.println(nombre + " esta haciendo un sonido");
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            accion();
        }
        
    }

    void accion () {
        int intento = random.nextInt(3);

        switch (intento) {
            case 0:
                andar();
                break;
            case 1:
                sonido();
                break;
            case 2:
                System.out.println(nombre + " quiere comer...");
                comedero.comiendo(nombre);
                break;
        }
    }

}
