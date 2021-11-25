package com.liceolapaz.des.ejercicio1fal;

public class Usuario {

    private String email;
    private String nombreUsuario;
    private String idiomaUsuario;
    private int edadUsuario;
    private String pass;
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getNombreUsuario() {return nombreUsuario;}
    public void setNombreUsuario(String nombreUsuario) {this.nombreUsuario = nombreUsuario;}
    public String getIdiomaUsuario() {return idiomaUsuario;}
    public void setIdiomaUsuario(String idiomaUsuario) {this.idiomaUsuario = idiomaUsuario;}
    public int getEdadUsuario() {return edadUsuario;}
    public void setEdadUsuario(int edadUsuario) {this.edadUsuario = edadUsuario;}
    public String getPass() {return pass;}
    public void setPass(String pass) {this.pass = pass;}
    public Usuario(String email, String pass, String nombreUsuario, String idiomaUsuario, int edadUsuario) {
        this.email = email;
        this.pass = pass;
        this.nombreUsuario = nombreUsuario;
        this.idiomaUsuario = idiomaUsuario;
        this.edadUsuario = edadUsuario;
    }

}

