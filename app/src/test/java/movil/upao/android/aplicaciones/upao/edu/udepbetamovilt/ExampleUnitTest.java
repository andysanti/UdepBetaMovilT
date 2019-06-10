package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt;

import org.junit.Test;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Curso;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Persona;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void validarDni(){
        Persona p = new Persona();
        p.setNombre("Victor");
        p.setApellidos("Ramirez");
        //
        //Exito
        p.setDNI("00016774");
        //Error
        p.setDNI("00016774A");

        assertEquals("N/A", p.getDNI());
    }

    @Test
    public void validarEmail(){
        Persona p = new Persona();
        p.setNombre("Victor");
        p.setApellidos("Ramirez");
        //
        //Exito
        p.setEmail("alumno1@upao.com");
        //Error
        p.setEmail("alumnoupaocom");

        assertEquals("N/A", p.getDNI());
    }

    @Test
    public void validar_Curso_asignado_con_un_profesor(){
        Curso c = new Curso(-1, "ICSI - 438", "ETICA Y DEONTOLOGIA ", "10",4, "Lic. Juan Perez", "");
        String prof = c.getProfesor();
        boolean value;
        if(prof == "")
            value = false;
        else
            value = true;

        assertEquals(true, value);
    }
}