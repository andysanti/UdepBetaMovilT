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
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.utils.UdepSharedPreferences;

public class FichaMatriculaActivity extends AppCompatActivity {

    private ListView lst_fichmatr_Cursos;
    private LinearLayout ll_Detalle;
    private TextView link_Detalle;

    private DAOSQLCurso dao_Curso ;
    private DAOSQLMatricula dao_Matricula;
    private UdepSharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_matricula);

        //configurar el botón Back (arriba)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prefs = new UdepSharedPreferences(this);
        lst_fichmatr_Cursos = findViewById(R.id.lst_fichmatr_Cursos);

        dao_Curso = new DAOSQLCurso(this);
        if(dao_Curso.all().size()==0) {
            dao_Curso.save(new Curso(-1,"CIEN - 397","MATEMATICA I","01",4,"","",17));
            dao_Curso.save(new Curso(-1,"CIEN - 532","FISICA GENERAL","01",4,"","",16));
            dao_Curso.save(new Curso(-1,"HUMA - 608","ACT FORMAT I: DES PERS Y LID","01",1,"","",16));
            dao_Curso.save(new Curso(-1,"HUMA - 899","LENGUAJE I","01",4,"","",17));
            dao_Curso.save(new Curso(-1,"HUMA - 900","METODOLOG APREND UNIVERSIT","01",2,"","",15));
            dao_Curso.save(new Curso(-1,"ICSI - 399","FUND. DE PROGRAMACION I","01",3,"","",17));
            dao_Curso.save(new Curso(-1,"ICSI - 401","INTR A LA ING SIS Y TEC DE INF","01",3,"","",17));
            dao_Curso.save(new Curso(-1,"CIEN - 539","FISICA I","02",4,"","",16));
            dao_Curso.save(new Curso(-1,"CIEN - 597","ALGEBRA LINEAL Y GEOM DESCRIPT","02",4,"","",16));
            dao_Curso.save(new Curso(-1,"CIEN - 599","MATEMATICA II","02",4,"","",17));
            dao_Curso.save(new Curso(-1,"HUMA - 641","ACT FORMAT II: APREC MUSICAL","02",1,"","",16));
            dao_Curso.save(new Curso(-1,"HUMA - 901","LENGUAJE II","02",2,"","",17));
            dao_Curso.save(new Curso(-1,"HUMA - 902","PSICOLOGIA GENERAL","02",2,"","",14));
            dao_Curso.save(new Curso(-1,"CIEN - 600","MATEMATICA III","03",4,"","",13));
            dao_Curso.save(new Curso(-1,"HUMA - 679","ACT FORMAT III: APR ART PLAST","03",1,"","",17));
            dao_Curso.save(new Curso(-1,"HUMA - 903","FILOSOFIA DE LA CIENCIA","03",2,"","",15));
            dao_Curso.save(new Curso(-1,"ICSI - 403","PROGRA ORIENTADA A OBJETOS","03",5,"","",17));
            dao_Curso.save(new Curso(-1,"ICSI - 404","FUNDAMENTOS DE SISTEMAS DE INF","03",5,"","",13));
            dao_Curso.save(new Curso(-1,"ICSI - 405","FISICA APLICADA A LA COMPUTAC","03",4,"","",14));
            dao_Curso.save(new Curso(-1,"HUMA - 701","ACT FORMAT IV: VIDA Y OBR A.O.","04",1,"","",18));
            dao_Curso.save(new Curso(-1,"HUMA - 904","REALIDAD NACIONAL Y REGIONAL","04",2,"","",17));
            dao_Curso.save(new Curso(-1,"ICSI - 406","ESTRUCTURAS DE DATOS E INFORMA","04",4,"","",15));
            dao_Curso.save(new Curso(-1,"ICSI - 407","MATEMATICA DISCR PARA LA COMPU","04",4,"","",14));
            dao_Curso.save(new Curso(-1,"ICSI - 408","ARQUITECTURA DE COMPUTADORAS","04",6,"","",12));
            dao_Curso.save(new Curso(-1,"ICSI - 409","ORGANIZACION Y GESTION DE EMP","04",4,"","",17));
            dao_Curso.save(new Curso(-1,"CIEN - 598","ESTADISTICA Y PROBABILIDADES","05",3,"","",15));
            dao_Curso.save(new Curso(-1,"HUMA - 905","METODOLOG INVESTIG CIENTIFICA","05",3,"","",15));
            dao_Curso.save(new Curso(-1,"ICSI - 410","BASE DE DATOS","05",4,"","",16));
            dao_Curso.save(new Curso(-1,"ICSI - 411","AUTOMATAS Y COMPILADORES","05",4,"","",15));
            dao_Curso.save(new Curso(-1,"ICSI - 412","INTERACCION HOMBRE MAQUINA","05",4,"","",17));
            dao_Curso.save(new Curso(-1,"INSO - 135","INGENIERIA DE SOFTWARE I","05",4,"","",15));
            dao_Curso.save(new Curso(-1,"ICSI - 413","DESARROLLO DE APLICACIONES","06",4,"","",16));
            dao_Curso.save(new Curso(-1,"ICSI - 414","SIST DE GEST DE BASE DE DATOS","06",4,"","",15));
            dao_Curso.save(new Curso(-1,"ICSI - 415","MODELADO DE PROCESO DE NEG I","06",3,"","",15));
            dao_Curso.save(new Curso(-1,"ICSI - 416","SISTEMAS OPERATIVOS","06",4,"","",15));
            dao_Curso.save(new Curso(-1,"ICSI - 417","SISTEMAS DE CONT Y PRESUPUEST","06",3,"","",17));
            dao_Curso.save(new Curso(-1,"INSO - 136","INGENIERIA DE SOFTWARE II","06",4,"","",17));
            dao_Curso.save(new Curso(-1,"ICSI - 418","SISTEMAS DE INFORMACION","07",4,"","",13));
            dao_Curso.save(new Curso(-1,"ICSI - 419","MODELADO DE PROCESO DE NEG II","07",4,"","",15));
            dao_Curso.save(new Curso(-1,"ICSI - 420","ARQUI DE REDES DE COMPUTADORAS","07",4,"","",12));
            dao_Curso.save(new Curso(-1,"ICSI - 421","PLANEAMIENTO ESTRAT DE TIC","07",4,"","",13));
            dao_Curso.save(new Curso(-1,"ICSI - 422","INVESTIGACION DE OPERACIONES","07",4,"","",11));
            dao_Curso.save(new Curso(-1,"ICSI - 423","MEDIO AMB Y DESA SOSTENIBLE","07",2,"","",18));
            // Para la MATRICULA
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

        int idAlumno = prefs.getInt(UdepSharedPreferences.PREF_ID, -1);

        FichaMatriculaGridAdapter adapter = new FichaMatriculaGridAdapter(this, dao_Matricula.allCursosByAlumno(idAlumno));
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
