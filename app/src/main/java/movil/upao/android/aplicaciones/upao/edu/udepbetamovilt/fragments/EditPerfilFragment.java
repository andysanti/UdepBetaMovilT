package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Alumno;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.daos.DAOSQLAlumno;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.utils.UdepSharedPreferences;

public class EditPerfilFragment extends Fragment {
    private EditText etxt_email;
    private EditText etxt_clave;
    private EditText etxt_direccion;
    private EditText etxt_telefono;
    private TextView txt_nroCarne;
    private ImageView img_foto;

    private UdepSharedPreferences prefs;
    private DAOSQLAlumno dao_Alumno;

    //private int id_alumno;
    //private String carne_alumno;
    private Alumno alumno;

    public EditPerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new UdepSharedPreferences(getActivity()); // creo mis preferencias
        dao_Alumno = new DAOSQLAlumno(getActivity());

        int id_alumno =  prefs.getInt(UdepSharedPreferences.PREF_ID, -1);
        String carne_alumno =  prefs.getString(UdepSharedPreferences.PREF_USUARIO, "");

        alumno = dao_Alumno.getByCarne(carne_alumno);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_perfil, container, false);

        etxt_email = view.findViewById(R.id.idtxtmail);
        etxt_clave = view.findViewById(R.id.idtxtclave);
        etxt_direccion = view.findViewById(R.id.idtxtdireccion);
        etxt_telefono = view.findViewById(R.id.idtxttelefono);
        txt_nroCarne = view.findViewById(R.id.txt_nroCarne);
        img_foto = view.findViewById(R.id.img_foto);

        txt_nroCarne.setText(alumno.getNro_carne());
        new InicioFragment.GetImageFromURL(img_foto).execute(alumno.getFoto());
        //img_foto.

        setDatosEnInputs();

        FabSpeedDial fabSpeedDial = (FabSpeedDial)view.findViewById(R.id.fabSpeedDial_EditarPerfil);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true; // false: no muestra el menu
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {

                String mensaje = "";

                switch (menuItem.getItemId()) {
                    case R.id.perfil_save:
                        mensaje = "Los datos se han guardado exitosamente.";

                        alumno.setEmail(etxt_email.getText().toString());
                        alumno.setClave(etxt_clave.getText().toString());
                        alumno.setDireccion(etxt_direccion.getText().toString());
                        alumno.setTelefono(etxt_telefono.getText().toString());

                        dao_Alumno.save(alumno);
                        break;
                    case R.id.perfil_cancel:
                        mensaje = "Se ha cancelado la operación.";
                        break;
                }

                Toast.makeText(getActivity().getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();

                setDatosEnInputs();
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });

        return view;
    }

    private void setDatosEnInputs() {
        etxt_email.setText(alumno.getEmail());
        etxt_clave.setText(alumno.getClave());
        etxt_direccion.setText(alumno.getDireccion());
        etxt_telefono.setText(alumno.getTelefono());
    }
}
