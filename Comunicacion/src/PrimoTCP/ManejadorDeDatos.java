package PrimoTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejadorDeDatos implements Runnable {
    private Socket clientSocket;

    public ManejadorDeDatos(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            // Se declará el bufferReader y mi descriptor
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String numeroLeido;
            while ((numeroLeido = in.readLine()) != null) {
                int numero = Integer.parseInt(numeroLeido);
                String esPrimo = esPrimo(numero);
                out.println("El número "+numero+". "+ esPrimo + " es primo");
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error al manejar la conexión con el cliente " + clientSocket.getInetAddress());
        }
    }

    private String esPrimo(int numero) {
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

