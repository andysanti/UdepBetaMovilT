package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;

public class AulaVirtualActivity extends AppCompatActivity  implements View.OnClickListener {

    private CardView crdAsistencias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula_virtual);

        crdAsistencias= findViewById(R.id.crdAsistencia);
        crdAsistencias.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AsistenciaActivity.class);
        startActivity(intent);
    }


}
