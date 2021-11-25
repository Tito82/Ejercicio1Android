package com.liceolapaz.des.ejercicio1fal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


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
    private Toolbar toolbarUsuarios;
    private boolean existeUsuario = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_usuario);
        UsersSQLLiteOpen sqliteOpen = new UsersSQLLiteOpen(this, "UsuariosDDBB", null, 1);
        db = sqliteOpen.getWritableDatabase();
        toolbarUsuarios = (Toolbar) findViewById(R.id.toolbarUsuarios);
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
        if(bundle.getString("EMAIL") == null) {
        toolbarUsuarios.setTitle("Nuevo Usuario");
        btEliminar.setVisibility(View.GONE);
        }
        txtEmail.setText(bundle.getString("EMAIL"));
        txtPass.setText(bundle.getString("PASS"));
        txtNombreUsuario.setText(bundle.getString("NOMBRE"));
        if(bundle.getString("EMAIL") != null) {
        existeUsuario = true;
        txtEmail.setEnabled(false);
        toolbarUsuarios.setTitle(txtNombreUsuario.getText() + " ( " + txtEmail.getText() + ")");
        spIdioma.setSelection(seleccionSpinner(spIdioma, bundle.getString("IDIOMA")), true);
        stringIdioma = bundle.getString("IDIOMA");
        txtEdad.setText(String.valueOf(bundle.getInt("EDAD")));
        }
        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                if(txtEmail.getText().toString().isEmpty() || txtPass.getText().toString().isEmpty()
                        || spIdioma.getSelectedItemPosition() == 0 || txtEdad.getText().toString().isEmpty() ||
                        txtNombreUsuario.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                }else {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    DialogoAceptar dialogo = new DialogoAceptar(NuevoUsuario.this);
                    dialogo.show(fragmentManager, "tagConfirmacion");
                }
            }
        });
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoCancelar cancelar = new DialogoCancelar(NuevoUsuario.this);
                cancelar.show(fragmentManager, "tagConfirmacion");
            }
        });
        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoEliminar eliminar = new DialogoEliminar(NuevoUsuario.this);
                eliminar.show(fragmentManager, "tagConfirmacion");
            }
        });
/////////////////////////////////////////////coger el item que esta seleccionado
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
        Intent intent = new Intent(NuevoUsuario.this, ListaUsuarios.class);
        startActivity(intent);
    }
    public void eliminarUsuario() {
        db.delete("usuarios", "email=" + "'" + txtEmail.getText().toString() + "'", null);
        Intent intent = new Intent(NuevoUsuario.this, ListaUsuarios.class);
        startActivity(intent);
    }
   public void editarUsuario(){
        ContentValues  datosEditados= new ContentValues();
        datosEditados.put("email",txtEmail.getText().toString());
        datosEditados.put("password",txtPass.getText().toString());
        datosEditados.put("idioma", stringIdioma);
        datosEditados.put("edad",Integer.parseInt(txtEdad.getText().toString()));
        datosEditados.put("nombre",txtNombreUsuario.getText().toString());
        db.update("Usuarios", datosEditados, "email='" + txtEmail.getText().toString() +"'", null);
        Intent intent = new Intent(NuevoUsuario.this, ListaUsuarios.class);
        startActivity(intent);
    }
    public void cancelar() {
        Intent intent = new Intent(NuevoUsuario.this, ListaUsuarios.class);
        startActivity(intent);
    }
    public boolean isExisteUsuario() {
        return existeUsuario;
    }
    public void setExisteUsuario(boolean existeUsuario) {
        this.existeUsuario = existeUsuario;
    }
}
