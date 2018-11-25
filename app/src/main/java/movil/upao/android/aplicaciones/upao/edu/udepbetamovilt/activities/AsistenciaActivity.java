package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.ArrayList;
import java.util.List;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Asistencia;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Detalle_Asistencia;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.utils.UdepSharedPreferences;

public class AsistenciaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private FirebaseDatabase database;
    private DatabaseReference asistenciasRef;
    private DatabaseReference detalleRef;
    private ListAdapter adapter;
    private FirebaseListOptions<Asistencia> options;
    private ListView lstAsistencias;
    private UdepSharedPreferences prefs;
    private Bitmap bitmap;
    private List<Asistencia> asistencias= new ArrayList<Asistencia>();
    final static Asistencia asiste=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);

        prefs = new UdepSharedPreferences(this);
        database = FirebaseDatabase.getInstance();

        lstAsistencias = findViewById(R.id.lstAsistencias);
        lstAsistencias.setEmptyView(findViewById(R.id.emptyElement));
        lstAsistencias.setOnItemClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        asistenciasRef = database.getReference("asistencias");

        asistenciasRef.setValue(null);

        List<Detalle_Asistencia> detAsistencias = new ArrayList<Detalle_Asistencia>();
        detAsistencias.add(new Detalle_Asistencia(1,2,"SISTEMAS DE INFORMACION ESTRATEGICA","PORNO","A"));
        detAsistencias.add(new Detalle_Asistencia(2,2,"SISTEMAS DE INFORMACION ESTRATEGICA","METODOLOGIAS","F"));
        detAsistencias.add(new Detalle_Asistencia(3,2,"SISTEMAS DE INFORMACION ESTRATEGICA","ESTANDARES ISO","F"));
        detAsistencias.add(new Detalle_Asistencia(4,2,"SISTEMAS DE INFORMACION ESTRATEGICA","AUDITORIA DE BASE DE DATOS","T"));
        detAsistencias.add(new Detalle_Asistencia(5,2,"SISTEMAS DE INFORMACION ESTRATEGICA","LEY DE BENFORD","A"));
        detAsistencias.add(new Detalle_Asistencia(6,2,"SISTEMAS DE INFORMACION ESTRATEGICA","AUDITORÍA A LA USABILIDAD DE UN SISTEMA DE INFORMACION","A"));
        detAsistencias.add(new Detalle_Asistencia(7,2,"SISTEMAS DE INFORMACION ESTRATEGICA","EVALUACIONES A LA INGENIERIA","A"));
        detAsistencias.add(new Detalle_Asistencia(8,2,"SISTEMAS DE INFORMACION ESTRATEGICA","SOFTWARE PARA REALIZAR EVALUACIONES","A"));

        List<Detalle_Asistencia> detAsistencias2 = new ArrayList<>();
        detAsistencias2.add(new Detalle_Asistencia(1,3,"AUDITORIA DE SISTEMAS DE LA INFORMACIÓN","REPASO DE CONSULTAS SQL","A"));
        detAsistencias2.add(new Detalle_Asistencia(2,3,"AUDITORIA DE SISTEMAS DE LA INFORMACIÓN","ETL1","F"));
        detAsistencias2.add(new Detalle_Asistencia(3,3,"AUDITORIA DE SISTEMAS DE LA INFORMACIÓN","ETL2","F"));
        detAsistencias2.add(new Detalle_Asistencia(4,3,"AUDITORIA DE SISTEMAS DE LA INFORMACIÓN","PRACTICA DE ETL","T"));
        detAsistencias2.add(new Detalle_Asistencia(5,3,"AUDITORIA DE SISTEMAS DE LA INFORMACIÓN","DESARROLLO DE PROCESO ETL","A"));
        detAsistencias2.add(new Detalle_Asistencia(6,3,"AUDITORIA DE SISTEMAS DE LA INFORMACIÓN","SEMANA DE EVALUACION PARCIAL","A"));
        detAsistencias2.add(new Detalle_Asistencia(7,3,"AUDITORIA DE SISTEMAS DE INFO","METODOLOGIAS DE DESARROLLO","A"));

        List<Detalle_Asistencia> detAsistencias3 = new ArrayList<>();
        detAsistencias3.add(new Detalle_Asistencia(1,4,"TESIS I","REVISIÓN DE PLAN DE TESIS","A"));
        detAsistencias3.add(new Detalle_Asistencia(2,4,"TESIS I","PRESENTACION DE PLANES DE TESIS","A"));
        detAsistencias3.add(new Detalle_Asistencia(3,4,"TESIS I","PRESENTACION DE FRAMEWORK DE CONOCIMIENTO DE PLAN DE TESIS","A"));
        detAsistencias3.add(new Detalle_Asistencia(4,4,"TESIS I","ARTEFACTO DE SOTWARE","A"));
        detAsistencias3.add(new Detalle_Asistencia(5,4,"TESIS I","CICLO DE VIDA DEL ARTEFACTO DE SOFTWARE","A"));
        detAsistencias3.add(new Detalle_Asistencia(6,4,"TESIS I","PLANTILLA DEL INFORME DE TESIS","A"));
        detAsistencias3.add(new Detalle_Asistencia(7,4,"TESIS I","PLANTILLA DEL INFORME DE TESIS","A"));

        //detAsistencias2.add(new Detalle_Asistencia());

        /*asistenciasRef.push().setValue(
                new Asistencia(2, 76, 24, "Habilitado", "201979", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjcwsANNsmcCfuFa820dYuPi3M8_3V9ol1eq0jNPwCpCKDuvaF", "SISTEMAS DE INFORMACION ESTRATEGICA", "Silvia Rodriguez",detAsistencias));

        asistenciasRef.push().setValue(
                new Asistencia(3, 100, 0, "Habilitado", "201979", "https://ta.azureedge.net/p/images/usuarios/l/ESGJ1fKw1UiaDARQsPv6Q58msg2tWn_f0.jpg", "AUDITORIA DE SISTEMAS DE LA INFORMACIÓN", "Silvia Rodriguez",detAsistencias2));
        asistenciasRef.push().setValue(
                new Asistencia(4, 5, 95, "Inhabilitado", "201979", "https://ta.azureedge.net/p/images/usuarios/l/y9HJoZ711Ug5FwKn9EhETb43rpJFDNmO0.jpg", "DESARROLLO PARA APLICACIONES MOVILES", "ALI LOZANO CHU",detAsistencias3));


            */
        Asistencia asistencia = new Asistencia(2, 76, 24, "Habilitado", "201979", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjcwsANNsmcCfuFa820dYuPi3M8_3V9ol1eq0jNPwCpCKDuvaF", "SISTEMAS DE INFORMACION ESTRATEGICA", detAsistencias);
        asistenciasRef.child(""+asistencia.getId()).child("asistencias").setValue(asistencia.getAsistencias());
        asistenciasRef.child(""+asistencia.getId()).child("faltas").setValue(asistencia.getFaltas());
        asistenciasRef.child(""+asistencia.getId()).child("estado").setValue(asistencia.getEstado());
        asistenciasRef.child(""+asistencia.getId()).child("semestre").setValue(asistencia.getSemestre());
        asistenciasRef.child(""+asistencia.getId()).child("photo_url").setValue(asistencia.getPhoto_url());
        asistenciasRef.child(""+asistencia.getId()).child("curso").setValue(asistencia.getCurso());
        asistenciasRef.child(""+asistencia.getId()).child("detalle_asistencia").setValue(asistencia.getDetAsistencias());

        asistencia=new Asistencia(3, 100, 0, "Habilitado", "201979", "https://ta.azureedge.net/p/images/usuarios/l/ESGJ1fKw1UiaDARQsPv6Q58msg2tWn_f0.jpg", "AUDITORIA DE SISTEMAS DE LA INFORMACIÓN",detAsistencias2);
        asistenciasRef.child(""+asistencia.getId()).child("asistencias").setValue(asistencia.getAsistencias());
        asistenciasRef.child(""+asistencia.getId()).child("faltas").setValue(asistencia.getFaltas());
        asistenciasRef.child(""+asistencia.getId()).child("estado").setValue(asistencia.getEstado());
        asistenciasRef.child(""+asistencia.getId()).child("semestre").setValue(asistencia.getSemestre());
        asistenciasRef.child(""+asistencia.getId()).child("photo_url").setValue(asistencia.getPhoto_url());
        asistenciasRef.child(""+asistencia.getId()).child("curso").setValue(asistencia.getCurso());
        asistenciasRef.child(""+asistencia.getId()).child("detalle_asistencia").setValue(asistencia.getDetAsistencias());

        asistencia=new Asistencia(4, 5, 95, "Inhabilitado", "201979", "https://ta.azureedge.net/p/images/usuarios/l/y9HJoZ711Ug5FwKn9EhETb43rpJFDNmO0.jpg", "DESARROLLO PARA APLICACIONES MOVILES",detAsistencias3);
        asistenciasRef.child(""+asistencia.getId()).child("asistencias").setValue(asistencia.getAsistencias());
        asistenciasRef.child(""+asistencia.getId()).child("faltas").setValue(asistencia.getFaltas());
        asistenciasRef.child(""+asistencia.getId()).child("estado").setValue(asistencia.getEstado());
        asistenciasRef.child(""+asistencia.getId()).child("semestre").setValue(asistencia.getSemestre());
        asistenciasRef.child(""+asistencia.getId()).child("photo_url").setValue(asistencia.getPhoto_url());
        asistenciasRef.child(""+asistencia.getId()).child("curso").setValue(asistencia.getCurso());
        asistenciasRef.child(""+asistencia.getId()).child("detalle_asistencia").setValue(asistencia.getDetAsistencias());

        //detalleRef.push().setValue(new Detalle_Asistencia());
       /*asistenciasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    int id=postSnapshot.child().getValue(int.class);
                    Asistencia asiste = new Asistencia();
                    asiste.setId(id);

                    //asistencias.add(asiste);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        filtrarListNotif(null);

    }

    //Class for download IMAGE
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


    private void filtrarListNotif(String squery) {

        Query query = asistenciasRef;
        //if (squery != null)
        // query = asistenciasRef.orderByChild("tipo").equalTo(squery);

        options =
                new FirebaseListOptions.Builder<Asistencia>()
                        .setQuery(query, Asistencia.class)
                        .setLayout(R.layout.grid_asistencia__item)
                        .setLifecycleOwner(this)
                        .build();

        adapter = new FirebaseListAdapter<Asistencia>(options) {

            @Override
            protected void populateView(View v, Asistencia s, int i) {

                // Get references to the views of grid_notificacion_item.xml
                TextView asiste = v.findViewById(R.id.lblNroAsistencias);
                TextView asiste2 = v.findViewById(R.id.lblNroAsistencias2);
                TextView curso = v.findViewById(R.id.lblCurso);
                TextView faltas = v.findViewById(R.id.lblNroFaltas);
                TextView estado = v.findViewById(R.id.lblEstado);
                TextView semestre = v.findViewById(R.id.lblSemestre);
                ImageView icon = v.findViewById(R.id.imgstar);

                // Set their text
                asiste.setText("" + s.getAsistencias());
                faltas.setText("" + s.getFaltas());
                asiste2.setText("" + s.getAsistencias());
                estado.setText(s.getEstado());
                semestre.setText(s.getSemestre());
                curso.setText(s.getCurso());

                if (s.getEstado().equals("Habilitado")) {
                    estado.setTextColor(Color.parseColor("#35682d"));
                } else {
                    estado.setTextColor(Color.parseColor("#ff0000"));

                }
                if (s.getAsistencias() < 50) {

                    asiste2.setTextColor(Color.parseColor("#ff0000"));
                }

                asistencias.add(s);
                new GetImageFromURL(icon).execute(s.getPhoto_url());
                notifyDataSetChanged();
            }
        };
        lstAsistencias.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        int ide= asistencias.get(position).getId();
        Intent intent = new Intent(this, DetalleAsistenciaActivity.class);
        intent.putExtra("id",ide);
        startActivity(intent);

    }


}