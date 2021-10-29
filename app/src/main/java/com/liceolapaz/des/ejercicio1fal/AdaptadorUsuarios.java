package com.liceolapaz.des.ejercicio1fal;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AdaptadorUsuarios extends RecyclerView.Adapter<AdaptadorUsuarios.UsuariosViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;
    private ArrayList<Usuarios>users;

    public static class UsuariosViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreUser;
        public UsuariosViewHolder(View itemView){
            super(itemView);
            nombreUser = (TextView) itemView.findViewById(R.id.nombreBBDD);
        }
        public void bindUsuario(Usuarios t){
                nombreUser.setText(t.getNombre());
        }
    }
    public AdaptadorUsuarios(ArrayList<Usuarios>users){
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
