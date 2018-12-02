package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Curso;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Matricula;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.adapters.ViewPagerAdapter;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.daos.*;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments.GridViewFragment;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.utils.FHelps;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.utils.UdepSharedPreferences;

public class RegistroMatriculaActivity extends AppCompatActivity {

    TextView fecha;
    GridViewFragment gridViewFragment;

    private DAOSQLAlumno dao_Alumno;
    private DAOSQLCurso dao_Curso ;
    private DAOSQLMatricula dao_Matricula;
    private FHelps fHelps;
    private UdepSharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_matricula);

        //configurar el botón Back (arriba)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prefs = new UdepSharedPreferences(this);
        dao_Curso = new DAOSQLCurso(this);
        dao_Alumno = new DAOSQLAlumno(this);
        dao_Matricula = new DAOSQLMatricula(this, dao_Curso);
        
        fecha = findViewById(R.id.txt_regmatr_fecha);

        fHelps = new FHelps();

        gridViewFragment = new GridViewFragment();
        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);
    }

    //Setting View Pager
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(gridViewFragment, "GridView");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());
        fecha.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_regmatricula, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.opt_deseleccionar_cursos:
                desSeleccinarTodos();
                return true;
            case R.id.opt_registrar_cursos:
                abre_dialogo();
                return true;
        }
        return false;
    }

    private void desSeleccinarTodos() {
        gridViewFragment.deselect_all();
    }

    private void registrarMatricula(List<Curso> cursos) {
        final Context ctx = this;

        int id_alumno =  prefs.getInt(UdepSharedPreferences.PREF_ID, -1);

        for (int i = 0; i < cursos.size(); i++)
        {
            dao_Matricula.save(new Matricula(-1, cursos.get(i).getId(), id_alumno, fecha.getText().toString(), "2018-II"));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Registro Matricula");
        builder.setMessage("Registro de Matricula exitoso.");
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(RegistroMatriculaActivity.this, FichaMatriculaActivity.class);
                startActivity(intent);
                RegistroMatriculaActivity.this.finish();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    public void abre_dialogo() {
        final List<Curso> cursos = gridViewFragment.show_ones();


        for (int i = 0; i < cursos.size(); i++)
        {
            if(cursos.get(i).getCiclo() != "08"){
                fHelps.f_showMessage(this, "Ha seleccionado cursos que no corresponden a su ciclo actual.","Udep In");
                return;
            }
        }

        if(cursos.size()==0){
            fHelps.f_showMessage(this, "No ha seleccionado ningún curso.","Udep In");
            return;
        }
        else {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Registro Matricula");
            builder.setMessage("Se procederá al registro de matrícula. Seguro que desea Proceder?");
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    registrarMatricula(cursos);
                }
            });
            builder.setNegativeButton(android.R.string.cancel, null);
            Dialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
