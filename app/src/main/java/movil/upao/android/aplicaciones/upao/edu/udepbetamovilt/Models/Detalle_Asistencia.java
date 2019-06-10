package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models;

public class Detalle_Asistencia {

    private int id;
    private int idAsistencia;
    private String curso;
    private String sesion;
    private String tipoAsistencia;
    private String fecha;

    public Detalle_Asistencia() {

    }

    public Detalle_Asistencia(int id, int idAsistencia, String curso, String sesion, String tipoAsistencia) {
        this.id = id;
        this.idAsistencia = idAsistencia;
        this.curso = curso;
        this.sesion = sesion;
        this.tipoAsistencia = tipoAsistencia;
    }

    public Detalle_Asistencia(int id, int idAsistencia, String curso, String sesion, String tipoAsistencia, String fecha) {
        this.id = id;
        this.idAsistencia = idAsistencia;
        this.curso = curso;
        this.sesion = sesion;
        this.tipoAsistencia = tipoAsistencia;
        this.setFecha(fecha);
    }

    public String getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(String tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }


    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}