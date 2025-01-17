package com.graph.controller.dao;

import com.graph.controller.dao.implement.AdapterDao;
import com.graph.controller.dao.implement.JsonFileManager;
import com.graph.controller.models.Veterinaria;

public class VeterinariaDao extends AdapterDao<Veterinaria> {
    public Veterinaria veterinario;

    public VeterinariaDao() {
        super(Veterinaria.class);
    }

    public Veterinaria getVeterinario() {
        if (this.veterinario == null) {
            this.veterinario = new Veterinaria();
        }

        return this.veterinario;
    }

    public void setVeterinario(Veterinaria veterinario) {
        this.veterinario = veterinario;
    }

    public void veterinarioFromJson(String json) {
        this.veterinario = this.g.fromJson(json, Veterinaria.class);
    }

    public Veterinaria[] getVeterinarios() {
        return this.getArray();
    }

    public Veterinaria getVeterinarioById(Integer id) throws Exception {
        return get(id);
    }

    public Veterinaria saveVeterinario() throws Exception {
        Integer id = JsonFileManager.readAndUpdateCurrentIdOf(className);
        this.getVeterinario().setId(id);
        return persist(veterinario);
    }

    public Veterinaria updateVeterinario() throws Exception {
        Integer id = this.veterinario.getId();
        return merge(veterinario, id);
    }

    public Veterinaria deleteVeterinario(Integer id) throws Exception {
        return remove(id);
    }

    

}