package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;

public class AsistenciaGridAdapter extends ArrayAdapter {

    public AsistenciaGridAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView (int position, @Nullable View converView, @NonNull ViewGroup parent){

        View view=converView;
        if(converView==null){
            view= LayoutInflater.from(this.getContext()).inflate(R.layout.grid_asistencia__item,parent,false);





        }

        return view;

    }


}
