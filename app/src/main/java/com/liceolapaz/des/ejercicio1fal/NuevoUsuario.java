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


public class NuevoUsuario extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPass;
    private EditText txtEdad;
    private EditText txtNombreUsuario;
    private SQLiteDatabase db;
    private String stringIdioma;
    private String[] datos;
    private Button btAceptar;
    private Button btCancelar;
    private Button btEliminar;
    private Spinner spIdioma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_usuario);
       UsersSQLLiteOpen liteOpen = new UsersSQLLiteOpen(this, "UsuariosDDBB", null, 1);
        db = liteOpen.getWritableDatabase();
        btAceptar = (Button) findViewById(R.id.btAceptar);
        btCancelar = (Button) findViewById(R.id.btCancelar);
        btEliminar = (Button) findViewById(R.id.btEliminar);
        txtEmail = (EditText) findViewById(R.id.txtUser);
        txtPass = (EditText) findViewById(R.id.txtPass);
        spIdioma = (Spinner) findViewById(R.id.spIdioma);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        txtNombreUsuario = (EditText) findViewById(R.id.txtNombreUsuario);

        datos = new String[]{"Selecciona idioma:", "Español(ES)", "Gallego(GL)", "Inglés(EN)"};

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, datos);

        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spIdioma.setAdapter(adaptador);

        Bundle bundle = this.getIntent().getExtras();

        txtEmail.setText(bundle.getString("EMAIL"));
        txtPass.setText(bundle.getString("PASS"));

        if(bundle.getString("EMAIL") != null) {
            spIdioma.setSelection(obtenerPosicionSpinner(spIdioma, bundle.getString("IDIOMA")), true);
            txtEdad.setText(String.valueOf(bundle.getInt("EDAD")));
        }

        txtNombreUsuario.setText(bundle.getString("NOMBRE"));

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
            //    DialogoAceptar dialogo = new DialogoAceptar(ActivityDatosUsuarios.this);
             //   dialogo.show(fragmentManager, "tagConfirmacion");
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });

        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarUsuario();
            }
        });

        spIdioma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent,
                                   View v, int position, long id) {
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

    private int obtenerPosicionSpinner(Spinner spIdioma, String idioma) {

        datos = new String[]{"Selecciona idioma:", "Español(ES)", "Gallego(GL)", "Inglés(EN)"};
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
        ContentValues valoresRegistro = new ContentValues();
        valoresRegistro.put("email",txtEmail.getText().toString());
        valoresRegistro.put("password",txtPass.getText().toString());
        valoresRegistro.put("idioma", stringIdioma);
        valoresRegistro.put("edad",Integer.parseInt(txtEdad.getText().toString()));
        valoresRegistro.put("nombre",txtNombreUsuario.getText().toString());
        db.insert("usuarios",null,valoresRegistro);
        db.close();
    }
    private void eliminarUsuario() {
        db.delete("usuarios", "email=" + "'" + txtEmail.getText().toString() + "'", null);
        db.close();
    }
    private void cancelar() {
        Intent intent = new Intent(NuevoUsuario.this, ListaUsuarios.class);
        startActivity(intent);
    }



}
