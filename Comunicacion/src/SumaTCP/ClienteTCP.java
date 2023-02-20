package SumaTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteTCP {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Cambiar por la dirección IP del servidor si es necesario
        int port = 12345;

        try {
            // Creamos el socket del cliente
            Socket clientSocket = new Socket(serverAddress, port);
            System.out.println("Conectado al servidor en " + serverAddress + ":" + port);

            // Obtenemos los streams de entrada y salida del socket
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Leemos los datos del usuario y los enviamos al servidor
            BufferedReader clienteIn = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while ((message = clienteIn.readLine()) != null) {
                out.println(message);

                // Leemos la respuesta del servidor
                String response = in.readLine();
                System.out.println("Respuesta del servidor: " + response);
            }

            // Cerramos los streams y el socket
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al conectarse al servidor: " + e.getMessage());
        }
    }
}
