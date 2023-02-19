package PrimoTCP;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

public class PrimoServer {
    
    public static void main(String[] args) throws IOException {
      
        // Lo defino aqui para que sea más facil para comprobar
        int port = 1234;

        /**
         * Por parametro seria : 
         *  if (args.length != 1) {
         *    System.out.println("Uso: java TCPServer [port]");
         *     System.exit(1);
         *  }
         */

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor iniciado en el puerto " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                Thread thread = new Thread(new ManejadorDeDatos(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor en el puerto " + port);
            System.exit(1);
        }
    }
}

