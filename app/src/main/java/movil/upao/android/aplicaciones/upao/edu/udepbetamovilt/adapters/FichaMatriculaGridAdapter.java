package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Curso;

public class FichaMatriculaGridAdapter extends ArrayAdapter {

    public FichaMatriculaGridAdapter(@NonNull Context context, List<Curso> cursos) {
        super(context, R.layout.grid_fichamatricula_item, R.id.txt_curso_nombre, cursos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(convertView== null)
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.grid_fichamatricula_item,parent,false);

        TextView txtNombre = view.findViewById(R.id.txt_curso_nombre);
        TextView txtCodigo = view.findViewById(R.id.txt_curso_codigo);
        TextView txtCreditos = view.findViewById(R.id.txt_fichmatr_creditos);
        TextView txtCiclo = view.findViewById(R.id.txt_curso_ciclo);

        Curso curso = (Curso) getItem(position);

        txtNombre.setText(curso.getNombre());
        txtCodigo.setText(curso.getCodigo());
        txtCreditos.setText(Integer.toString(curso.getCreditos()));
        txtCiclo.setText(curso.getCiclo());

        return view;
    }
}
