package com.liceolapaz.des.ejercicio1fal;

public class Usuario {

    private String email;
    private String NombreUsuario;
    private String IdiomaUsuario;
    private int EdadUsuario;
    private String pass;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        NombreUsuario = nombreUsuario;
    }

    public String getIdiomaUsuario() {
        return IdiomaUsuario;
    }

    public void setIdiomaUsuario(String idiomaUsuario) {
        IdiomaUsuario = idiomaUsuario;
    }

    public int getEdadUsuario() {
        return EdadUsuario;
    }

    public void setEdadUsuario(int edadUsuario) {
        EdadUsuario = edadUsuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Usuario(String email, String pass, String NombreUsuario, String IdiomaUsuario, int EdadUsuario) {
        this.email = email;
        this.pass = pass;
        this.NombreUsuario = NombreUsuario;
        this.IdiomaUsuario = IdiomaUsuario;
        this.EdadUsuario = EdadUsuario;
    }

}

