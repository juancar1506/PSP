import java.io.*;
import java.net.*;

public class DescifradoCesar {
    
    public static void main(String[] args) {
        // Verificar que se han proporcionado los parámetros necesarios
        if (args.length < 2) {
            System.out.println("Uso: java DescifradoCesar <puerto> <clave>");
            return;
        }

        int puerto = Integer.parseInt(args[0]);
        int clave = Integer.parseInt(args[1]);

        try {
            // Escuchar en el puerto especificado
            ServerSocket serverSocket = new ServerSocket(puerto);

            // Esperar a que llegue una conexión
            Socket socket = serverSocket.accept();

            // Leer el mensaje cifrado del otro programa
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensajeCifrado = in.readLine();

            // Descifrar el mensaje cifrado utilizando la clave
            String mensajeDescifrado = descifrarCesar(mensajeCifrado, clave);

            // Imprimir el mensaje descifrado en la consola
            System.out.println("Mensaje descifrado: " + mensajeDescifrado);

            // Cerrar la conexión
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String descifrarCesar(String mensaje, int clave) {
        // Para descifrar el mensaje cifrado, simplemente se aplica la rotación inversa
        return cifrarCesar(mensaje, -clave);
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
            }

            resultado.append(caracter);
        }

        return resultado.toString();
    }
}
