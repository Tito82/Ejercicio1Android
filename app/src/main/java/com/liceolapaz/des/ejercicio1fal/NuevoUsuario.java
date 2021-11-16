package com.liceolapaz.des.ejercicio1fal;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class NuevoUsuario extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPass;
    private EditText txtEdad;
    private EditText txtNombreUsuario;
    private SQLiteDatabase db;
    private String stringIdioma;
    private String[] idiomas;
    private Button btAceptar;
    private Button btCancelar;
    private Button btEliminar;
    private Spinner spIdioma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_usuario);
        UsersSQLLiteOpen sqliteOpen = new UsersSQLLiteOpen(this, "UsuariosDDBB", null, 1);
        db = sqliteOpen.getWritableDatabase();
        btAceptar = (Button) findViewById(R.id.btAceptar);
        btCancelar = (Button) findViewById(R.id.btCancelar);
        btEliminar = (Button) findViewById(R.id.btEliminar);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPass = (EditText) findViewById(R.id.txtPass);
        spIdioma = (Spinner) findViewById(R.id.spIdioma);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        txtNombreUsuario = (EditText) findViewById(R.id.txtNombreUsuario);
        idiomas = new String[]{"Selecciona idioma:", "Español(ES)", "Gallego(GL)", "Inglés(EN)"};

        ArrayAdapter<String> adaptador =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, idiomas);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spIdioma.setAdapter(adaptador);

        Bundle bundle = this.getIntent().getExtras();

        txtEmail.setText(bundle.getString("EMAIL"));
        txtPass.setText(bundle.getString("PASS"));
        if(bundle.getString("EMAIL") != null) {
        spIdioma.setSelection(seleccionSpinner(spIdioma, bundle.getString("IDIOMA")), true);
        txtEdad.setText(String.valueOf(bundle.getInt("EDAD")));}
        txtNombreUsuario.setText(bundle.getString("NOMBRE"));
        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
               FragmentManager fragmentManager = getSupportFragmentManager();
               DialogoAceptar dialogo = new DialogoAceptar(NuevoUsuario.this);
               dialogo.show(fragmentManager, "tagConfirmacion");
            }


        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });

        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarUsuario();
            }
        });

        spIdioma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//ejercicio spinner jesus
        public void onItemSelected(AdapterView<?> parent,
                                   View view, int position, long id) {
            if(parent.getItemAtPosition(position).toString() == "Español(ES)"){
                stringIdioma = "ES"; }
            if(parent.getItemAtPosition(position).toString() == "Gallego(GL)"){
                stringIdioma = "GL"; }
            if(parent.getItemAtPosition(position).toString() == "Inglés(EN)"){
                stringIdioma = "EN"; }
        }
        public void onNothingSelected(AdapterView<?> parent) { }
         });
    }

    private int seleccionSpinner(Spinner spIdioma, String idioma) {//ejercicio spinner jesus

        idiomas = new String[]{"Selecciona idioma:", "Español(ES)", "Gallego(GL)", "Inglés(EN)"};
        int posicion = 0;
        if(idioma.equals("ES")){
            posicion = 1;
        }
        if(idioma.equals("GL")){
            posicion = 2;
        }
        if(idioma.equals("EN")){
            posicion = 3;
        }
        return posicion;
    }

    protected void anadirUsuario() {
        ContentValues datosNuevos = new ContentValues();
        datosNuevos.put("email",txtEmail.getText().toString());
        datosNuevos.put("password",txtPass.getText().toString());
        datosNuevos.put("idioma", stringIdioma);
        datosNuevos.put("edad",Integer.parseInt(txtEdad.getText().toString()));
        datosNuevos.put("nombre",txtNombreUsuario.getText().toString());
        db.insert("usuarios",null,datosNuevos);
        //db.close();
        Intent intent = new Intent(NuevoUsuario.this, ListaUsuarios.class);
        startActivity(intent);

    }
    private void eliminarUsuario() {
        db.delete("usuarios", "email=" + "'" + txtEmail.getText().toString() + "'", null);
        //db.close();
        Intent intent = new Intent(NuevoUsuario.this, ListaUsuarios.class);
        startActivity(intent);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void editarUsuario(){
        ContentValues  datosEditados= new ContentValues();
        datosEditados.put("email",txtEmail.getText().toString());
        datosEditados.put("password",txtPass.getText().toString());
        datosEditados.put("idioma", stringIdioma);
        datosEditados.put("edad",Integer.parseInt(txtEdad.getText().toString()));
        datosEditados.put("nombre",txtNombreUsuario.getText().toString());
        //db.update("usuarios",null,datosEditados);
        db.update("Usuarios", datosEditados, "email='" + txtEmail.getText().toString() +"'", null);
        //db.close();
        Intent intent = new Intent(NuevoUsuario.this, ListaUsuarios.class);
        startActivity(intent);
    }
    public void guardarOActualizar(){
       if(txtEmail.getText()==null){
            anadirUsuario();
        }else{
            editarUsuario();
        }
    }

    private void cancelar() {
        Intent intent = new Intent(NuevoUsuario.this, ListaUsuarios.class);
        startActivity(intent);
    }
}
