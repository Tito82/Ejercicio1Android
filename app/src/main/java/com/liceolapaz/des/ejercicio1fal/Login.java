package com.liceolapaz.des.ejercicio1fal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private Button btLogin;
    private TextView lblUsuario;
    private EditText txtUsuario;
    private TextView lblPass;
    private EditText txtPass;
    private TextView lblError;
    int intentos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btLogin = (Button) findViewById(R.id.buttonLogin);
        lblError = (TextView) findViewById(R.id.mensajeError);
        txtUsuario = (EditText) findViewById(R.id.editUser);
        txtPass = (EditText) findViewById(R.id.editPass);

        btLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String usuario = txtUsuario.getText().toString();
                String password = txtPass.getText().toString();

                if ((usuario.equals("admin") && password.equals("liceo"))) {
                    Intent intent = new Intent(Login.this, ListaUsuarios.class);
                    startActivity(intent);
                    finish();
                } else {
                    lblError.setVisibility(View.VISIBLE);
                    intentos++;
                }
                if (intentos == 3) {
                    System.exit(0);
                }
            }

        });
    }
}