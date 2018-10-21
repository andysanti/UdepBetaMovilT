package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.utils.UdepSharedPreferences;

public class MainActivity extends AppCompatActivity {
    private UdepSharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = new UdepSharedPreferences(this); // creo mis preferencias
    }

    @Override
    protected void onResume() {
        super.onResume();
        String usuario = prefs.getString(UdepSharedPreferences.PREF_USUARIO, null);
        if(usuario == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

}
