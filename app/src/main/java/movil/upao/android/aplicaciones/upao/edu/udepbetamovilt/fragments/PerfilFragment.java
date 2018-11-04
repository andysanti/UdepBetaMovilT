package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;


public class PerfilFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private TextView txtNombre;
    public PerfilFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        showToolbar("", false, view);
       // showToolbar("",false,view);
        String imageUrl = "https://sigadocentes.udep.edu.pe/fotossiga/persona/1016398_a866d43.jpg";
        String nombre="Andy Santi";
        //txtNombre = (TextView)view.findViewById(R.id.txtNombre);
        //txtNombre.setText(nombre);
        CircleImageView imageView = (CircleImageView) view.findViewById(R.id.imagenperfil);
        Context c = getActivity().getApplicationContext();
        Picasso.with(c).load(imageUrl)
                .fit()
                .into(imageView);

        return view;
    }

    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

    // TODO: Rename method, update argument and hook method into UI event

  }
