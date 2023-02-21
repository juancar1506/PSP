import java.io.*;
import java.net.*;

public class CifradoPlus {
    public static void main(String[] args) {
        // Verificar que se han proporcionado los parámetros necesarios
        if (args.length < 4) {
            System.out.println("Uso: java CifradoCesar <dirección> <puerto> <rotación> <mensaje>");
            return;
        }

        String direccion = args[0];
        int puerto = Integer.parseInt(args[1]);
        int rotacion = Integer.parseInt(args[2]);
        String mensaje = args[3];

        // Cifrar el mensaje utilizando el cifrado César
        String mensajeCifrado = cifrarCesar(mensaje, rotacion);

        try {
            // Establecer la conexión con el servidor
            Socket socket = new Socket(direccion, puerto);

            // Enviar el mensaje cifrado al servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(mensajeCifrado);

            // Cerrar la conexión
            socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String cifrarCesar(String mensaje, int rotacion) {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < mensaje.length(); i++) {
            char caracter = mensaje.charAt(i);

            // Cifrar solo los caracteres alfabéticos
            if (Character.isLetter(caracter)) {
                // Determinar la posición del caracter en el alfabeto
                int posicion = caracter - (Character.isUpperCase(caracter) ? 'A' : 'a');

                // Aplicar la rotación y ajustar la posición para que esté dentro del alfabeto
                posicion = (posicion + rotacion + 26) % 26;

                // Convertir la posición de vuelta a un caracter y agregarlo al resultado
                caracter = (char) (posicion + (Character.isUpperCase(caracter) ? 'A' : 'a'));

                // Incremento la clave en 1
                rotacion++;
            }

            resultado.append(caracter);
        }

        return resultado.toString();
    }
}
