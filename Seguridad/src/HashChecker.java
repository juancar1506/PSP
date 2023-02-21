import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class HashChecker {
    public static void main(String[] args) {
        HashMap<String, String> hash = new HashMap<>();
        // hash generado por el programa, es diferente por el tema de bits y mierdas
        // raras
        hash.put("mondongo", "9fe75de7500e7073d749469bb3a46cc2");
        hash.put("cum", "de7b32b8b9ef96538e4b27fa25ca4bae");

        for (String clave : hash.keySet()) {
            String valor = hash.get(clave);
            String hashCalculado = calcularMD5(clave);

            if (hashCalculado.equals(valor)) {
                System.out.println("El hash de " + clave + " ha sido bien calculado.");
            } else {
                System.out.println("ERROR: El hash de " + clave + " no ha sido bien calculado.");
            }
        }
    }

    public static String calcularMD5(String clave) {
        // indica la que la entrada va a ser por el flujo estandar
        ProcessBuilder pb = new ProcessBuilder("md5sum"); // sin guion puede funcionar "-"
        // ProcessBuilder pb = new ProcessBuilder("sh", "-c", "echo " + clave + " |
        // md5sum");

        // redirijo la salida, redirect error en el caso de que falle
        // md5sum espera el input del usuario por eso hay que redirigirlo
        // conecta la entrada estándar del proceso a una tubería para que outputstream
        // pueda enviar al proceso.
        // gracias jorge por explicarlo en clase
        //pb.redirectInput(ProcessBuilder.Redirect.PIPE);
        //pb.redirectError(ProcessBuilder.Redirect.PIPE);

        try {
            Process proceso = pb.start();
            // mando al proceso la clave, linea churro
            proceso.getOutputStream().write(clave.getBytes());
            proceso.getOutputStream().close();

            // leo lo que me devuelve
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()));
            String result = reader.readLine();
            if (result == null) {
                return null;
            }
            // devuelve una cadena rara que tiene espacio asi que uso split
            System.out.println(result);
            return result.split(" ")[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
