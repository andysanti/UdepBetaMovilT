package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.adapters;

import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.Query;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Notificacion;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.R;

public class NotificacionGridAdapter extends FirebaseListAdapter<Notificacion> implements Filterable {

    private NotificacionFilter notificacionFilter;
    private Query query;
    private FirebaseListOptions<Notificacion> options;

    public NotificacionGridAdapter(FirebaseListOptions<Notificacion> opt) {
        super(opt);
    }

    @Override
    protected void populateView(View v, Notificacion n, int position) {

        TextView txtTitulo       = v.findViewById(R.id.txtTituloNotif);
        TextView txtDescripcion  = v.findViewById(R.id.txtDescripcionNotif);
        ImageView img            = v.findViewById(R.id.iconNotif);

        txtTitulo.setText(n.getTitulo());
        txtDescripcion.setText(n.getDescripcion());
    }

    @Override
    public Filter getFilter() {
        if (notificacionFilter == null) {
            notificacionFilter = new NotificacionFilter();
        }
        return notificacionFilter;
    }

    private class NotificacionFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            Query query = null;
            if (constraint != null && constraint.length() > 0) {
                //query = FirebaseHelper.getCustomerRef().orderByChild("titulo").equalTo(constraint.toString());
            } else {
                //query = FirebaseHelper.getCustomerRef().orderByChild("titulo");
            }
            filterResults.values = query;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            query = (Query) results.values;
        }
    }
}