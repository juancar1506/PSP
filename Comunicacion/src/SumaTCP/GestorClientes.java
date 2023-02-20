package SumaTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GestorClientes implements Runnable {

    private Socket cliente;
    private static final String SALIR = "salir";
    
    public GestorClientes(Socket cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(cliente.getInputStream());
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());) {
                String respuesta = "";
                String num1S = "";
                String operacion = "";
                String num2S = "";
                do {
                    
                    if (in.available() > 0) {
                        respuesta = in.readUTF();
                        // 
                    }
                } while (respuesta.equalsIgnoreCase(SALIR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String calcular (int numero_a, int numero_b, String operacion) {
        int respuesta = 0;
        try { 
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
                    return "No es un numero o no has pasado una ioperación.\nOperaciones disponilbles ['+','-','*','/']";
            }
        } catch (ArithmeticException e) {
            System.out.println("Algun dato recibido era incorrecto");
        }
        return "Error recuerda que este programa no válida divisiones entre 0 o números no enteros";
    }
}
