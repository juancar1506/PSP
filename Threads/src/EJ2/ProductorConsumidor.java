package EJ2;

public class ProductorConsumidor {
    public static void main(String[] args) {
        // se crea el número
        Numero numero = new Numero();

        // Se cre el consumidor
        Consumidor c1 = new Consumidor("Juan", numero);
        
        new Thread(c1).start();

        // Se crea el productor 
        Productor p = new Productor(numero);
        new Thread(p).start();

        System.out.println("Comienza...");
    }
}
