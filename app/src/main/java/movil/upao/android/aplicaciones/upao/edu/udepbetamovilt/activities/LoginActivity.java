package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.utils.UdepSharedPreferences;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtPassword;
    private Button btnIngresar;
    private UdepSharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefs = new UdepSharedPreferences(this);
        txtUsuario = findViewById(R.id.idtxtUsuario);
        txtPassword = findViewById(R.id.idtxtPassword);
        btnIngresar = findViewById(R.id.idbtnIngresar);

        /*btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abre_dialogo_error(view);
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        // si el usuario ya está logueado para que mostrarle el login? lo redirigmos al main activity.
        String usuario = prefs.getString(UdepSharedPreferences.PREF_USUARIO, null);
        if (usuario != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
    public void pantallaAula(View view) {
        if( txtPassword.getText().toString().equals("Sullana") ){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            prefs.putString(UdepSharedPreferences.PREF_USUARIO, txtUsuario.getText().toString());
            this.finish();
        }else{
            abre_dialogo_error(view);
        }

    }

    public void abre_dialogo_error(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atención");
        builder.setMessage("Usuario y/o contraseña son incorrectos. Vuelva a intentarlo.");
        builder.setPositiveButton(android.R.string.ok, null);
        //builder.setNegativeButton(android.R.string.cancel, null);
        Dialog dialog = builder.create();
        dialog.show();
    }
}
