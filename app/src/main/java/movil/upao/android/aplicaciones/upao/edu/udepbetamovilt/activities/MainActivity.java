package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Alumno;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.PrincipalActivity;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.daos.DAOSQLAlumno;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments.HomeFragment;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments.PerfilFragment;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.utils.UdepSharedPreferences;

public class MainActivity extends AppCompatActivity {
    private UdepSharedPreferences prefs;
    private DAOSQLAlumno dao_Alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = new UdepSharedPreferences(this); // creo mis preferencias

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        dao_Alumno = new DAOSQLAlumno(this);

        if(dao_Alumno.all().size()==0) {
            dao_Alumno.save(new Alumno(-1, "000155085","71716463","Victor", "Ramirez Dominguez", "Piura", "961244567", "victor@udep.edu.pe", "M", "https://sigadocentes.udep.edu.pe/fotossiga/persona/10173455_101d322.jpg"));
            dao_Alumno.save(new Alumno(-1, "000154674","45125744","Andy", "Santi Almeida", "Piura", "961353411", "andy@udep.edu.pe", "M", "https://sigadocentes.udep.edu.pe/fotossiga/persona/1016398_a866d43.jpg"));
            dao_Alumno.save(new Alumno(-1, "000155260","03883798","Oscar Ricardo", "Navarro Mendoza", "Sullana", "959047742", "onavarrom1@upao.edu.pe", "M", "https://scontent.ftru3-1.fna.fbcdn.net/v/t1.0-9/45159244_1017730965101788_4371484848502603776_n.jpg?_nc_cat=102&_nc_eui2=AeEtZHXTP28cDLtbormBHlNGoxAl48p6gDJX2kVGf9kphL6eNp08CFg5xzuur_gd_03PeH9ZaPprS2xwiDn6EFxZuNG9LnvhH33k_nYEGvYrXA&_nc_ht=scontent.ftru3-1.fna&oh=8f35a9f95599694a776db52a1bc6032b&oe=5C72FFE6"));
            // dao_Alumno.save(new Alumno(-1, "000155085","https://sigadocentes.udep.edu.pe/fotossiga/persona/10173455_101d322.jpg","71716463","Victor", "Ramirez Dominguez", "Piura", "961244567", "victor@udep.edu.pe", "M"));
           // dao_Alumno.save(new Alumno(-1, "000154674","https://sigadocentes.udep.edu.pe/fotossiga/persona/1016398_a866d43.jpg","45125744","Andy", "Santi Almeida", "Piura", "961353411", "andy@udep.edu.pe", "M"));
        }

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String usuario = prefs.getString(UdepSharedPreferences.PREF_USUARIO, null);
        if(usuario == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_perfil:
                            Nav_Perfil();
                            break;
                        case R.id.nav_notificaciones:
                            Nav_Notificaciones();
                            break;
                    }

                    if(selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null)
                                .commit();
                    }
                    return true;
                }
            };

    public void Nav_Notificaciones(){
        Intent intent = new Intent(this, NotificacionesActivity.class);
        startActivity(intent);
    }
    public void Nav_Perfil(){
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }

}
