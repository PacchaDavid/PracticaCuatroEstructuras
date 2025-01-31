package com.graph.rest;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.graph.controller.dao.implement.JsonFileManager;
import com.graph.controller.dao.services.MascotaServices;
import com.graph.controller.dao.services.VeterinariaServices;
import com.graph.controller.models.Mascota;
import com.graph.controller.models.Veterinaria;
import com.graph.controller.tda.graph.LabeledGraph;
import com.graph.rest.response.ResponseFactory;

@Path("/graph")
public class GraphResource {
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/get-as-js/{clase}/{randomic}")
    public String javaScriptGraph(@PathParam("clase") Integer clase, @PathParam("randomic") Boolean randomic) {
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

            if (randomic) {
                grafo.edgeAllVertices();
            }
            
            respuesta = grafo.writeGraphAsJavaScriptCode();
        } catch (Exception e) {
            e.printStackTrace();
            respuesta = "console.log('Error en el servidor, 500')";
        }
        
        return respuesta;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get-vertices/{clase}")
    public Response getVertices(@PathParam("clase") Integer clase) {
        LabeledGraph<Object> grafo;

        try {
            if (clase == 0) {
                grafo = JsonFileManager.graphFromJson(Mascota.class, true);    
            } else if (clase == 1) {
                grafo = JsonFileManager.graphFromJson(Veterinaria.class, true);
            } else {
                return ResponseFactory.buildResponseWithException(new Exception("Bad Request"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseFactory.buildResponseWithException(e);
        }
        
        return ResponseFactory.buildResponse(grafo.getDictVertices());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add-adjacency/{clase}/{v1}/{v2}")
    public Response addAdjacency(@PathParam("clase") Integer clase, @PathParam("v1") Integer v1, @PathParam("v2") Integer v2) throws Exception {
        if (clase == 0) {
            MascotaServices mascotaServices = new MascotaServices();
            mascotaServices.addEdgeMascota(v1, v2);
            return ResponseFactory.buildResponse("success");
        } else if (clase == 1) {
            VeterinariaServices veterinariaServices = new VeterinariaServices();
            veterinariaServices.addEdgeVeterinaria(v1, v2);
            return ResponseFactory.buildResponse("success");
        } else {
            return ResponseFactory.buildResponseWithException(new Exception("Bad Request"));
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/shortest-paths/{clase}/{source}")
    public Response shortestPath(@PathParam("clase") Integer clase, @PathParam("source") Integer source) {
        LabeledGraph<Object> grafo = null;

        try {
            if (clase == 0) {
                grafo = JsonFileManager.graphFromJson(Mascota.class, true);    
            } else if (clase == 1) {
                grafo = JsonFileManager.graphFromJson(Veterinaria.class, true);
            } else {

            }

            HashMap<String, Object> shortestPath = new HashMap<>();
            shortestPath.put("source", source);
            shortestPath.put("paths", grafo.bellmanFord(source));
            return ResponseFactory.buildResponse(shortestPath);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseFactory.buildResponseWithException(e);
        }
    }



}
