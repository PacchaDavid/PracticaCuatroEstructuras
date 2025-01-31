package com.graph.controller.dao.services;

import com.graph.controller.dao.VeterinariaDao;
import com.graph.controller.models.Veterinaria;

public class VeterinariaServices {
    private VeterinariaDao obj;

    public VeterinariaServices() {
        this.obj = new VeterinariaDao();
    }

    public Veterinaria getVeterinaria() {
        return this.obj.getVeterinaria();
    }

    public Veterinaria[] getVeterinarias() {
        return this.obj.getVeterinarias();
    }

    public Veterinaria getVeterinariaById(Integer id) throws Exception {
        return this.obj.getVeterinariaById(id);
    }

    public Veterinaria saveVeterinaria(String json) throws Exception {
        this.obj.veterinarioFromJson(json);
        return this.obj.saveVeterinaria();
    }

    public Veterinaria updateVeterinaria(String json) throws Exception {
        this.obj.veterinarioFromJson(json);
        return this.obj.updateVeterinaria();
    }

    public Veterinaria deleteVeterinaria(Integer id) throws Exception {
        return this.obj.deleteVeterinaria(id);
    }

    public void addEdgeVeterinaria(Integer v1, Integer v2) throws Exception {
        this.obj.addEdgeVeterinaria(v1, v2);
    }

    // BUSQUEDA Y ORDENACIÓN ==========================================================

   /*  public Veterinaria[] sort(String attribute, Integer orden, Integer method) throws Exception {
        return this.obj.sort(attribute,orden,method);
    }
    
    public Veterinaria[] search(String attribute, String x) throws Exception {
        return this.obj.search(attribute, x);
    } */
}
