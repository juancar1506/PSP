package EJ7;

public class Granja {

    private static final int MAX_ANIMALES = 4;
    public static void main(String[] args) {
        
        // Comedero uno a la vez
        Comedero comedero = new Comedero();
        
         // Se inica el cuidador
         Thread cuidador = new Thread(new Cuidador(comedero));
         cuidador.start();

        // Animales
        Thread[] animales = new Thread[MAX_ANIMALES];
        
        for (int i = 0; i < MAX_ANIMALES; i++) {
            animales[i] = new Thread(new Animales("animal "+ (i+1), comedero));
            animales[i].start();
        }
       

    }
}
