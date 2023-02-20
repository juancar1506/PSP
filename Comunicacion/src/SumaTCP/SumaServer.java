package SumaTCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SumaServer {
    public static void main(String[] args) {
        int port = 12345;

        try {
            // Creamos el socket del servidor
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor iniciado en el puerto " + port);

            // Aceptamos conexiones de clientes
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress().getHostAddress());

                // Creamos un nuevo hilo para el cliente y lo iniciamos
                GestorClientes client = new GestorClientes(clientSocket);
                Thread thread = new Thread(client);
                thread.start();
            }
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al iniciar el servidor: " + e.getMessage());
        }
    }
} 