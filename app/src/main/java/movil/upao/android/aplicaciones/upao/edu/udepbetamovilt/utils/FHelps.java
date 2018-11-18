package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class FHelps {

    public FHelps() {
    }

    public void f_showMessage(Context context, String oMessage, String oTitle){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle( oTitle );
        builder.setMessage( oMessage );
        builder.setPositiveButton(android.R.string.ok, null);
        //builder.setNegativeButton(android.R.string.cancel, null);
        Dialog dialog = builder.create();
        dialog.show();
    }
}
