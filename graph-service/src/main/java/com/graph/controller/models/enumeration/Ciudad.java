package com.graph.controller.models.enumeration;

public enum Ciudad {
    BOGOTA("Bogotá", 4.6097, -74.0817), 
    MEDELLIN("Medellín", 6.2442, -75.5812), 
    CALI("Cali", 3.4516, -76.5320);

    double latitud;
    double longitud;
    String nombre;

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public String getNombre() {
        return nombre;
    }

    Ciudad(String nombre, double latitud, double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
