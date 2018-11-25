package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Detalle_Asistencia;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;

public class DetalleAsistenciaActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference detalleRef;
    private ListView lstDetalleAsistencias;
    private ListAdapter adapter;
    private FirebaseListOptions<Detalle_Asistencia> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_asistencia);

        Intent intent = getIntent();
        Integer idAsistencia = intent.getIntExtra("id", 0);

        database = FirebaseDatabase.getInstance();
        detalleRef = database.getReference("asistencias/"+idAsistencia+"/detalle_asistencia").getRef();

        Query query = detalleRef;

        options =
                new FirebaseListOptions.Builder<Detalle_Asistencia>()
                        .setQuery(query, Detalle_Asistencia.class)
                        .setLayout(R.layout.grid_detalle_asistencia_item)
                        .setLifecycleOwner(this)
                        .build();

        adapter = new FirebaseListAdapter<Detalle_Asistencia>(options) {
            @Override
            protected void populateView(View v, Detalle_Asistencia s, int i) {

                TextView sesion         =  v.findViewById(R.id.lblSesion);
                TextView tipoAsiste    =  v.findViewById(R.id.lblTipoAsistencia);

                sesion.setText(s.getSesion());
                tipoAsiste.setText(s.getTipoAsistencia());
                notifyDataSetChanged();
            }
        };
        lstDetalleAsistencias = findViewById(R.id.lstDetalleAsistencia);
        lstDetalleAsistencias.setAdapter(adapter);
    }
}