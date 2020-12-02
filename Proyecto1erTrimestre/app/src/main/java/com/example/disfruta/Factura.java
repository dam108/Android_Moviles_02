package com.example.disfruta;

import java.util.ArrayList;

public class Factura {

    private ArrayList<LineaDetalle> lineas;
    private int id;
    private String nombre;
    private String direccion;
    private int cp;
    private String provincia;
    private String pais;
    private int telefono;

    public Factura(int id, String nombre, String calle, int num, int cp, String provincia, String pais, int telefono){
        this.id = id;
        this.nombre = nombre;
        this.direccion = calle + " Nº " + num;
        this.cp = cp;
        this.provincia = provincia;
        this.pais = pais;
        this.telefono = telefono;
        this.lineas = new ArrayList<LineaDetalle>();
    }

    public ArrayList<LineaDetalle> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<LineaDetalle> lineas) {
        this.lineas = lineas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getStringTelefono() {
        String stelefono = String.valueOf(telefono);
        return stelefono;
    }

    public String getStringPrecioTotal(){
        double resultado = 0.0;
        for (LineaDetalle linea: lineas) {
            resultado += linea.precioTotal;
        }
        resultado =  Math.round( resultado * 100.0) / 100.0;
        String sResultado = String.valueOf(resultado);
        return sResultado + "€";
    }
}
