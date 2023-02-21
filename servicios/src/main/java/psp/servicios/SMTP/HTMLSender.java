package psp.servicios.SMTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class HTMLSender {

    private static final String SERVER = "smtp.educa.madrid.org"; //smtp.gmail.com
    private static final int PORT = 587; //465
    private static final String MAILFROM = "miguelsegoviafreeman@gmail.com";
    private static final String MAILTO = "zangirugaru@gmail.com"; 
    private static final String SUCCESS = "Correo electrónico enviado exitosamente.";
    private static final String SUBJECT = "HTML DEL EJERCICIO XD";
    private static final String URL = "www.serebii.net";

    public static void main(String[] args) {
        String mail = extraerHTML();
        System.out.println(mail);
        sendHTML(args, mail);
    }

    private static String extraerHTML() {
        String textoH2 = "";
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try (Socket socket = factory.createSocket(URL, 443)) {

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder response = new StringBuilder();

            out.print("GET / HTTP/1.1\r\n");
            out.print("Host: " + URL + "\r\n");
            out.print("\r\n");
            out.flush();

            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            textoH2 = filtrado(response);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textoH2;
    }

    private static String filtrado(StringBuilder response) {
        String textoH2;
        String html = response.toString();

        StringBuilder sb = new StringBuilder();
        String[] partes = html.split("<h2[^>]*>");
        for (int i = 1; i < partes.length; i++) {
            String[] partes2 = partes[i].split("</h2>");
            sb.append(partes2[0]).append("\n");
        }
        textoH2 = sb.toString();
        return textoH2;
    }

    private static void sendHTML(String[] args, String mail) {
        String user = args[0];
        String pass = args[1];

        Email email = new SimpleEmail();

        email.setHostName(SERVER);
        email.setSmtpPort(PORT);

        email.setAuthentication(user, pass);
        email.setSSLOnConnect(true);
        try {
            email.setFrom(MAILFROM);
            email.setSubject(SUBJECT);
            email.setMsg(mail);
            email.addTo(MAILTO);
            email.send();
            System.out.println(SUCCESS);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}