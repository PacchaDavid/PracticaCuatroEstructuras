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

    public void addEdgeMascota(Integer v1, Integer v2) throws Exception {
        LabeledGraph<Object> graph = JsonFileManager.graphFromJson(Mascota.class);
        
        ModelEdge<Object> edge = (Object o1, Object o2) -> {
            Mascota mascota1 = (Mascota)o1;
            Mascota mascota2 = (Mascota)o2;

            final boolean mismaEspecie = mascota1.getTipoMascota().equals(mascota2.getTipoMascota());

            if (mismaEspecie) {
                return 1.0f;
            } else {
                return 100000.0f;
            }
        };

        Mascota mascota1 = (Mascota)graph.getLabelOfVertice(v1);
        Mascota mascota2 = (Mascota)graph.getLabelOfVertice(v2);

        graph.addEdge(v1, v2, edge.heuristicaVertices(mascota1, mascota2));

        System.out.println(graph);
        JsonFileManager.saveFile(graph.graphToJson(), "Graph" + className);
    }

    public Mascota updateMascota() throws Exception {
        Integer id = this.mascota.getId();
        return merge(mascota, id);
    }

    public Mascota deleteMascota(Integer id) throws Exception {
        return remove(id);
    }

}