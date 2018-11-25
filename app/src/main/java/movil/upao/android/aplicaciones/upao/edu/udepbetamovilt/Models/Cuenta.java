package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models;

/**
 * Created by Victor Ramirez Pc on 10/29/18.
 */

public class Cuenta {
    private int id;
    private String descripcion;
    private String fecha;
    private float monto;
    private boolean mora;

    public Cuenta() {
    }

    public Cuenta(int id, String descripcion, String fecha, float monto) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.monto = monto;
    }

    public Cuenta(int id, String descripcion, String fecha, float monto, boolean mora) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.mora = mora;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public boolean isMora() {
        return mora;
    }

    public void setMora(boolean mora) {
        this.mora = mora;
    }
}
