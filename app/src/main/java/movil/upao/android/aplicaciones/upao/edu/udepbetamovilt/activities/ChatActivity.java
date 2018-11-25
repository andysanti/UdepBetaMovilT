package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Alumno;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.ChatMessage;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.daos.DAOSQLAlumno;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.utils.UdepSharedPreferences;

public class ChatActivity extends AppCompatActivity {
    private UdepSharedPreferences prefs;

    private FirebaseDatabase database;
    private DatabaseReference mensajesRef;
    private DatabaseReference mensajesActualRef;
    private ListAdapter adapter;
    private FirebaseListOptions<ChatMessage> options;
    private EditText txtMensaje;
    private ListView lstMensajes;

    private DAOSQLAlumno dao_Alumno;
    private String ActualUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //configurar el botón Back (arriba)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prefs = new UdepSharedPreferences(this); // creo mis preferencias
        ActualUser = prefs.getString(UdepSharedPreferences.PREF_USUARIO, null);

        database = FirebaseDatabase.getInstance();
        mensajesRef = database.getReference("mensajes");

        txtMensaje = findViewById(R.id.txtMensaje);
        lstMensajes = findViewById(R.id.lstMensajes);

        dao_Alumno = new DAOSQLAlumno(this);
        /*
        int ultimo = dao_Alumno.all().size() - 1;
        Alumno alumno = dao_Alumno.all().get( ultimo );

        mensajesActualRef = mensajesRef.push(); // GENERO UN ID
        mensajesActualRef.setValue(
                new ChatMessage(
                        mensajesActualRef.getKey(), alumno.getNombres(),
                        "Buenas ingeniero. Puedo hablar con Ud para seguir corrigiendo mi tesis.",
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()),
                        true ));

        mensajesActualRef = mensajesRef.push(); // GENERO UN ID
        mensajesActualRef.setValue(
                new ChatMessage(
                        mensajesActualRef.getKey(), "Mario Gonzales Orejuela",
                        "Buenas tardes, yo tambien queria ver ese tema.",
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()),
                        false ));

        mensajesActualRef = mensajesRef.push(); // GENERO UN ID
        mensajesActualRef.setValue(
                new ChatMessage(
                        mensajesActualRef.getKey(), "Luis Miguel Carrera Neyra",
                        "Y yo.",
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()),
                        false ));
        */
        filtrarListMensajes(null);
    }

    private void filtrarListMensajes(String squery) {
        final Context ctx = this;

        Query query = mensajesRef;

//        if(squery!=null)
//            query = mensajesRef.orderByChild("tipo").equalTo(squery);

        options =
                new FirebaseListOptions.Builder<ChatMessage>()
                        .setQuery(query, ChatMessage.class)
                        .setLayout(R.layout.messages)
                        .setLifecycleOwner(this)
                        .build();

        adapter = new FirebaseListAdapter<ChatMessage>(options) {


            @Override
            protected void populateView(View v, ChatMessage message, int i) {
                MessageViewHolder holder = new MessageViewHolder();

                RelativeLayout my_layout    = v.findViewById(R.id.rl_my_message);
                RelativeLayout their_layout = v.findViewById(R.id.rl_their_message);

                if (message.getUser().equals(ActualUser)) { //message.isBelongsToCurrentUser()
                    // this message was sent by us so let's create a basic chat bubble on the right

                    holder.messageBody = (TextView) v.findViewById(R.id.my_message_body);
                    v.setTag(holder);
                    holder.messageBody.setText(message.getMessage());

                    my_layout.setVisibility(View.VISIBLE);
                    their_layout.setVisibility(View.GONE);
                }
                else {
                    // this message was sent by someone else so let's create an advanced chat bubble on the left

                    holder.avatar = (View) v.findViewById(R.id.avatar);
                    holder.name = (TextView) v.findViewById(R.id.name);
                    holder.messageBody = (TextView) v.findViewById(R.id.their_message_body);
                    v.setTag(holder);

                    holder.name.setText(message.getName());
                    holder.messageBody.setText(message.getMessage());
                    GradientDrawable drawable = (GradientDrawable) holder.avatar.getBackground();
                    drawable.setColor(Color.parseColor(message.getColor()));

                    my_layout.setVisibility(View.GONE);
                    their_layout.setVisibility(View.VISIBLE);
                }
                notifyDataSetChanged();
            }
        };

        lstMensajes.setAdapter(adapter);
    }

    class MessageViewHolder {
        public View avatar;
        public TextView name;
        public TextView messageBody;
    }

    public void sendMessage(View view) {
        String mensaje = txtMensaje.getText().toString();

        if (mensaje.length() > 0) {
            /*
            ChatMessage oChat = new ChatMessage();
            oChat.setUser(prefs.getString(ChatkiSharedPreferences.PREF_USUARIO, null));
            oChat.setName(prefs.getString(ChatkiSharedPreferences.PREF_NOMBRE_USUARIO, null));
            oChat.setMessage(mensaje);
            oChat.setTime(String.valueOf(System.currentTimeMillis()));
            oChat.setBelongsToCurrentUser(true);
            */

            mensajesActualRef = mensajesRef.push(); // GENERO UN ID
            mensajesActualRef.setValue(
                    new ChatMessage(
                            ActualUser, //mensajesActualRef.getKey(),
                            prefs.getString(UdepSharedPreferences.PREF_NOMBRES, null),
                            mensaje,
                            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())
                    ));

            txtMensaje.getText().clear();
        }
        //else
        //Toast.makeText(this, "Escriba un mensaje", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
