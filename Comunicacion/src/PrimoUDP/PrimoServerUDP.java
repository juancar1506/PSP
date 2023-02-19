package PrimoUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class PrimoServerUDP  {
    public static void main(String[] args) throws IOException {
        // Lo defino aqui para que sea más facil para comprobar
        int port = 1234;

        /**
         * Por parametro seria : 
         *  if (args.length != 1) {
         *    System.out.println("Uso: java UDP [port]");
         *     System.exit(1);
         *  }
         */

            byte[] bufferRecibido = new byte[1024];
        try (DatagramSocket  serverSocket = new DatagramSocket(port)) {
            System.out.println("Servidor iniciado en el puerto " + port);

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(bufferRecibido, bufferRecibido.length);
                serverSocket.receive(receivePacket);

                byte[] data = Arrays.copyOf(receivePacket.getData(), receivePacket.getLength());
                String numeroString = new String(data);
                System.out.println(numeroString);
                int numero = Integer.parseInt(numeroString);

                String esPrimo = esPrimo(numero);
                String response = "El número "+numero+". "+ esPrimo + " es primo";
                byte[] sendResponse = response.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendResponse, sendResponse.length,receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor en el puerto " + port);
            System.exit(1);
        }
    }

    private static String esPrimo(int numero) {
        if (numero <= 1) {
            return "No";
        }

        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return "No";
            }
        }

        return "Si";
    }
}
