package com.graph.controller.dao;

import com.graph.controller.dao.implement.AdapterDao;
import com.graph.controller.dao.implement.JsonFileManager;
import com.graph.controller.models.Veterinaria;
import com.graph.controller.models.enumeration.Ciudad;
import com.graph.controller.tda.graph.LabeledGraph;
import com.graph.controller.tda.graph._interface.ModelEdge;

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

    public void addEdgeVeterinaria(Integer v1, Integer v2) throws Exception {
        LabeledGraph<Object> graph = JsonFileManager.graphFromJson(Veterinaria.class);
        
        ModelEdge<Object> edge = (Object o1, Object o2) -> {
            Veterinaria v1_ = (Veterinaria) o1;
            Veterinaria v2_ = (Veterinaria) o2;

            return calcularDistancia(v1_.getCiudad(), v2_.getCiudad());
        };

        Veterinaria _v1 = getVeterinariaById(v1);
        Veterinaria _v2 = getVeterinariaById(v2);

        graph.addEdge(v1, v2, edge.heuristicaVertices(_v1, _v2));
        JsonFileManager.saveFile(graph.graphToJson(), "Graph" + className);
    }   

    public float calcularDistancia(Ciudad ciudad1, Ciudad ciudad2) {
        // radio tierra
        final int R = 6371; 

        double lat1 = ciudad1.getLatitud();
        double lon1 = ciudad1.getLongitud();
        double lat2 = ciudad2.getLatitud();
        double lon2 = ciudad2.getLongitud();

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Kilometros
        double distance = R * c; 

        return (float) distance; 
    }

    public Veterinaria updateVeterinaria() throws Exception {
        Integer id = this.veterinaria.getId();
        return merge(veterinaria, id);
    }

    public Veterinaria deleteVeterinaria(Integer id) throws Exception {
        return remove(id);
    }

}