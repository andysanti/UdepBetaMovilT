package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Curso;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;

public class DialogReporteFragment extends DialogFragment {

    private TextView txt_codigo;
    private TextView txt_nombre;
    private TextView txt_ciclo;
    private TextView txt_nota;

    private String codigo;
    private String nombre;
    private String creditos;
    private String nota;

    // El contructor vacio es requerido para el dialogFragment
    public DialogReporteFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            codigo = getArguments().getString("codigo","");
            nombre = getArguments().getString("nombre","");
            creditos = getArguments().getString("creditos","");
            nota = getArguments().getString("nota","");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogo_personalizado, container);

        txt_codigo = view.findViewById(R.id.reporte_dialog_codigo);
        txt_nombre = view.findViewById(R.id.reporte_dialog_nombre);
        txt_ciclo = view.findViewById(R.id.reporte_dialog_ciclo);
        txt_nota = view.findViewById(R.id.reporte_dialog_nota);

        txt_codigo.setText(codigo);
        txt_nombre.setText(nombre);
        txt_ciclo.setText(creditos);
        txt_nota.setText(nota);

        getDialog().setTitle("Detalle de Curso");

        return view;
    }
}
