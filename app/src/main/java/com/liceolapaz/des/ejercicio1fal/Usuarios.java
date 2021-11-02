package com.liceolapaz.des.ejercicio1fal;

public class Usuarios {
    private String nombre;
    private String idioma;
    private String edad;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Usuarios(String nombre, String idioma, String edad ) {
        this.nombre = nombre;
        this.idioma = idioma;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

}
