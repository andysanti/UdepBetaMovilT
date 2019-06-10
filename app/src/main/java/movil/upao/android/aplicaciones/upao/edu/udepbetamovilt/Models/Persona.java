package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Persona {
    private int id;
    private String DNI;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String email;
    private String genero;

    public Persona() {
    }

    public boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    public Persona(int id, String DNI, String nombre, String apellidos) {
        this.id = id;
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Persona(int id, String DNI, String nombre, String apellidos, String direccion, String telefono, String email, String genero) {
        this.id = id;
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        //this.DNI = DNI;
        if(DNI.length() == 8){
            if(isNumeric(DNI)==true)
                this.DNI = DNI;
            else
                this.DNI = "N/A";
        }
        else
            this.DNI = "N/A";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // El email a validar
        // String email = "info@programacionextrema.com";

        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(email);

        if (mather.find() == true) {
            this.email = email;
        } else {
            this.email = "N/A";
        }
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombres(){
        return this.nombre+ " " + this.apellidos;
    }
}
