package SumaTCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SumaServer {
    public static void main(String[] args) {
        int puerto = 1234;
        try (ServerSocket server = new ServerSocket(puerto)){
            System.out.println("Server iniciado en puerto: "+ puerto);
            while (true) {
                // Esperamos la conexion
                Socket socket = server.accept();
                // Se gestiona al cliente que hemos recibido                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
} 