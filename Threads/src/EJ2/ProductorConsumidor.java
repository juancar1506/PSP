package EJ2;

public class ProductorConsumidor {
    public static void main(String[] args) {
        Numero numero = new Numero();
        Consumidor c1 = new Consumidor("Juan", numero);
        
        new Thread(c1).start();

        Productor p = new Productor(numero);
        new Thread(p).start();

        System.out.println("Comienza...");
    }
}
