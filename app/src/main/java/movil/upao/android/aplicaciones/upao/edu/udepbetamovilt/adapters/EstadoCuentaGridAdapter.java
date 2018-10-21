package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Cuenta;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;

public class EstadoCuentaGridAdapter extends ArrayAdapter {

    public EstadoCuentaGridAdapter(@NonNull Context context, List<Cuenta> cuentas) {
        super(context, R.layout.grid_estadocuenta_item, R.id.idtxtdescripcion_cuenta, cuentas);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(convertView== null)
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.grid_estadocuenta_item,parent,false);

        TextView txtdescripcion_cuenta = view.findViewById(R.id.idtxtdescripcion_cuenta);
        TextView txtfecha_cuenta = view.findViewById(R.id.idtxtfecha_cuenta);
        TextView txtmonto_cuenta = view.findViewById(R.id.idtxtmonto_cuenta);

        Cuenta cuenta = (Cuenta) getItem(position);

        txtdescripcion_cuenta.setText(cuenta.getDescripcion());
        txtfecha_cuenta.setText(cuenta.getFecha());
        txtmonto_cuenta.setText(Float.toString(cuenta.getMonto()));

        return view;
    }
}
