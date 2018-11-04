package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments.HomeFragment;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments.NotificacionFragment;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments.PerfilFragment;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.utils.UdepSharedPreferences;

public class MainActivity extends AppCompatActivity {
    private UdepSharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = new UdepSharedPreferences(this); // creo mis preferencias

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

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
                            selectedFragment = new PerfilFragment();
                            break;
                        case R.id.nav_notificaciones:
                            selectedFragment = new NotificacionFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null)
                            .commit();

                    return true;
                }
            };

}
