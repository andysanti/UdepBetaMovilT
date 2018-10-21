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

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtPassword;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsuario = findViewById(R.id.idtxtUsuario);
        txtPassword = findViewById(R.id.idtxtPassword);
        btnIngresar = findViewById(R.id.idbtnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abre_dialogo_error(view);
            }
        });
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
