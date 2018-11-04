package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models;

/**
 * Created by Victor Ramirez Pc on 10/29/18.
 */

public class Matricula {

    private int id;
    private int id_curso;
    private int id_alumno;
    private String fecha;
    private String semestre;
    private int nota;

    public Matricula() {
    }

    public Matricula(int id, int id_curso, int id_alumno, String fecha, String semestre) {
        this.id = id;
        this.id_curso = id_curso;
        this.id_alumno = id_alumno;
        this.fecha = fecha;
        this.semestre = semestre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
