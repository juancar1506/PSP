package EJ2;

import java.util.Random;

public class Numero {

    private int numero;

    public int getNumero() {
        return numero;
    }
    /* Se genera un numero aleatorio entre 1 y 100 */
    int generarAleatorio () {
        Random random = new Random();
        int num = random.nextInt(101);

        return num;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
