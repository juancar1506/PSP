package SumaUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CalculadoraClienteUDP {
    public static void main(String[] args) throws IOException {
        int portServer = 1234;
        String salir = "exit";
        int numeros = 3;
        byte[] bufferRecibido = new byte[1024];
        try (DatagramSocket clienSocket = new DatagramSocket()) {
            InetAddress host = InetAddress.getByName("localhost");
            Scanner in = new Scanner(System.in);
            String envia = "";
           
            do {
                // Lista de paquetes que se envian, elservidor espera 3.
                DatagramPacket[] paquetes =  new DatagramPacket[numeros];
                for (int i = 0; i < numeros; i++) {
                    if(i != 1){
                        System.out.println("Escribe un número");
                    } else {
                        System.out.println("Escribe la operación");
                    }
                    // PEdimos por consola
                    envia = in.nextLine();
                    byte[] sendData = envia.getBytes(); 
                    paquetes[i] = new DatagramPacket(sendData, sendData.length, host, portServer);
                    clienSocket.send(paquetes[i]);
                }

                // Se recibe el paquete 
                DatagramPacket receiveData = new DatagramPacket(bufferRecibido, bufferRecibido.length);
                clienSocket.receive(receiveData);
                String response = new String(receiveData.getData(), 0 ,receiveData.getLength());
                // Se muestra
                System.out.println(response);
                
            } while (envia.equalsIgnoreCase(salir));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
