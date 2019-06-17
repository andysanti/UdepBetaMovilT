package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt;

import junit.framework.Assert;

import org.junit.Test;

public class UsuarioTest {


    /**
     1
     No se permite el ingreso de letras
     **/
    @Test(expected = RuntimeException.class)
    public final void cuandoIngreseunaomasLetrasoCaracteresEspecialesLanzarExcepcion(){
   //
    final String letraentrenumeros="1234j678";
    final String todasletras="fghjnjku";
    final String caracteresespeciales="#%-+";

    ValidacionUsuario.ingresounoomasLetrasoCaracteresespeciales(caracteresespeciales);

    }

    @Test(expected = RuntimeException.class)
    public final void cuandoingresaespaciosenblancoLanzarExcepcion(){
        final String espacios="12345678 ";
        ValidacionUsuario.ingresoEspaciosEnBlanco(espacios);
    }

    //Solo se permite caracteres numéricos
    @Test
    public final void cuandoingresanumerosretornarUNO(){
        final String numeros="123489";

        Assert.assertEquals(1, ValidacionUsuario.cuandoingresanumeros(numeros));
    }
    //longitud de 9 caracteres
    @Test
    public final void cuandoingresaLongitudDiferentedeNueveretornaCero(){
        final String numeros="123456789";

        Assert.assertEquals(0, ValidacionUsuario.cuandoingresaLongitudDiferentedeNueveretornaCero(numeros));
    }




}
