package com.example.disfruta;

public class LineaDetalle {

    public int id;
    public String nombre;
    public int cantidad;
    public double precioUnidad;
    public double precioTotal;

    public LineaDetalle(int id, String nombre, int cantidad, double precio){
        this.id = id;
        this.nombre = nombre;
        this.precioUnidad = precio;
        this.cantidad = cantidad;
        this.precioTotal = this.precioUnidad * this.cantidad;
    }
}
