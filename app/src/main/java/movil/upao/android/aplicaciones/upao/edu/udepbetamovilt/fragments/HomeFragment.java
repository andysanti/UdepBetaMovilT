package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities.AsistenciaActivity;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities.ChatActivity;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities.EstadoCuentaActivity;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities.FichaMatriculaActivity;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities.HorarioActivity;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities.MainActivity;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities.ReporteNotasActivity;


public class HomeFragment extends Fragment {

    public HomeFragment() {
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
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        //showToolbar(getResources().getString(R.string.tab_home), false, view);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //loadGridView(view);
        //loadCursoGridView(view);
        onClickEvent(view);
    }

    private void onClickEvent(View view) {

        view.findViewById(R.id.fichaMatriculaId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FichaMatriculaActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.miHorarioId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast toast1 =
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Proximamente", Toast.LENGTH_SHORT);

                toast1.show();
               // Intent intent2 = new Intent(getActivity(), HorarioActivity.class);
               // startActivity(intent2);


            }
        });
        view.findViewById(R.id.asistenciaId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AsistenciaActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.estadoCuentaId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EstadoCuentaActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.reporteNotasId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReporteNotasActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.mensajeriaId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);


    }


}
