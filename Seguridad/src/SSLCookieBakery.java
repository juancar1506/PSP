import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.net.ssl.*;

public class SSLCookieBakery {
    private final static String URL = "aulavirtual33.educa.madrid.org";
    private final static String USER = "miguel.segoviafreeman";
    private final static String PASSWORD = "";
    private static String nombreCookie = "";
    private static HashMap<String, String> cookieHash;

    public static void main(String[] args) {
        // establezco la conexión SSL al servidor
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try {
            // metodo que hará el post para conseguir la cookie
            socketPost(factory);
            // metodo que hará el get para conseguir el listado de cursos
            socketGet(factory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void socketGet(SSLSocketFactory factory) throws UnknownHostException, IOException {
        System.out.println("NOMBRE COOKIE: " + nombreCookie + " COOKIE: " + cookieHash.get(nombreCookie));
        Socket socket = factory.createSocket(URL, 443);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader getIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.print("GET /ies.juandelacierva.madrid/my/ HTTP/1.1\r\n");
        out.print("Host: " + URL + "\r\n");
        out.print("Cookie: " + nombreCookie + "=" + cookieHash.get(nombreCookie) + "\r\n");
        out.print("\r\n");
        out.flush();
        String line;
        while ((line = getIn.readLine()) != null) {
            System.out.println(line);
        }
        //codigo para extraer cursos en el caso de que funcionase ...
    }

    private static void socketPost(SSLSocketFactory factory) throws IOException, UnknownHostException {
        Socket socket = factory.createSocket(URL, 443);
        extraerCookie(socket);
    }

    private static void extraerCookie(Socket socket) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        String postBody = "username=" + USER + "&password=" + PASSWORD;
        out.print("POST /ies.juandelacierva.madrid/login/index.php HTTP/1.1\r\n");
        out.print("Host: " + URL + "\r\n");
        out.print("Content-Type: application/x-www-form-urlencoded\r\n");
        out.print("Content-Length: " + postBody.length() + "\r\n");
        out.print("Connection: close\r\n");
        out.print("\r\n");
        out.print(postBody);
        out.flush();

        String cookie = "";
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            if (line.startsWith("Set-Cookie: Moodle")) {
                cookie = line.split(";")[0].split("=")[1];
                nombreCookie = line.split(": ")[1].split("=")[0];
            }
        }
        cookieHash = new HashMap<>();
        cookieHash.put(nombreCookie, cookie);
        System.out.println("ESTA ES: " + cookie);
        in.close();
        out.close();
        socket.close();
    }
}
