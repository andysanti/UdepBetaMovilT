package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Alumno;

public class ValidacionUsuario {
    public static void ingresounoomasLetrasoCaracteresespeciales(String letras) {
        Alumno alumno = new Alumno();

        /*
        if(!(letras.length()==8)){
            throw new RuntimeException("maxima cantidad de caracteres es 8");
        }
        */


        char[] letrasarray = letras.toCharArray();

        for (char letra : letrasarray) {
            Integer numero = Integer.parseInt(letra + "");

        }
    }
        public static void ingresoEspaciosEnBlanco(String espacios){

            char[] letrasarray = espacios.toCharArray();
            for (char letra : letrasarray) {
                Integer numero = Integer.parseInt(letra + "");

            }
        }

    public static int cuandoingresanumeros(String numeros){
        int numero = 1;
        char[] letrasarray = numeros.toCharArray();
        for (char letra : letrasarray) {
                try {
                    Integer numero1 = Integer.parseInt(letra + "");
                }catch(NumberFormatException EX){
                    numero=2;
                }
        }
        return numero;

    }

    public static int cuandoingresaLongitudDiferentedeNueveretornaCero(String numeros){


        if(numeros.length()==9)
            return 0;
        else return 1;

    }


}
