package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Curso;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.adapters.ExpandableListAdapter;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.daos.DAOSQLCurso;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments.DialogReporteFragment;

public class ReporteNotasActivity extends AppCompatActivity {
    private static ExpandableListView expandableListView;
    private static ExpandableListAdapter adapter;

    // Array list for header
    private ArrayList<String> header = new ArrayList<String>();
    // Array list for child items
//  private List<Curso> child = new ArrayList<Curso>();
    // Hash map for both header and child
    HashMap<String, List<Curso>> hashMap = new HashMap<String, List<Curso>>();

    private DAOSQLCurso dao_Curso ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_notas);
        //configurar el botón Back (arriba)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        expandableListView = (ExpandableListView) findViewById(R.id.reporte_lvExp);

        // Setting group indicator null for custom indicator
        expandableListView.setGroupIndicator(null);
        expandableListView.setEmptyView(findViewById(R.id.reporte_emptyElement));

        dao_Curso = new DAOSQLCurso(this);

        setItems();
        setListener();
    }


    // Setting headers and childs to expandable listview
    void setItems() {

        // Adding header and childs to hash map
        Build_Reporte();

        adapter = new ExpandableListAdapter(ReporteNotasActivity.this, this.header, this.hashMap);

        // Setting adpater over expandablelistview
        expandableListView.setAdapter(adapter);
    }

    // Setting different listeners to expandablelistview
    void setListener() {

        // This listener will show toast on group click
        expandableListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView listview, View view, int group_pos, long id) {

                Toast.makeText(ReporteNotasActivity.this,
                        "You clicked : " + adapter.getGroup(group_pos), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // This listener will expand one group at one time
        // You can remove this listener for expanding all groups
        expandableListView
                .setOnGroupExpandListener(new OnGroupExpandListener() {

                    // Default position
                    int previousGroup = -1;

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        if (groupPosition != previousGroup)
                            // Collapse the expanded group
                            expandableListView.collapseGroup(previousGroup);
                        
                        previousGroup = groupPosition;
                    }

                });

        // This listener will show toast on child click
        expandableListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listview, View view, int groupPos, int childPos, long id) {
                Curso curso = (Curso) adapter.getChild(groupPos, childPos);
                dialogoPersonalizado(curso);
                return false;
            }
        });
    }

    public void Build_Reporte(){
//      HashMap<String, List<Curso>> hashMap = new HashMap<String, List<Curso>>();

        for (int i = 1; i <= 9; i++) {
            String DescpCiclo = "Ciclo  0" + i;
            String ciclo = "0" + i;
            List<Curso> ltaCursos = dao_Curso.getByCiclo(ciclo);

            if(ltaCursos.size() > 0){
                // Adding headers to list
                this.header.add(DescpCiclo);
                // Adding child data
                this.hashMap.put(DescpCiclo, ltaCursos );
            }
        }
//      return hashMap;
    }

    public void dialogoPersonalizado(Curso c){
        DialogReporteFragment dialogoPersonalizado = new DialogReporteFragment();
        Bundle bundle = new Bundle();
        bundle.putString("codigo", c.getCodigo());
        bundle.putString("nombre", c.getNombre());
        bundle.putString("creditos", String.valueOf(c.getCreditos()));
        bundle.putString("nota", String.valueOf(c.getNota()));

        dialogoPersonalizado.setArguments(bundle);
        dialogoPersonalizado.show(getSupportFragmentManager(), "personalizado");

        android.app.Fragment frag = getFragmentManager().findFragmentByTag("personalizado");

        if (frag != null) {
            getFragmentManager().beginTransaction().remove(frag).commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}