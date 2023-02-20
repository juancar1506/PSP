package SumaUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CalculadoraServerUDP {
    public static void main(String[] args) {
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
                    DatagramPacket recibePaquete = new DatagramPacket(bufferRecibido, bufferRecibido.length);
                    while (true) {
                        // El servidor va ha espperar 3 paquetes enviados por un cliente y los va agestionar
                        serverSocket.receive(recibePaquete);

                        // Número 1
                        String numero1_string = new String(recibePaquete.getData()).trim();
                        serverSocket.receive(recibePaquete);

                        String operacion = new String(recibePaquete.getData()).trim();
                        serverSocket.receive(recibePaquete);
                        
                        String numero2_string = new String(recibePaquete.getData()).trim();
                        System.out.println("Se ha recibiido toda la información.");
                        System.out.println(numero1_string + ", "+ operacion +", "+numero2_string);
                        // Realizara las operaciones
                        String respuesta = calcular(
                            Integer.parseInt(numero1_string),
                            Integer.parseInt(numero2_string), 
                            operacion);
                        // Devolvera al cliente
                        byte[] sendResponse = respuesta.getBytes();

                        DatagramPacket sendPacket = 
                            new DatagramPacket(
                                sendResponse, 
                                sendResponse.length, 
                                recibePaquete.getAddress(), 
                                recibePaquete.getPort()
                            );
                        serverSocket.send(sendPacket);
                    }
            
                } catch (IOException e) {
                    System.err.println("Error al iniciar el servidor en el puerto " + port);
                    System.exit(1);
                }
            }
        
    private static String calcular (int numero_a, int numero_b, String operacion) {
        int respuesta = 0; 
        switch (operacion) {
            case "+":
                respuesta = numero_a + numero_b;
                return ""+respuesta;
            case "-":
                respuesta = numero_a - numero_b;
                return ""+respuesta;
            case "*":
                respuesta = numero_a * numero_b;
                return ""+respuesta;
            case "/":
                respuesta = numero_a / numero_b;
            return ""+respuesta;
            default:
                return "No es un numero";
        }
    }
}