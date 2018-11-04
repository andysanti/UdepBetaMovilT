package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.adapters.ViewPagerAdapter;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.daos.*;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments.GridViewFragment;

public class RegistroMatriculaActivity extends AppCompatActivity {

    TextView fecha;

    private DAOSQLAlumno dao_Alumno;
    private DAOSQLCurso dao_Curso ;
    private DAOSQLMatricula dao_Matricula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_matricula);

        //configurar el botón Back (arriba)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fecha = findViewById(R.id.txt_regmatr_fecha);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);
    }

    //Setting View Pager
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new GridViewFragment(), "GridView");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());
        fecha.setText(date);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
