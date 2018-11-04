package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models;

/**
 * Created by Victor Ramirez Pc on 10/27/18.
 */

public class Notificacion {
    public int id;
    public String titulo;
    public String descripcion;
    public String fecha;
    public String foto_url;
    public String tipo;

    public Notificacion() {
        this.tipo = "avisos";
    }

    public Notificacion(int id, String titulo, String descripcion, String fecha, String foto_url, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.foto_url = foto_url;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
