package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models;

/**
 * Created by Victor Ramirez Pc on 11/01/18.
 */

public class Curso {
    private int id;
    private String codigo;
    private String nombre;
    private String ciclo;
    private int creditos;
    private String profesor;
    private String observaciones;
    private int nota;

    public Curso() {
    }

    public Curso(int id, String codigo, String nombre, String ciclo, int creditos, String profesor, String observaciones) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciclo = ciclo;
        this.creditos = creditos;
        this.profesor = profesor;
        this.observaciones = observaciones;
        this.nota = 0;
    }

    public Curso(int id, String codigo, String nombre, String ciclo, int creditos, String profesor, String observaciones, int nota) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciclo = ciclo;
        this.creditos = creditos;
        this.profesor = profesor;
        this.observaciones = observaciones;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
