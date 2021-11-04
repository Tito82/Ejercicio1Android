package com.liceolapaz.des.ejercicio1fal;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AdaptadorUsuarios extends RecyclerView.Adapter<AdaptadorUsuarios.UsuariosViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;
    private ArrayList<Usuarios>users;

    public static class UsuariosViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNombre;
        private TextView txtIdioma;
        private TextView txtEdad;
        public UsuariosViewHolder(View itemView){
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.nombre);
            txtIdioma = (TextView) itemView.findViewById(R.id.idioma);
            txtEdad =  (TextView) itemView.findViewById(R.id.edad);

        }
        public void bindUsuario(Usuarios t){
            txtNombre.setText(t.getNombre());
            txtIdioma.setText(t.getIdioma());
            txtEdad.setText( "Edad:"+ t.getEdad());

        }
    }
    public AdaptadorUsuarios(ArrayList<Usuario> users){
        this.users = users;
    }

    @Override
    public UsuariosViewHolder onCreateViewHolder( ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view, viewGroup,false);
        itemView.setOnClickListener(this);
        UsuariosViewHolder vwHold = new UsuariosViewHolder(itemView);
    return vwHold;
    }

    @Override
    public void onBindViewHolder(UsuariosViewHolder viewHolder, int position) {
        Usuarios item = users.get(position);
        viewHolder.bindUsuario(item);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

}
