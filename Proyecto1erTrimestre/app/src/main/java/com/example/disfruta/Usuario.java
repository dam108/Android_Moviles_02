package com.example.disfruta;

public class Usuario {
    String name, username, pass;
    int id_user;

    public Usuario(int id, String nombre, String login, String pass){
        this.id_user = id;
        this.name = nombre;
        this.username = login;
        this.pass = pass;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public String getLogin() {
        return username;
    }

    public void setLogin(String login) {
        this.username = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id_user;
    }

    public void setId(int id) {
        this.id_user = id;
    }


}
