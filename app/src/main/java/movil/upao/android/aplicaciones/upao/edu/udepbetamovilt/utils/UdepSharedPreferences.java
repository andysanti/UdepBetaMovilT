package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UdepSharedPreferences {
    public static final String SHARED_PREFERENCES_NAME = "UDEP_MOVIL";
    public static final String PREF_USUARIO = "USUARIO";
    public static final String PREF_ID = "PREF_ID";
    public static final String PREF_NOMBRES = "PREF_NOMBRES";

    private SharedPreferences preferences;
    public UdepSharedPreferences(Context ctx) {
        // Construye y/u obtiene un share preferences
        // con nombre "UDEP_MOVIL" en modo privado(solo para este apk)
        preferences = ctx.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void putString(String key, String value){
        SharedPreferences.Editor editor = preferences.edit(); // Pone las preferencias en modo edición
        editor.putString(key, value); // Agrega la preferencia
        editor.apply(); // Aplica cambios
    }
    public String getString(String key, String defaultValue){
        return preferences.getString(key, defaultValue); // Obtiene una preferencia
    }

    public void putInt(String key, int value){
        SharedPreferences.Editor editor = preferences.edit(); // Pone las preferencias en modo edición
        editor.putInt(key, value); // Agrega la preferencia
        editor.apply(); // Aplica cambios
    }
    public int getInt(String key, int defaultValue){
        return preferences.getInt(key, defaultValue); // Obtiene una preferencia
    }
}
