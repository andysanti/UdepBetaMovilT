package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt;

import junit.framework.Assert;
import org.junit.Test;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.validations.ValEditarPerfil;

public class EditarPerfilTest {

    @Test
    public void validarEditarPerfil_mail(){
        String oEmail = "victor.ramirez@udep.pe";

        Assert.assertTrue(ValEditarPerfil.validarEmail(oEmail));
    }

    @Test
    public void validarEditarPerfil_clave(){
        String oClave_antigua = "qyte_12Y";
        String oClave_nueva = "Tr73276_e";

        Assert.assertTrue(ValEditarPerfil.validarClaveNoSeRepita(oClave_antigua, oClave_nueva));
    }

    @Test
    public void validarEditarPerfil_telefono(){
        String oTelefono = "971733776";

        Assert.assertTrue(ValEditarPerfil.validarTelefono(oTelefono));
    }
}
