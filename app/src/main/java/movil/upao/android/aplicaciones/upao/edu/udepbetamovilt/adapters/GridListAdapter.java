package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.adapters;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Curso;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonu on 08/02/17. http://www.androhub.com/android-listview-checkbox/
 */

public class GridListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> arrayList;
    private List<Curso> cursos;
    private LayoutInflater inflater;
    private boolean isListView;
    private SparseBooleanArray mSelectedItemsIds;

    public GridListAdapter(Context context, ArrayList<String> arrayList, boolean isListView) {
        this.context = context;
        this.arrayList = arrayList;
        this.isListView = isListView;
        inflater = LayoutInflater.from(context);
        mSelectedItemsIds = new SparseBooleanArray();
    }

    public GridListAdapter(Context context, List<Curso> cursos) {
        this.context = context;
        this.cursos = cursos;
        inflater = LayoutInflater.from(context);
        mSelectedItemsIds = new SparseBooleanArray();
    }

    @Override
    public int getCount() {
        return cursos.size();
    }

    @Override
    public Object getItem(int i) {
        return cursos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View v, ViewGroup vGroup) {
        ViewHolder viewHolder;
        if (v == null) {
            viewHolder = new ViewHolder();
            v = inflater.inflate(R.layout.grid_custom_row_layout, vGroup, false);

            viewHolder.txt_curso = (TextView) v.findViewById(R.id.gcrl_curso);
            viewHolder.txt_ciclo = (TextView) v.findViewById(R.id.gcrl_ciclo);
            viewHolder.txt_creditos = (TextView) v.findViewById(R.id.gcrl_creditos);
            viewHolder.checkBox = (CheckBox) v.findViewById(R.id.gcrl_checkbox);

            v.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) v.getTag();

        Curso c = (Curso) getItem(i);

        //viewHolder.label.setText(arrayList.get(i));
        viewHolder.txt_curso.setText(c.getNombre());
        viewHolder.txt_ciclo.setText(c.getCiclo());
        viewHolder.txt_creditos.setText( String.valueOf(c.getCreditos()) );
        viewHolder.checkBox.setChecked(mSelectedItemsIds.get(i));

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCheckBox(i, !mSelectedItemsIds.get(i));
            }
        });

        viewHolder.txt_curso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCheckBox(i, !mSelectedItemsIds.get(i));
            }
        });

        return v;
    }

    private class ViewHolder {
        private TextView txt_curso;
        private TextView txt_ciclo;
        private TextView txt_creditos;
        private CheckBox checkBox;
    }


    /**
     * Remove all checkbox Selection
     **/
    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    /**
     * Check the Checkbox if not checked
     **/
    public void checkCheckBox(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, true);
        else
            mSelectedItemsIds.delete(position);

        notifyDataSetChanged();
    }

    /**
     * Return the selected Checkbox IDs
     **/
    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }

}
