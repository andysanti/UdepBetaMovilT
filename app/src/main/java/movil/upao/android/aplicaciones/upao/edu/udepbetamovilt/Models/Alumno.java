package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models;

/**
 * Created by Victor Ramirez Pc on 11/01/18.
 */

public class Alumno extends Persona {
    private String nro_carne;
    private String clave;
    private String foto;

    public Alumno() {
        super();
    }

    public Alumno(int id, String nro_carne, String clave, String DNI, String nombre, String apellidos) {
        super(id, DNI, nombre, apellidos);
        this.nro_carne = nro_carne;
        this.clave = clave;
        this.foto = foto;
    }

    public Alumno(int id, String nro_carne, String clave, String DNI, String nombre, String apellidos, String direccion, String telefono, String email, String genero) {
        super(id, DNI, nombre, apellidos, direccion, telefono, email, genero);
        this.nro_carne = nro_carne;
        this.clave = clave;
    }

    public Alumno(int id, String nro_carne, String clave, String DNI, String nombre, String apellidos, String direccion, String telefono, String email, String genero, String foto) {
        super(id, DNI, nombre, apellidos, direccion, telefono, email, genero);
        this.nro_carne = nro_carne;
        this.clave = clave;
        this.foto = foto;
    }

    public String getNro_carne() {
        return nro_carne;
    }

    public void setNro_carne(String nro_carne) {
        this.nro_carne = nro_carne;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
