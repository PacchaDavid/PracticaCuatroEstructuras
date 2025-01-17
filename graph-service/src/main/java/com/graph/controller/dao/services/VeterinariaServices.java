package com.graph.controller.dao.services;

import com.graph.controller.dao.VeterinariaDao;
import com.graph.controller.models.Veterinaria;

public class VeterinariaServices {
    private VeterinariaDao obj;

    public VeterinariaServices() {
        this.obj = new VeterinariaDao();
    }

    public Veterinaria getVeterinario() {
        return this.obj.getVeterinario();
    }

    public Veterinaria[] getVeterinarios() {
        return this.obj.getVeterinarios();
    }

    public Veterinaria getVeterinarioById(Integer id) throws Exception {
        return this.obj.getVeterinarioById(id);
    }

    public Veterinaria saveVeterinario(String json) throws Exception {
        this.obj.veterinarioFromJson(json);
        return this.obj.saveVeterinario();
    }

    public Veterinaria updateVeterinario(String json) throws Exception {
        this.obj.veterinarioFromJson(json);
        return this.obj.updateVeterinario();
    }

    public Veterinaria deleteVeterinario(Integer id) throws Exception {
        return this.obj.deleteVeterinario(id);
    }

    // BUSQUEDA Y ORDENACIÓN ==========================================================

   /*  public Veterinario[] sort(String attribute, Integer orden, Integer method) throws Exception {
        return this.obj.sort(attribute,orden,method);
    }
    
    public Veterinario[] search(String attribute, String x) throws Exception {
        return this.obj.search(attribute, x);
    } */
}
