package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models;

public class Profesor extends Persona {
    private String especialidad;

    public Profesor() {
        super();
    }

    public Profesor(int id, String DNI, String nombre, String apellidos, String especialidad) {
        super(id, DNI, nombre, apellidos);
        this.especialidad = especialidad;
    }

    public Profesor(int id, String DNI, String nombre, String apellidos, String direccion, String telefono, String email, String genero, String especialidad) {
        super(id, DNI, nombre, apellidos, direccion, telefono, email, genero);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return (getNombre() + " " + getApellidos());
    }
}
