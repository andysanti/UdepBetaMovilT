package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities.LoginActivity;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities.MainActivity;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities.ReporteNotasActivity;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments.EditPerfilFragment;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments.InicioFragment;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments.PerfilFragment;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.utils.UdepSharedPreferences;

public class PrincipalActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private UdepSharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        prefs = new UdepSharedPreferences(this); // creo mis preferencias
        agregarToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));
        }
    }

    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {
            case R.id.item_inicio:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.item_cuenta:
                // Fragmento para la sección Cuenta
                fragmentoGenerico = new InicioFragment();
                break;
            case R.id.item_editarcuenta:
                // Fragmento para la sección Editar Cuenta
                fragmentoGenerico = new EditPerfilFragment();
                break;
            case R.id.item_categorias:
                //  sección cerrar sesión
                cerrarSesion();
                break;
        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }

        // Setear título actual
        setTitle(itemDrawer.getTitle());
    }

    public void cerrarSesion(){
        this.prefs.putString(UdepSharedPreferences.PREF_USUARIO, null);
        this.prefs.putInt(UdepSharedPreferences.PREF_ID, 0);
        this.prefs.putString(UdepSharedPreferences.PREF_NOMBRES, null);
        this.prefs.putString(UdepSharedPreferences.PREF_PATH_PHOTO, null);
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        this.finish();
    }
    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarprincipal);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
