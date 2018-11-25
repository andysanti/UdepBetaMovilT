package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models;

import java.util.List;

public class Asistencia {

    private int id;
    private int asistencias;
    private int faltas;
    private  String estado;
    private  String semestre ; //201979
    private String photo_url;
    private String curso;
    private List<Detalle_Asistencia> detAsistencias;

    public Asistencia() {
    }

    public Asistencia(int id, int asistencias, int faltas, String estado, String semestre, String photo_url, String curso, List<Detalle_Asistencia> detAsistencias) {
        this.id = id;
        this.asistencias = asistencias;
        this.faltas = faltas;
        this.estado = estado;
        this.semestre = semestre;
        this.photo_url = photo_url;
        this.curso=curso;
        this.detAsistencias=detAsistencias;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public List<Detalle_Asistencia> getDetAsistencias() {
        return detAsistencias;
    }

    public void setDetAsistencias(List<Detalle_Asistencia> detAsistencias) {
        this.detAsistencias = detAsistencias;
    }
}