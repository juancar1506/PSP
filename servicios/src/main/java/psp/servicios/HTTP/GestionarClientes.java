package psp.servicios.HTTP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GestionarClientes  implements Runnable {

    private Socket socket;
    private String root;

    public GestionarClientes(Socket socket, String root) {
        this.socket = socket;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            File file = procesarPeticion();
            System.out.println("File: " + file);
            devolverRespuesta(file);
            socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }   
    
    private File procesarPeticion() throws IOException {
        // Se crea un bufferReader para leer la peticion recibida. 
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String LineaPedida = in.readLine();
        System.out.println("Petición HTTP: " + LineaPedida);
        // Se separan los espacios en blanco o las tabulaciones
        String[] tokesns = LineaPedida.split("\\s");
        System.out.println(tokesns[1]);
        String filePath = tokesns[1];
        System.out.println(root + filePath);

        return new File(root,filePath);
    }

    private void devolverRespuesta (File file) throws FileNotFoundException, IOException {
        String respuesta;
        // Existe fichero
        System.out.println(file);
        if(file.exists()) {
            System.out.println("Existe...");
            // Leemos el html
            String contenido = leerHTML(file);

            // Se construye la respuesta 
            respuesta = "HTTP/1.1 200 OK\r\n"
            + "Content-Type: text/html\r\n"
            + "Content-Length: " + contenido.length() + "\r\n"
            + "\r\n"
            + contenido;
        } else {
            System.out.println("No existe");
            // Si el archivo no existe, devuelve un error 404
            respuesta = "HTTP/1.1 404 Not Found\r\n"
                        + "Content-Type: text/html\r\n"
                        + "Content-Length: 0\r\n"
                        + "\r\n";
        }
        // Enviamos la respuesta
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        out.println(respuesta);
    }

    private String leerHTML(File file) throws FileNotFoundException, IOException {
        
        // Creamos un buffer para leer el fichero
        BufferedReader fileReader = new BufferedReader(new FileReader(file));

        // Construimos un string que guradará el contenido del fichero.
        StringBuilder contenidoBuilder = new StringBuilder();
        String linea;

        // Mientras se lee el fichero
        while ((linea = fileReader.readLine())!= null) {
            contenidoBuilder.append(linea);
            contenidoBuilder.append("\n");
        }

        //Cerrar el fichero
        fileReader.close();
        // Se mdevuelve el contenido
        return contenidoBuilder.toString();
    }
    
}
