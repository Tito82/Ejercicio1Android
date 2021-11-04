package com.liceolapaz.des.ejercicio1fal;

public class Usuario {

    private String email;
    private String NombreUsuario;
    private String IdiomaUsuario;
    private int EdadUsuario;
    private String pass;

    public Usuario(String email, String pass, String NombreUsuario, String IdiomaUsuario, int EdadUsuario) {
        this.email = email;
        this.pass = pass;
        this.NombreUsuario = NombreUsuario;
        this.IdiomaUsuario = IdiomaUsuario;
        this.EdadUsuario = EdadUsuario;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public String getIdiomaUsuario() {
        return IdiomaUsuario;
    }

    public int getEdadUsuario() {
        return EdadUsuario;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }
}

