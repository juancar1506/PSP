package SumaTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GestorClientes implements Runnable {

    private Socket clientSocket;

    public GestorClientes(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            System.out.println("Conexión establecida desde " + clientSocket.getInetAddress().getHostAddress());

            // Obtenemos los streams de entrada y salida del socket
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            // Leemos los datos del cliente
            String message;
            String operacion = "";
            int num1,num2 = 0;
            while ((message = in.readLine()) != null) {
                
                System.out.println("Mensaje recibido desde " + clientSocket.getInetAddress().getHostAddress() + ": " + message);
                // Primer número
                try {
                    // Se divide en 2 numeros y un operador y se devuelve
                    String[] partes = message.trim().split("\\s*[+\\-*/]\\s*");
                    operacion = message.replaceAll("\\d+\\s*", "");
                    num1 = Integer.parseInt(partes[0]);
                    num2 = Integer.parseInt(partes[1]);
                    String response = pedirOperacion(num1, num2, operacion);
    
                    out.println(response);
                    
                } catch (ArrayIndexOutOfBoundsException e) {
                    out.println("No has escrito una operacion");
                }
            }
                        
            // Cerramos los streams y el socket
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error de entrada/salida al procesar los datos del cliente: " + e.getMessage());
        }
    }

    // OPeraciones calc
    public String pedirOperacion(int num1, int num2, String sumOp) throws IOException {
        String operacion ="";
        switch (sumOp) {
            case "+":
                operacion = ""+(num1 + num2);
                break;
            case "-":
            operacion = ""+(num1 - num2);
                break;
            case "*":
            operacion = ""+(num1 * num2);
                break;
            case "/":
            operacion = ""+(num1 / num2);
                break;
            default:
            operacion = "Error la operación fallo";
                break;
        }
        return operacion;
}
}
