package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.firebaseServices;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.content.ContentValues.TAG;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    public static final String TAG = "NOTICIAS";

    // Se ejecuta cuando nos asignan un token o se actualiza
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token: " + token);
        Toast.makeText( this, "Token: " + token, Toast.LENGTH_LONG).show();
    }


}
