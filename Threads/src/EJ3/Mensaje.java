package EJ3;

import java.util.Scanner;

public class Mensaje {
    String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje() {
        // Se pide al usuario que escriba un mensaje cada vez que se quiera cambiar el valor
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el mensaje que quieras enviar: ");
        String mensaje = sc.nextLine();
        
        // Se cambia el valor del mensaje
        this.mensaje = mensaje;
    }
    
}
