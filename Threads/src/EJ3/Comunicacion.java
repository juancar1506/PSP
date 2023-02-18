package EJ3;

public class Comunicacion {

    public static void main(String[] args) {
        // Se crea el mensaje
        Mensaje mensaje = new Mensaje();
    
        // Se crea un receptor
        Receptor receptor = new Receptor("Juan", mensaje);
        new Thread(receptor).start();
        
        // Se crea el emisor del mensaje
        Emisor emisor = new Emisor(mensaje);
        new Thread(emisor).start();    
    }
}
