package com.graph.controller.models;

import com.graph.controller.models.enumeration.TipoMascota;

public class Mascota {
    private Integer id;
    private String nombre;
    private String fechaNacimiento;
    private Float peso;
    private Float altura;
    private TipoMascota tipoMascota;

    public Mascota() {}

    public Mascota(Integer id, String nombre, String fechaNacimiento, Float peso, Float altura, TipoMascota tipoMascota) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.altura = altura;
        this.tipoMascota = tipoMascota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoMascota getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascota tipoMascota) {
        this.tipoMascota = tipoMascota;
    }
    
    public Float getPeso() {
        return peso;
    }
    
    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }


    @Override
    public String toString() {
        return this.nombre + " " + this.tipoMascota;
    }

}
