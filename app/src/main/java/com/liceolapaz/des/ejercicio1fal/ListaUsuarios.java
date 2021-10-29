package com.liceolapaz.des.ejercicio1fal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import java.util.ArrayList;

public class ListaUsuarios extends AppCompatActivity {
    private ArrayList<Usuarios>users;
    private RecyclerView recyclerW;
    private Button agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listausuarios);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
*/
        users = new ArrayList<Usuarios>();
        for (int i = 1; i<15; i++)// pasar en i el numero de usuarios de la bbdd
        users.add(new Usuarios("nombre:" + i ));
        recyclerW=(RecyclerView) findViewById(R.id.recyclerW);
        recyclerW.setHasFixedSize(true);
        final AdaptadorUsuarios adaptUsers = new AdaptadorUsuarios(users);

        adaptUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("RecyclerView", "Pulsado" + recyclerW.getChildAdapterPosition(view));
            }
        });
        recyclerW.setAdapter(adaptUsers);
        recyclerW.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerW.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerW.setItemAnimator(new DefaultItemAnimator());
    }

}