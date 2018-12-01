package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
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

import java.io.InputStream;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Detalle_Asistencia;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;

public class DetalleAsistenciaActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference detalleRef;
    private Bitmap bitmap;
    private ListView lstDetalleAsistencias;
    private ListAdapter adapter;
    private FirebaseListOptions<Detalle_Asistencia> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_asistencia);

        //configurar el botón Back (arriba)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Integer idAsistencia = intent.getIntExtra("id", 0);
        int asistencias=intent.getIntExtra("asistencias",0);
        int faltas = intent.getIntExtra("faltas",0);
        String semestre=intent.getStringExtra("semestre");
        String estado= intent.getStringExtra("estado");
        String area= intent.getStringExtra("curso");
        String url=intent.getStringExtra("url");

        TextView asiste = findViewById(R.id.lblNroAsistencias);
        TextView asiste2 = findViewById(R.id.lblNroAsistencias2);
        TextView curso = findViewById(R.id.lblCurso);
        TextView falto = findViewById(R.id.lblNroFaltas);
        TextView state = findViewById(R.id.lblEstado);
        TextView semes = findViewById(R.id.lblSemestre);
        ImageView icon = findViewById(R.id.imgteacher);
        TextView porcentaje= findViewById(R.id.lblPorcentaje2);

        asiste.setText(""+asistencias);
        asiste2.setText(""+asistencias);
        curso.setText(area);
        falto.setText(""+faltas);
        state.setText(estado);
        semes.setText(semestre);
        new GetImageFromURL(icon).execute(url);

        if (estado.equals("Habilitado")) {
            state.setTextColor(Color.parseColor("#35682d"));
        } else {
            state.setTextColor(Color.parseColor("#ff0000"));

        }
        if (asistencias < 50) {

            asiste2.setTextColor(Color.parseColor("#ff0000"));
            porcentaje.setTextColor(Color.parseColor("#ff0000"));
        }

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
                TextView fecha =v.findViewById(R.id.lblFecha);

                sesion.setText(s.getSesion());
                tipoAsiste.setText(s.getTipoAsistencia());
                fecha.setText(s.getFecha());
                if(s.getTipoAsistencia().equals("A")) {
                    tipoAsiste.setTextColor(Color.parseColor("#35682d"));
                    tipoAsiste.setTypeface(null,Typeface.BOLD);
                }
                else {
                    tipoAsiste.setTextColor(Color.parseColor("#ff0000"));
                }

                notifyDataSetChanged();
            }
        };
        lstDetalleAsistencias = findViewById(R.id.lstDetalleAsistencia);
        lstDetalleAsistencias.setAdapter(adapter);
    }

    public class GetImageFromURL extends AsyncTask<String, Void, Bitmap> {
        ImageView imgV;

        public GetImageFromURL(ImageView imgV) {
            this.imgV = imgV;
        }

        @Override
        protected Bitmap doInBackground(String... url) {
            String urldisplay = url[0];
            bitmap = null;
            try {
                InputStream srt = new java.net.URL(urldisplay).openStream();
                bitmap = BitmapFactory.decodeStream(srt);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgV.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}