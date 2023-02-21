package psp.servicios.HTTP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DAMHttpServer {
    public static void main(String[] args) {
        int puerto = 1234;
        String raizServer = "/home/juancar1506/PSP/servicios/src/main/java/psp/servicios/HTTP";

        try (ServerSocket server = new ServerSocket(puerto)) {
            // El sevidor escucha
            System.out.println("Running server on "+puerto+"...");
            
            while (true) {
                // Llega un cliente...
                Socket cliente = server.accept();
                // Gestionar Clientes
                new Thread(new GestionarClientes(cliente, raizServer)).start();;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
