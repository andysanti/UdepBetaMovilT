package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.adapters.GridListAdapter;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.daos.DAOSQLCurso;

/**
 * Created by sonu on 08/02/17. http://www.androhub.com/android-listview-checkbox/
 */
public class GridViewFragment extends Fragment {
    private Context context;
    private GridListAdapter adapter;
    private ArrayList<String> arrayList;
    private DAOSQLCurso dao_Curso ;

    public GridViewFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        //
        dao_Curso = new DAOSQLCurso(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.grid_view_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //loadGridView(view);
        loadCursoGridView(view);
        onClickEvent(view);
    }

    private void loadCursoGridView(View view) {
        GridView gridView = (GridView) view.findViewById(R.id.grid_view);
        arrayList = new ArrayList<>();
        for(int i = 0; i < dao_Curso.all().size(); i++)
            arrayList.add(dao_Curso.all().get(i).getNombre());

        adapter = new GridListAdapter(context, arrayList, false);
        gridView.setAdapter(adapter);
    }

    private void loadGridView(View view) {
        GridView gridView = (GridView) view.findViewById(R.id.grid_view);
        arrayList = new ArrayList<>();
        for (int i = 1; i <= 50; i++)
            arrayList.add("GridView Items " + i);

        adapter = new GridListAdapter(context, arrayList, false);
        gridView.setAdapter(adapter);
    }

    private void onClickEvent(View view) {
        /*
        view.findViewById(R.id.show_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray selectedRows = adapter.getSelectedIds();//Get the selected ids from adapter
                //Check if item is selected or not via size
                if (selectedRows.size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    //Loop to all the selected rows array
                    for (int i = 0; i < selectedRows.size(); i++) {

                        //Check if selected rows have value i.e. checked item
                        if (selectedRows.valueAt(i)) {

                            //Get the checked item text from array list by getting keyAt method of selectedRowsarray
                            String selectedRowLabel = arrayList.get(selectedRows.keyAt(i));

                            //append the row label text
                            stringBuilder.append(selectedRowLabel + "\n");
                        }
                    }
                    Toast.makeText(context, "Selected Rows\n" + stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
        view.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray selectedRows = adapter.getSelectedIds();//Get the selected ids from adapter
                //Check if item is selected or not via size
                if (selectedRows.size() > 0) {
                    //Loop to all the selected rows array
                    for (int i = (selectedRows.size() - 1); i >= 0; i--) {

                        //Check if selected rows have value i.e. checked item
                        if (selectedRows.valueAt(i)) {

                            //remove the checked item
                            arrayList.remove(selectedRows.keyAt(i));
                        }
                    }

                    //notify the adapter and remove all checked selection
                    adapter.removeSelection();
                }
            }
        });
        */
    }

}
