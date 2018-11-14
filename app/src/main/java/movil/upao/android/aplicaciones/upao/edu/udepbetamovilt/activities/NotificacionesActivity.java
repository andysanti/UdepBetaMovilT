package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Notificacion;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;
import com.google.firebase.database.Query;

public class NotificacionesActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference notificacionesRef;
    private ListAdapter adapter;
    private FirebaseListOptions<Notificacion> options;
    private ListView lstNotificaciones;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);

        //configurar el botón Back (arriba)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance();
        notificacionesRef = database.getReference("notificaciones");
        /*
        notificacionesRef.push().setValue(
                new Notificacion(3, "Notas","La docente, Maria Pilar del Socorro ha subido las notas del componente 2",
                        "2018-10-30","https://secure.gravatar.com/avatar/b6f4d863f6d9dc1e4aef9f4708588cc0?s=96&d=https%3A%2F%2Ftusdudasdesalud.com%2Fwp-content%2Fplugins%2Fwp-user-avatar%2Fimages%2Fwpua-96x96.png"));

        notificacionesRef.push().setValue(
                new Notificacion(4, "Avisos","Por velaciones, la universidad cesará sus actividades el dia Jueves 01 de Nov. El viernes la atención es normal.",
                        "2018-10-30","https://i0.wp.com/cdna.c3dt.com/icon/257969-com.ficct.eddye.misapuntes.png"));

        notificacionesRef.push().setValue(
                new Notificacion(5, "Notas","La docente, Maria Pilar ha subido material en la semana 5.",
                        "2018-10-30","https://secure.gravatar.com/avatar/b6f4d863f6d9dc1e4aef9f4708588cc0?s=96&d=https%3A%2F%2Ftusdudasdesalud.com%2Fwp-content%2Fplugins%2Fwp-user-avatar%2Fimages%2Fwpua-96x96.png"));

        notificacionesRef.push().setValue(
                new Notificacion(6, "Semana de arquitectura 2018","Semana de arquitectura 2018 30 años FAUA-DEPA, se estará llevando del 29 de Octubre al 3 de Noviembre",
                        "2018-11-01","https://i0.wp.com/cdna.c3dt.com/icon/257969-com.ficct.eddye.misapuntes.png"));
        */
        lstNotificaciones = findViewById(R.id.lstNotificaciones);
        lstNotificaciones.setEmptyView(findViewById(R.id.emptyElement));
        /*
        FirebaseListOptions<Notificacion> options =
                new FirebaseListOptions.Builder<Notificacion>()
                        .setQuery(notificacionesRef, Notificacion.class)
                        .setLayout(R.layout.grid_notificacion_item)
                        .setLifecycleOwner(this)
                        .build();

        adapter = new FirebaseListAdapter<Notificacion>(options) {
            @Override
            protected void populateView(View v, Notificacion s, int i) {
                // Get references to the views of grid_notificacion_item.xml
                TextView titulo         = (TextView) v.findViewById(R.id.txtTituloNotif);
                TextView descripcion    = (TextView) v.findViewById(R.id.txtDescripcionNotif);
                ImageView icon          = (ImageView) v.findViewById(R.id.iconNotif);

                // Set their text
                titulo.setText(s.getTitulo());
                descripcion.setText(s.getDescripcion());

                new GetImageFromURL(icon).execute(s.getFoto_url());
                notifyDataSetChanged();
            }
        };
        lstNotificaciones.setAdapter(adapter);
        */
        filtrarListNotif(null);
    }

    //Class for download IMAGE
    public class GetImageFromURL extends AsyncTask<String, Void, Bitmap> {
        ImageView imgV;

        public GetImageFromURL(ImageView imgV){
            this.imgV = imgV;
        }

        @Override
        protected Bitmap doInBackground(String... url) {
            String urldisplay = url[0];
            bitmap = null;
            try {
                InputStream srt = new java.net.URL(urldisplay).openStream();
                bitmap = BitmapFactory.decodeStream(srt);
            } catch (Exception e){
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_notificacion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.opt_todos:
                filtrarListNotif(null);
                return true;
            case R.id.opt_profesor:
                filtrarListNotif("profesores");
                return true;
            case R.id.opt_avisos:
                filtrarListNotif("avisos");
                return true;
            case R.id.opt_otros:
                filtrarListNotif("otros");
                return true;
        }
        return false;
    }

    private void filtrarListNotif(String squery) {

        Query query = notificacionesRef;
        if(squery!=null)
            query = notificacionesRef.orderByChild("tipo").equalTo(squery);

        options =
                new FirebaseListOptions.Builder<Notificacion>()
                        .setQuery(query, Notificacion.class)
                        .setLayout(R.layout.grid_notificacion_item)
                        .setLifecycleOwner(this)
                        .build();

        adapter = new FirebaseListAdapter<Notificacion>(options) {
            @Override
            protected void populateView(View v, Notificacion s, int i) {
                // Get references to the views of grid_notificacion_item.xml
                TextView titulo         = (TextView) v.findViewById(R.id.txtTituloNotif);
                TextView descripcion    = (TextView) v.findViewById(R.id.txtDescripcionNotif);
                ImageView icon          = (ImageView) v.findViewById(R.id.iconNotif);

                // Set their text
                titulo.setText(s.getTitulo());
                descripcion.setText(s.getDescripcion());

                new GetImageFromURL(icon).execute(s.getFoto_url());
                notifyDataSetChanged();
            }
        };
        lstNotificaciones.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}