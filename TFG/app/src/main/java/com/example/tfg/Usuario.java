package com.example.tfg;

public class Usuario {

    private String id;
    private String correo;
    private int intentos;
    private int aciertos;

    public Usuario(String id, String correo, int intentos, int aciertos) {
        this.id = id;
        this.correo = correo;
        this.intentos = intentos;
        this.aciertos = aciertos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return correo;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.correo = nombreUsuario;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }



}

