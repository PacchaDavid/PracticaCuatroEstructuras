package com.graph.controller.dao;

import com.graph.controller.dao.implement.AdapterDao;
import com.graph.controller.dao.implement.JsonFileManager;
import com.graph.controller.models.Veterinaria;

public class VeterinariaDao extends AdapterDao<Veterinaria> {
    public Veterinaria veterinaria;

    public VeterinariaDao() {
        super(Veterinaria.class);
    }

    public Veterinaria getVeterinaria() {
        if (this.veterinaria == null) {
            this.veterinaria = new Veterinaria();
        }

        return this.veterinaria;
    }

    public void setVeterinaria(Veterinaria veterinaria) {
        this.veterinaria = veterinaria;
    }

    public void veterinarioFromJson(String json) {
        this.veterinaria = this.g.fromJson(json, Veterinaria.class);
    }

    public Veterinaria[] getVeterinarias() {
        return this.getArray();
    }

    public Veterinaria getVeterinariaById(Integer id) throws Exception {
        return get(id);
    }

    public Veterinaria saveVeterinaria() throws Exception {
        Integer id = JsonFileManager.readAndUpdateCurrentIdOf(className);
        this.getVeterinaria().setId(id);
        saveInJsonGraph(this.veterinaria, Veterinaria.class);
        return persist(veterinaria);
    }

    public Veterinaria updateVeterinaria() throws Exception {
        Integer id = this.veterinaria.getId();
        return merge(veterinaria, id);
    }

    public Veterinaria deleteVeterinaria(Integer id) throws Exception {
        return remove(id);
    }

}