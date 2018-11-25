package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Alumno;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Curso;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.adapters.FichaMatriculaGridAdapter;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.daos.*;

public class FichaMatriculaActivity extends AppCompatActivity {

    private ListView lst_fichmatr_Cursos;
    private LinearLayout ll_Detalle;
    private TextView link_Detalle;

    private DAOSQLCurso dao_Curso ;
    private DAOSQLMatricula dao_Matricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_matricula);

        //configurar el botón Back (arriba)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lst_fichmatr_Cursos = findViewById(R.id.lst_fichmatr_Cursos);

        dao_Curso = new DAOSQLCurso(this);
        if(dao_Curso.all().size()==0) {
            /* 8 */
            dao_Curso.save(new Curso(-1, "ICSI - 424", "ADM DE PROY DE SIST DE INFOR", "08",4, "Lic. Juan Perez", ""));
            dao_Curso.save(new Curso(-1, "ICSI - 425", "SISTEMA DE SOPORTE DE DECISION", "08",4, "Lic. Juan Perez", ""));
            dao_Curso.save(new Curso(-1, "ICSI - 426", "ADM DE REDES Y SEG DE LA INF", "08",4, "Lic. Juan Perez", ""));
            dao_Curso.save(new Curso(-1, "ICSI - 427", "GERENCIA DE TI", "08",4, "Lic. Juan Perez", ""));
            dao_Curso.save(new Curso(-1, "ICSI - 428", "PROYECTO DE INVESTIGACION", "08",2, "Lic. Juan Perez", ""));
            /* 9 */
            dao_Curso.save(new Curso(-1, "ICSI - 434", "TESIS I", "09",3, "Lic. Juan Perez", ""));
            dao_Curso.save(new Curso(-1, "ICSI - 432", "SISTEMAS DE INFORMACION ESTRAT", "09", 4,"Mgtr. Richard Vladimir", ""));
            dao_Curso.save(new Curso(-1, "ICSI - 436", "DESA DE APLIC PARA DISP MOVI", "09", 4,"Ing. Martin Campos", ""));
            dao_Curso.save(new Curso(-1, "ICSI - 431", "AUDITORIA DE SISTEMAS DE INFO", "09", 5,"Mgtr. Jose Arellano", ""));
            dao_Curso.save(new Curso(-1, "ICSI - 433", "ADM Y ARQ DE MAINFRAMES", "09", 4,"Ing. Polo Abad", ""));
            /* 10 */
            dao_Curso.save(new Curso(-1, "ICSI - 437", "TESIS II", "10",3, "Lic. Juan Perez", ""));
            dao_Curso.save(new Curso(-1, "ICSI - 438", "ETICA Y DEONTOLOGIA ", "10",4, "Lic. Juan Perez", ""));
            dao_Curso.save(new Curso(-1, "ICSI - 439", "PROGRAMACION DE MAINFRAMES", "10",4, "Lic. Juan Perez", ""));
            dao_Curso.save(new Curso(-1, "ICSI - 440", "ELECTIVO II", "10",4, "Lic. Juan Perez", ""));
        }

        dao_Matricula = new DAOSQLMatricula(this, dao_Curso);

        FichaMatriculaGridAdapter adapter = new FichaMatriculaGridAdapter(this, dao_Matricula.allCursosByAlumno(1));
        lst_fichmatr_Cursos.setAdapter(adapter);


        ll_Detalle = findViewById(R.id.ll_fichmatr_Detalle);
        link_Detalle = findViewById(R.id.link_fichmatr_Detalle);

        SpannableString mitextoU = new SpannableString("Detalles");
        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
        link_Detalle.setText(mitextoU);
    }

    public void onClickMostrarOcultarDetalles(View v){
        if (ll_Detalle.getVisibility() == View.VISIBLE) {
            ll_Detalle.setVisibility(View.GONE);
            //link_Detalle.setVisibility(v.VISIBLE);
            Toast.makeText(this, "Ocultar Detalles", Toast.LENGTH_SHORT).show();
        }else{
            ll_Detalle.setVisibility(View.VISIBLE);
            //link_Detalle.setVisibility(v.GONE);
            Toast.makeText(this, "Mostrar Detalles", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(dao_Matricula.all().size()==0){
            Intent intent = new Intent(this, RegistroMatriculaActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
