package psp.servicios.SMTP;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;



public class CuadradoPorCorreo {

    private static final String SERVER = "smtp.educa.madrid.org";
    private static final int PORT = 587;
    private static final String SUCCESS = "Correo electrónico enviado exitosamente.";
    private static final String SUBJECT = "EJERCICIO PSP RANDOM";   

    public static void main(String[] args) {
        int alto = Integer.parseInt("100");
        int ancho = Integer.parseInt("100");
        String correo = "jctro2002@gmail.com";

        String cuadrado = "";

        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                cuadrado += "*";
            }
            cuadrado += "\n";
        }

        enviarEmail(cuadrado, correo);
    }

    private static void enviarEmail (String cuadrado, String correo) {
        // Leemos el usuario y la contraseña de un archivo oculto.
        File archivo = new File("/home/juancar1506/PSP/servicios/src/main/java/psp/servicios/SMTP/passw.txt");
        String user = "";
        String pass = "";
        String mailfrom = "";

        try {
            Scanner lector = new Scanner(archivo);
            user = lector.nextLine();
            pass = lector.nextLine();
            mailfrom = lector.nextLine();
            lector.close();
        } catch (FileNotFoundException  e) {
            e.printStackTrace();
        }

        Email email = new SimpleEmail();

        // Host y puerto
        email.setHostName(SERVER);
        email.setSmtpPort(PORT);

        // Credenciales, pedirlas de un fichero oculto.
        email.setAuthentication(user, pass);
        email.setSSLOnConnect(true);

        try {
            email.setFrom(mailfrom);
            email.setSubject(SUBJECT);
            email.setMsg(cuadrado);
            email.addTo(correo);
            email.send();
            System.out.println(SUCCESS);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

}
