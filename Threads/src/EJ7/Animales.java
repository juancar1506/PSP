package EJ7;

import java.util.Random;

public class Animales implements Runnable {
    
    private String nombre;
    private Comedero comedero;
    private final Random random = new Random();

    

    public Animales(String nombre, Comedero comedero) {
        this.nombre = nombre;
        this.comedero = comedero;
    }

    void andar() {
        System.out.println(nombre + " esta andando");
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void sonido() {
        System.out.println(nombre + " esta haciendo un sonido");
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            accion();
        }
    }

    void accion() {
        andar();
        sonido();
        try {
            Thread.sleep(random.nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        comer();
    }

    void comer() {
        synchronized (comedero) {
            // El animal qiere comer pero no puede si esta en uso o vacio.
            while (!comedero.acceder(nombre)) {
                System.out.println(nombre + " esperando a comer...");
                try {
                    comedero.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Come.
            System.out.println(nombre + " esta comiendo...");
            comedero.entrar();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Termina de comer.
            System.out.println(nombre + " termina de comer");
            comedero.salir();
        }
    }
}
