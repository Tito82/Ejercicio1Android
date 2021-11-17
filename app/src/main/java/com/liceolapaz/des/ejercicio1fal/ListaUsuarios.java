package com.liceolapaz.des.ejercicio1fal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ListaUsuarios extends AppCompatActivity {
    private ArrayList<Usuario>users;
    private RecyclerView recyclerW;
    private Button buttonAgregar;
    private SQLiteDatabase db;
    private TextView numUsuarios;
    private AdaptadorUsuarios adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listausuarios);

        UsersSQLLiteOpen liteOpen = new UsersSQLLiteOpen(this, "UsuariosDDBB",null,1);

        db = liteOpen.getWritableDatabase();
        buttonAgregar = (Button) findViewById(R.id.buttonAgregar);
        users = new ArrayList<Usuario>();
        recyclerW = (RecyclerView) findViewById(R.id.recyclerW);
        recyclerW.setHasFixedSize(true);
        numUsuarios = (TextView) findViewById(R.id.NumUsuarios);
        adaptador = new AdaptadorUsuarios(users);
        adaptador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaUsuarios.this, NuevoUsuario.class);
                Bundle bundle = new Bundle();
                bundle.putString("NOMBRE",users.get(recyclerW.getChildAdapterPosition(view)).getNombreUsuario());
                bundle.putString("PASS", users.get(recyclerW.getChildAdapterPosition(view)).getPass());
                bundle.putString("EMAIL", users.get(recyclerW.getChildAdapterPosition(view)).getEmail());
                bundle.putInt("EDAD", users.get(recyclerW.getChildAdapterPosition(view)).getEdadUsuario());
                bundle.putString("IDIOMA", users.get(recyclerW.getChildAdapterPosition(view)).getIdiomaUsuario());/////////meter spiner
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        cargarUsuarios();
        recyclerW.setAdapter(adaptador);
        recyclerW.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerW.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerW.setItemAnimator(new DefaultItemAnimator());
        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaUsuarios.this, NuevoUsuario.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void cargarUsuarios(){ //ejercicio bbss3 jesus

        Cursor c = db.rawQuery("SELECT email,password,nombre,idioma,edad FROM usuarios",null);
        if (c.moveToFirst()){
            do{
                String email = c.getString(0);
                String pass = c.getString(1);
                String nom = c.getString(2);
                String id = c.getString(3);
                int ed = Integer.parseInt(c.getString(4));
                Usuario reg = new Usuario(email, pass, nom, id, ed);
                users.add(reg);
            }while (c.moveToNext());
        }
        numUsuarios.setText(String.valueOf(users.size()));
        db.close();

    }

}