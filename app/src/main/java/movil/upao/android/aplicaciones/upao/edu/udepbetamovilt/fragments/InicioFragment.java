package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.daos.DAOSQLAlumno;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.utils.UdepSharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends Fragment {

    private TextView txtNombrePerfil;
    private UdepSharedPreferences prefs;
    private DAOSQLAlumno dao_Alumno;

    private Bitmap bitmap;

    public InicioFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new UdepSharedPreferences(getActivity()); // creo mis preferencias
        dao_Alumno = new DAOSQLAlumno(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        //showToolbar("", false, view);
        // showToolbar("",false,view);
        String usuario = prefs.getString(UdepSharedPreferences.PREF_USUARIO, null);
        String imageUrl = prefs.getString(UdepSharedPreferences.PREF_PATH_PHOTO, null);
        String nombre="Andy Santi";
        txtNombrePerfil = (TextView)view.findViewById(R.id.textView3);
        txtNombrePerfil.setText(usuario);
/*
        txtNombrePerfil.setText(nombre);
        txtNombrePerfilToolBar.setText(nombre);*/
        CircleImageView imageView = (CircleImageView) view.findViewById(R.id.imagenperfil);
        /*
        Context c = getActivity().getApplicationContext();
        Picasso.with(c).load(imageUrl)
                .fit()
                .into(imageView);
       */
        new GetImageFromURL(imageView).execute(imageUrl);
        return view;
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



}
