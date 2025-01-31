package com.graph.controller.dao.services;

import java.util.HashMap;

import com.graph.controller.dao.MascotaDao;
import com.graph.controller.models.Mascota;
import com.graph.controller.models.enumeration.SexoBiologico;
import com.graph.controller.models.enumeration.TipoMascota;


public class MascotaServices {
    private MascotaDao obj;

    public MascotaServices() {
        this.obj = new MascotaDao();
    }

    public Mascota getMascota() {
        return this.obj.getMascota();
    }

    public Mascota[] getMascotas() {
        return this.obj.getMascotas();
    }

    public Mascota getMascotaById(Integer id) throws Exception {
        return this.obj.getMascotaById(id);
    }

    public Mascota saveMascota(String json) throws Exception {
        this.obj.mascotaFromJson(json);
        return this.obj.saveMascota();
    }

    public Mascota updateMascota(String json) throws Exception {
        this.obj.mascotaFromJson(json);
        return this.obj.updateMascota();
    }

    public Mascota deleteMascota(Integer id) throws Exception {
        return this.obj.deleteMascota(id);
    }

    public TipoMascota[] tiposMascota() {
        return TipoMascota.values();
    }

    public void addEdgeMascota(Integer v1, Integer v2) throws Exception {
        this.obj.addEdgeMascota(v1, v2);
    }

    public HashMap<String, Object> enumerations() throws Exception {
        HashMap<String, Object> enumerations = new HashMap<>();
        enumerations.put("tipoMascota", TipoMascota.values());
        enumerations.put("sexoBiologico", SexoBiologico.values());
        return enumerations;
    }

    // BUSQUEDA Y ORDENACIÓN ==========================================================

   /*  public Mascota[] sort(String attribute, Integer orden, Integer method) throws Exception {
        return this.obj.sort(attribute,orden,method);
    }
    
    public Mascota[] search(String attribute, String x) throws Exception {
        return this.obj.search(attribute, x);
    } */
}
