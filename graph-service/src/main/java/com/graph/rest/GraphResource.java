package com.graph.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.graph.controller.dao.implement.JsonFileManager;
import com.graph.controller.models.Mascota;
import com.graph.controller.models.Veterinaria;
import com.graph.controller.tda.graph.LabeledGraph;

@Path("/graph")
public class GraphResource {
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/get-as-js/{clase}")
    public String javaScriptGraph(@PathParam("clase") Integer clase) {
        LabeledGraph<Object> grafo;
        String respuesta;

        try {
            if (clase == 0) {
                grafo = JsonFileManager.graphFromJson(Mascota.class, true);    
            } else if (clase == 1) {
                grafo = JsonFileManager.graphFromJson(Veterinaria.class, true);
            } else {
                return "console.log('Bad Request')";
            }
            
            grafo.edgeAllVertices();
            respuesta = grafo.writeGraphAsJavaScriptCode();
        } catch (Exception e) {
            e.printStackTrace();
            respuesta = "console.log('Error en el servidor, 500')";
        }
        
        return respuesta;
    }

}
