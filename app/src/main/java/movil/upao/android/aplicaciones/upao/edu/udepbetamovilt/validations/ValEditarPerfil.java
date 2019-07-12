package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValEditarPerfil {

    public ValEditarPerfil() { }

    public static boolean validarEmail(String email){
        // El email a validar
        // String email = "info@programacionextrema.com";

        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher matcher = pattern.matcher(email);

        if(matcher.find() == true){
            return true;
        }
        else
            return false;
    }

    public static boolean validarClaveNoSeRepita(String c_antigua, String c_nueva){
        if(!c_antigua.equals(c_nueva)){
            return true;
        }
        else
            return false;
    }
    public static boolean validarTelefono(String telefono){
        // El telefono a validar
        // String telefono = "971733776";

        // Patron para validar el telefono
        Pattern pattern = Pattern.compile("[0-9]{9}$");

        Matcher matcher = pattern.matcher(telefono);

        if(matcher.find() == true)
            return true;
        else
            return false;
    }
}