package com.graph.controller.dao;

import com.graph.controller.dao.implement.AdapterDao;
import com.graph.controller.dao.implement.JsonFileManager;
import com.graph.controller.models.Mascota;
import com.graph.controller.tda.graph.LabeledGraph;
import com.graph.controller.tda.graph._interface.ModelEdge;

public class MascotaDao extends AdapterDao<Mascota> {
    public Mascota mascota;

    public MascotaDao() {
        super(Mascota.class);
    }

    public Mascota getMascota() {
        if (this.mascota == null) {
            this.mascota = new Mascota();
        }

        return this.mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public void mascotaFromJson(String json) {
        this.mascota = this.g.fromJson(json, Mascota.class);
    }

    public Mascota[] getMascotas() {
        return this.getArray();
    }

    public Mascota getMascotaById(Integer id) throws Exception {
        return get(id);
    }

    public Mascota saveMascota() throws Exception {
        Integer id = JsonFileManager.readAndUpdateCurrentIdOf(className);
        this.getMascota().setId(id);
        saveInJsonGraph(this.mascota, Mascota.class);
        return persist(mascota);
    }

    public LabeledGraph<Object> edgeVerticesOfGraphMascota() throws Exception {
        LabeledGraph<Object> labeledGraph = JsonFileManager.graphFromJson(getMascota().getClass());
        labeledGraph.edgeAll(new ModelEdge<Object>() {
            @Override
            public float heuristicaVertices(Object o1, Object o2) {
                if ( ((Mascota)o1).getTipoMascota().equals( ((Mascota)o2).getTipoMascota() )) {
                    return 1.11f;
                } 
                return 10f;
            }
        });
        return labeledGraph;
    }

    public Mascota updateMascota() throws Exception {
        Integer id = this.mascota.getId();
        return merge(mascota, id);
    }

    public Mascota deleteMascota(Integer id) throws Exception {
        return remove(id);
    }

}