package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Cuenta;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Notificacion;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;

public class EstadoCuentaActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference EstadoCuentaRef;
    private ListAdapter adapter;
    private FirebaseListOptions<Cuenta> options;
    private ListView lstCuentas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_cuenta);

        //configurar el botón Back (arriba)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance();
        EstadoCuentaRef = database.getReference("estadocuenta");
        /*
        EstadoCuentaRef.push().setValue(
                new Cuenta(1, "Matricula 2018-II", "2018-10-24", 350));
        EstadoCuentaRef.push().setValue(
                new Cuenta(2, "Ingles Intermedio AVN I", "2018-10-30", 100));
        EstadoCuentaRef.push().setValue(
                new Cuenta(3, "Interes moratorio Pnesion", "25 AUGT 2018", (float)0.2, true));
        */
        lstCuentas = findViewById(R.id.lstCuentas);
        //lstCuentas.setEmptyView(findViewById(R.id.emptyElement_cuenta));

        filtrarListCuenta(null);
    }

    private void filtrarListCuenta(String squery) {
        final Context ctx = this;
        Query query = EstadoCuentaRef;

        if (squery != null)
            query = EstadoCuentaRef.orderByChild("tipo").equalTo(squery);

        options =
                new FirebaseListOptions.Builder<Cuenta>()
                        .setQuery(query, Cuenta.class)
                        .setLayout(R.layout.grid_estadocuenta_item)
                        .setLifecycleOwner(this)
                        .build();

        adapter = new FirebaseListAdapter<Cuenta>(options) {
            @Override
            protected void populateView(View v, Cuenta c, int position) {
                // Get references to the views of grid_notificacion_item.xml
                TextView descripcion = v.findViewById(R.id.txtdescripcion_cuenta);
                TextView fecha = v.findViewById(R.id.txtfecha_cuenta);
                TextView monto = v.findViewById(R.id.txtmonto_cuenta);

                // Set their text
                descripcion.setText(c.getDescripcion());
                fecha.setText(c.getFecha());
                monto.setText("S/." + String.valueOf(c.getMonto()));

                if(c.isMora()) {
                    monto.setTextColor(ContextCompat.getColor(ctx, R.color.red_primary));
                }
                notifyDataSetChanged();
            }
        };
        lstCuentas.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
